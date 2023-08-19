package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Caterpillar;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.exception.CreateObjectException;
import com.javarush.island.kavtasyev.factory.CreaturesFactory;
import com.javarush.island.kavtasyev.repository.CreaturePopulation;
import com.javarush.island.kavtasyev.repository.SetOfCreaturesTypes;

import java.io.IOException;

public class Spawner
{
	private final Cell[][] cells;
	private final int width;
	private final int height;

	public Spawner(Island island)
	{
		cells = island.getCells();
		this.width = island.getCustomData().getWidth();
		this.height = island.getCustomData().getHeight();
	}

	public void spawnIslandCreatures() throws CreateObjectException, IOException
	{
		for(Class<? extends Creature> creatureClass: SetOfCreaturesTypes.SET_OF_CREATURES_TYPES)
		{
			int x = GetRandom.RANDOM.nextInt(width);
			int y = GetRandom.RANDOM.nextInt(height);
			String className = creatureClass.getSimpleName();

			if ( !CreaturePopulation.population.containsKey(className) )
				throw new CreateObjectException(String.format("Класс %s отсутствует в списке популяций животных и растений острова!", className));
			else if ( !creatureClass.isAnnotationPresent(Config.class))
				throw new CreateObjectException(String.format("Класс %s должен иметь аннотацию Config!", className));

			int population = CreaturePopulation.population.get(creatureClass.getSimpleName());
			for (int i = 0; i < population; i++)
			{
				Creature creature = CreaturesFactory.getInstance(creatureClass, cells[y][x]);

				if(creature instanceof Plant || creature instanceof Caterpillar)
				{
					int randomX = GetRandom.RANDOM.nextInt(width - 1);
					int randomY = GetRandom.RANDOM.nextInt(height - 1);
					cells[randomY][randomX].getCellCreatures().get(creatureClass).add(creature);
				}
				else
					cells[y][x].getCellCreatures().get(creatureClass).add(creature);
			}
		}
	}
}
