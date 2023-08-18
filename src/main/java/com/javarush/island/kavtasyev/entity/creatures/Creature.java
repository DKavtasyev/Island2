package com.javarush.island.kavtasyev.entity.creatures;

import com.javarush.island.kavtasyev.entity.island.Cell;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public interface Creature extends Runnable
{
	String getImagePath();
	int getSpeed();
	void setImage(Image image);
	Image getImage();
	void setCurrentCell(Cell currentCell);
	Cell getCurrentCell();
	double getWeight();
	void setWeight(double weight);
	double getMaxWeight();
	int getMatureAge();
	int getAge();
	boolean isMale();
	boolean isBred();
	void setBred(boolean bred);
	void setAge(int age);
	boolean isAlive();
	void setWantToEat(double wantToEat);
	ReentrantLock getLock();
	int getMaxNumberPerCell();
	int getMaxSpeed();
	double getMassOfFood();
	int getMaxAge();
	int getMaxEnergy();
	int getEnergy();
	double getWantToEat();
	void setSpeed(int speed);
	void setAlive(boolean alive);
	void setEnergy(int energy);
	void setMale(boolean male);
	HashMap<Class<? extends Creature>, Integer> getFoodMap();
}





