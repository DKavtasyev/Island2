package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.Statistics;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.repository.ListOfCreaturesTypes;

import java.time.Instant;
import java.util.HashSet;
import java.util.concurrent.Callable;

public class MonitoringStatistics implements Callable<Statistics>, Timeable
{
	private final Island island;
	public MonitoringStatistics(Island island)
	{
		this.island = island;
	}

	@Override
	public Statistics call()
	{
		Instant start = Instant.now();
		Cell[][] cells = island.getCells();
		Statistics statistics = new Statistics();
		HashSet<Creature> creaturesOfCertainSpeciesInCell;
		for (Cell[] lineOfCells : cells)
		{
			for (Cell value : lineOfCells)
			{
				synchronized (value)
				{
					for (Class<? extends Creature> clazz : ListOfCreaturesTypes.listOfCreaturesTypes)
					{
						creaturesOfCertainSpeciesInCell = value.getCellCreatures().get(clazz);
						int number = statistics.getAllCreaturesPopulation().get(clazz);
						statistics.getAllCreaturesPopulation().put(clazz, number + creaturesOfCertainSpeciesInCell.size());
						if (clazz != Plant.class)
							statistics.setPopulation(statistics.getPopulation() + creaturesOfCertainSpeciesInCell.size());
					}
				}
			}
		}
		Instant stop = Instant.now();
		statistics.setTime(getMillis(start, stop));
		return statistics;
	}
}
