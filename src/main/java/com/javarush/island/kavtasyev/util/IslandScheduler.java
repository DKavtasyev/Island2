package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.constants.Text;
import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.Result;
import com.javarush.island.kavtasyev.entity.Statistics;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.creatures.animals.Animal;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.repository.ListOfCreaturesTypes;
import com.javarush.island.kavtasyev.repository.ResultCode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class IslandScheduler implements Timeable
{
	private final Island island;
	private final Cell[][] cells;
	private final int width;
	private final int height;
	private long dayLength;
	private Exchanger<Statistics> exchanger;
	private ScheduledExecutorService executorService;


	public IslandScheduler(Island island, Exchanger<Statistics> exchanger)
	{
		this.island = island;
		cells = island.getCells();
		this.width = island.getCustomData().getWidth();
		this.height = island.getCustomData().getHeight();
		this.dayLength = island.getCustomData().getDayLength();
		this.exchanger = exchanger;
	}

	public void startActivity()
	{
		executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(task, 0, dayLength, TimeUnit.MILLISECONDS);						// Пока ещё Main-thread
	}

	Runnable task = () -> {																								// Вот отсюда уже не Main-thread.
		Instant startDay = Instant.now();

		manageCreatureLife();

		List<ScheduledExecutorService> executorServices = new ArrayList<>();
		downloadActionsOfTheDay(executorServices);																		// Запуск потоков, ответственных за действия животных

		Instant stopDayLoad = Instant.now();																			// Но здесь первый каскад, поток, ответственный за день
		long dayLoadTimeMillis = getMillis(startDay, stopDayLoad);

		try
		{
			if (dayLoadTimeMillis < dayLength)
			{
				Thread.sleep((long) (dayLength * 0.95 - dayLoadTimeMillis));
			}
			else
			{
				System.err.println("Не хватило времени для запуска дня!");
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		stopActionsOfTheDay(executorServices);

		try
		{
			Statistics statistics = countStatistics();																	// В самом конце дня выполняются сервисные функции
			Instant stopDay = Instant.now();
			long dayLengthMillis = getMillis(startDay, stopDay);
			String dayLoadTime = String.format(Text.DAY_LOAD, dayLoadTimeMillis);
			String dayLength = String.format(Text.DAY_LENGTH, dayLengthMillis);
			String statisticsTime = String.format(Text.STATISTICS_LENGTH, statistics.getTime());
			Result result = new Result(ResultCode.OK, dayLength, dayLoadTime, statisticsTime);
			statistics.setResult(result);
			exchanger.exchange(statistics);																				// В конце каждого дня отдельный Thread высчитывает и возвращает статистику

			if (statistics.getPopulation() == 0)
				executorService.shutdown();
		}
		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}


	};

	private void stopActionsOfTheDay(List<ScheduledExecutorService> executorServices)
	{
		for(ScheduledExecutorService executorService : executorServices)
		{
			try
			{
				executorService.shutdown();
				boolean activitiesIsFinished = executorService.awaitTermination((long)(dayLength * 0.02), TimeUnit.MILLISECONDS);
				if (!activitiesIsFinished)
					System.err.println("Не хватило длины дня для выполнения всех действий животных. Пожалуйста, увеличьте значение длины дня");
			}
			catch (InterruptedException e)
			{
				System.err.println("Задания были прерваны.");
				e.printStackTrace();
			}
			finally
			{
				if (!executorService.isTerminated())
				{
					System.err.println("Принудительное завершение всех действий животных.");
					executorService.shutdownNow();
					System.err.println("Действия животных принудительно завершены.");
				}
				else
					System.out.println("Все действия данного вида животных успешно завершились до окончания дня");
			}
		}
	}

	private void downloadActionsOfTheDay(List<ScheduledExecutorService> executorServices)
	{
		for(Class<? extends Creature> clazz : ListOfCreaturesTypes.listOfCreaturesTypes)
		{
			ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
			executorServices.add(executor);

			for (int y = 0; y < height; y++)
			{
				for (int x = 0; x < width; x++)
				{
					HashMap<Class<? extends Creature>, HashSet<Creature>> cellCreatures = cells[y][x].getCellCreatures();
					HashSet<Creature> cellCreaturesOfSpecies = cellCreatures.get(clazz);
					for (Creature creature : cellCreaturesOfSpecies)
					{
						if (creature instanceof Animal)																	// Вот отсюда второй каскад потоков, в которых животные непосредственно выполняют свои действия. Действия совершают только животные
						{
							long initialDelay = creature.getSpeed() == 0 ? (long) ( dayLength * 0.3 ) : (long) (( dayLength / creature.getSpeed()) * 0.3);
							long period = creature.getSpeed() == 0 ? dayLength : dayLength / creature.getSpeed();
							executor.scheduleAtFixedRate(creature, initialDelay, period, TimeUnit.MILLISECONDS);
						}
					}
				}
			}
		}
	}

	private Statistics countStatistics() throws ExecutionException, InterruptedException
	{
		MonitoringStatistics monitoringStatistics = new MonitoringStatistics(island);
		FutureTask<Statistics> futureTask = new FutureTask<>(monitoringStatistics);
		Thread countStatistics = new Thread(futureTask);
		countStatistics.start();
		return futureTask.get();
	}

	private void manageCreatureLife()
	{
		collectDeadCreatures();
		manageCreatureLifeParameters();
	}

	private void manageCreatureLifeParameters()
	{
		CreatureParametersManager parametersManager = new CreatureParametersManager(island);
		Thread parametersManagerThread = new Thread(parametersManager);
		parametersManagerThread.start();
	}

	private void collectDeadCreatures()
	{
		DeadsCollector deadsCollector = new DeadsCollector(island);
		Thread deadsCollectorThread = new Thread(deadsCollector);
		deadsCollectorThread.start();
	}
}



//		ExecutorService executor = Executors.newFixedThreadPool(10);
//		for (int y = 0; y < height; y++)
//		{
//		for (int x = 0; x < width; x++)
//		{
//		HashMap <Class<? extends Creature>, HashSet<Creature>> cellCreatures = cells[y][x].getCellCreatures();
//		for(Class<? extends Creature> clazz : cellCreatures.keySet())
//		{
//		HashSet<Creature> cellCreaturesOfSpecies = cellCreatures.get(clazz);
//		for(Creature creature : cellCreaturesOfSpecies)
//		{
//		executor.submit(creature);
//		}
//		}
//		}
//		}