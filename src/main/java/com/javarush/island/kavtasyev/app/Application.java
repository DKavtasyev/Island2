package com.javarush.island.kavtasyev.app;

import com.javarush.island.kavtasyev.controller.Controller;
import com.javarush.island.kavtasyev.entity.CustomData;
import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.Statistics;
import com.javarush.island.kavtasyev.exception.CreateObjectException;
import com.javarush.island.kavtasyev.util.IslandScheduler;
import com.javarush.island.kavtasyev.util.Spawner;

import java.io.IOException;
import java.util.concurrent.Exchanger;

public class Application
{
	private final Controller controller;
	private Island island;
	private Statistics statistics;
	private Exchanger<Statistics> exchanger;

	public Application(Controller controller)
	{
		this.controller = controller;
	}

	public void run()
	{
		CustomData customData = controller.getView().getCustomerParameters();
		island = new Island(customData);
		try
		{
			startLivingProcess();
		}
		catch (CreateObjectException | IOException | InterruptedException e)											//TODO продумать, куда пробрасывать исключения
		{
			e.printStackTrace();
		}
	}

	public void startLivingProcess() throws CreateObjectException, IOException, InterruptedException
	{
		Spawner spawner = new Spawner(island);
		spawner.spawnIslandCreatures();


//		Cell[][] cells = island.getCells();
//		HashSet<Creature> wolves = cells[5][4].getCellCreatures().get(Wolf.class);
//		HashSet<Creature> rabbits = cells[5][4].getCellCreatures().get(Rabbit.class);
//		HashSet<Creature> boars = cells[7][2].getCellCreatures().get(Boar.class);
//
//		for (int i = 0; i < 30; i++)
//		{
//			Wolf wolf;
//			Rabbit rabbit;
//			Boar boar;
//			wolf = CreaturesFactory.getInstance(Wolf.class, cells[5][4]);
//			rabbit = CreaturesFactory.getInstance(Rabbit.class, cells[5][4]);
//			boar = CreaturesFactory.getInstance(Boar.class, cells[7][2]);
//
//			wolves.add(wolf);
//			rabbits.add(rabbit);
//			boars.add(boar);
//		}

		exchanger = new Exchanger<>();
		IslandScheduler islandScheduler = new IslandScheduler(island, exchanger);

		islandScheduler.startActivity();


	}
	public Statistics getStatistics()
	{
		try
		{
			statistics = exchanger.exchange(statistics);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return statistics;
	}

	public void printStatistics(Statistics statistics)
	{
		controller.getView().printStatistics(statistics);
	}
}
