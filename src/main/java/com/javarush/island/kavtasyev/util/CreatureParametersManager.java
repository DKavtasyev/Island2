package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.creatures.animals.Animal;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.repository.ListOfCreaturesTypes;

import java.util.HashMap;
import java.util.HashSet;

public class CreatureParametersManager implements Runnable
{
	private final Island island;

	public CreatureParametersManager(Island island)
	{
		this.island = island;
	}

	@Override
	public void run()
	{
		Cell[][] cells = island.getCells();
		for (int y = 0; y < island.getCustomData().getHeight(); y++)
		{
			for (int x = 0; x < island.getCustomData().getWidth(); x++)
			{
				for(Class<? extends Creature> clazz : ListOfCreaturesTypes.listOfCreaturesTypes)
				{
					HashMap<Class<? extends Creature>, HashSet<Creature>> cellCreatures = cells[y][x].getCellCreatures();
					HashSet<Creature> cellCreaturesOfSpecies = cellCreatures.get(clazz);
					synchronized (cellCreaturesOfSpecies)
					{
						for (Creature creature : cellCreaturesOfSpecies)
						{
							if (creature instanceof Animal)
							{
								creature.setAge(creature.getAge() + 1);													// Животное стало ещё на один день старше.

								double d = creature.getWantToEat() - creature.getMassOfFood() * 0.3;					// Животное хочет есть на 30 % больше за каждый день.
								double wantToEat = Math.min(d, creature.getMassOfFood());
								creature.setWantToEat(wantToEat);

								if (creature.getWantToEat() > creature.getMassOfFood() * 0.8)							// Если животное голодно на 80 %,
								{
									creature.setWeight(creature.getWeight() * 0.9);										// Тогда оно теряет 10 % массы.
								}

								if (creature.getWeight() < creature.getMaxWeight() * 0.5)								// Если животное похудело больше чем на 50 %, тогда оно умирает от голода
									creature.setAlive(false);
							}
							else if (creature instanceof Plant)															// У растений просто удваивается вес
							{
								if (creature.getWeight() < 1024)
									creature.setWeight(creature.getWeight() * 2);
							}

						}
					}
				}
			}
		}
	}
}
