package com.javarush.island.kavtasyev.entity.creatures.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javarush.island.kavtasyev.config.IslandConfig;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.exception.CreateObjectException;
import com.javarush.island.kavtasyev.util.Breeding;
import com.javarush.island.kavtasyev.util.Feeding;
import com.javarush.island.kavtasyev.util.Moving;
import javafx.scene.image.Image;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Animal implements Creature, Cloneable
{
	public double maxWeight;
	public int speed;
	public int maxSpeed;
	public double massOfFood;
	public int matureAge;
	public int maxAge;
	public int maxEnergy;
	public int maxNumberPerCell;
	public String imagePath;


	public double weight;
	public boolean isAlive;
	public int energy;
	public int age;
	public boolean isMale;
	public boolean isBred;
	public double wantToEat;

	@JsonIgnore
	protected Image image;
	@JsonIgnore
	protected Cell currentCell;
	@JsonIgnore
	protected final HashMap<Class<? extends Creature>, Integer> foodMap = new HashMap<>();
	@JsonIgnore
	public ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run()
	{
		try
		{
			boolean lockIsCaptured = this.lock.tryLock(IslandConfig.dayLength / 500, TimeUnit.MILLISECONDS);		// запрос на захват лока текущего животного
			if (lockIsCaptured)
				decideWhatToDo();
			else
				System.err.println("*ProVoLokA************************************* Животное заблокировано!!! **************************************");
		}
		catch (CreateObjectException | InterruptedException | IOException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException ignored)																			// Если объект уже удалён, то прекращаем выполнение таска. Значит его съели
		{
			System.err.println("*ProVoLokA************************************* Животное равно null!!! **************************************");
		}
		finally
		{
			if (this.lock.isHeldByCurrentThread())
				this.lock.unlock();
		}
	}

	private void decideWhatToDo() throws InterruptedException, CreateObjectException, IOException
	{
		if(!isAlive)
		{
			System.err.println("*ProVoLokA************************************* Мёртвое животное!!! **************************************");
			return;
		}
		if (wantToEat < massOfFood * 0.5)													// Если хочет есть больше, чем на 50 %, то в первую очередь нужно поесть.
		{
			if (Feeding.foodIsAvailable(this))									// Если еда доступна, то тогда пытаемся её есть.
				Feeding.eat(this);
			else																			// Иначе двигаемся в поисках еды.
				Moving.move(this);
		}
		else if (isMale && age > matureAge)													// Если хочет есть меньше, чем на 50 %, то тогда нужно размножиться.
		{
			if (!Breeding.breed(this, Breeding.availablePair(this)))	// Ищем партнёра и размножаемся с ним.
				Moving.move(this);												// Если партнёр не нашёлся, то тогда отправляемся в поиски нового партнёра.
		}
		else																				// Если мы - несовершеннолетнее сытое животное, то тогда - просто радуемся жизни и ходим по острову.
			Moving.move(this);
																							// TODO если реализовывать GUI, то тогда нужно в конце каждого действия перерисовывать поле
	}

	@Override
	public String getImagePath()
	{
		return imagePath;
	}

	@Override
	public int getSpeed()
	{
		return speed;
	}

	@Override
	public void setImage(Image image)
	{
		this.image = image;
	}

	@Override
	public Image getImage()
	{
		return image;
	}

	@Override
	public void setCurrentCell(Cell currentCell)
	{
		this.currentCell = currentCell;
	}

	@Override
	public Cell getCurrentCell()
	{
		return currentCell;
	}

	@Override
	public double getWeight()
	{
		return weight;
	}

	@Override
	public double getMaxWeight()
	{
		return maxWeight;
	}

	@Override
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	@Override
	public int getMatureAge()
	{
		return matureAge;
	}

	@Override
	public int getAge()
	{
		return age;
	}

	@Override
	public boolean isMale()
	{
		return isMale;
	}

	@Override
	public boolean isBred()
	{
		return isBred;
	}

	@Override
	public void setBred(boolean bred)
	{
		isBred = bred;
	}

	@Override
	public void setAge(int age)
	{
		this.age = age;
	}

	@Override
	public boolean isAlive()
	{
		return isAlive;
	}

	@Override
	public void setWantToEat ( double wantToEat)
	{
		this.wantToEat = wantToEat;
	}

	@Override
	public ReentrantLock getLock()
	{
		return lock;
	}

	@Override
	public int getMaxNumberPerCell()
	{
		return maxNumberPerCell;
	}

	@Override
	public int getMaxSpeed()
	{
		return maxSpeed;
	}

	@Override
	public double getMassOfFood()
	{
		return massOfFood;
	}

	@Override
	public int getMaxAge()
	{
		return maxAge;
	}

	@Override
	public int getMaxEnergy()
	{
		return maxEnergy;
	}

	@Override
	public int getEnergy()
	{
		return energy;
	}

	@Override
	public double getWantToEat()
	{
		return wantToEat;
	}

	@Override
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	@Override
	public void setAlive(boolean alive)
	{
		isAlive = alive;
	}

	@Override
	public void setEnergy(int energy)
	{
		this.energy = energy;
	}

	@Override
	public void setMale(boolean male)
	{
		isMale = male;
	}

	@Override
	public HashMap<Class<? extends Creature>, Integer> getFoodMap()
	{
		return foodMap;
	}

	@Override
	public Creature clone()
	{
		try
		{
			Class<? extends Creature> clazz = this.getClass();
			Constructor<? extends Creature> constructor = clazz.getConstructor();
			Animal newInstance = (Animal) constructor.newInstance();
			newInstance.maxWeight = this.maxWeight;
			newInstance.speed = this.speed;
			newInstance.maxSpeed = this.maxSpeed;
			newInstance.massOfFood = this.massOfFood;
			newInstance.matureAge = this.matureAge;
			newInstance.maxAge = this.maxAge;
			newInstance.maxEnergy = this.maxEnergy;
			newInstance.maxNumberPerCell = this.maxNumberPerCell;
			newInstance.imagePath = this.imagePath;
			newInstance.weight = this.weight;
			newInstance.isAlive = true;
			newInstance.energy = this.energy;
			newInstance.isBred = false;
			return newInstance;
		}
		catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
			   InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

//	protected static final HashSet<Class<? extends Creature>> enemies = new HashSet<>();

