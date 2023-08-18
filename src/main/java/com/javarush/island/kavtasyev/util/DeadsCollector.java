package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.creatures.animals.Animal;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.repository.ListOfCreaturesTypes;

import java.util.HashMap;
import java.util.HashSet;

public class DeadsCollector implements Runnable
{
	private final Island island;

	public DeadsCollector(Island island)
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
						cellCreaturesOfSpecies.removeIf(creature -> (creature instanceof Animal) && (!creature.isAlive() || creature.getAge() >= creature.getMaxAge()));
						cellCreaturesOfSpecies.removeIf(creature -> (creature instanceof Plant) && (!creature.isAlive() || creature.getWeight() == 0));
					}
				}
			}
		}
	}
}
