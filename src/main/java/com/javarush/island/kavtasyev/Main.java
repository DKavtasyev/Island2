package com.javarush.island.kavtasyev;

import com.javarush.island.kavtasyev.app.Application;
import com.javarush.island.kavtasyev.controller.Controller;
import com.javarush.island.kavtasyev.entity.Statistics;
import com.javarush.island.kavtasyev.exception.CreateObjectException;
import com.javarush.island.kavtasyev.view.View;
import com.javarush.island.kavtasyev.view.guiview.GUIView;

import java.io.IOException;

public class Main
{
	// TODO Сделать ScheduleExecutor, который будет управлять параметрами всех животных

	public static void main(String[] args) throws InterruptedException, CreateObjectException, IOException
	{
		View view = new GUIView();
		Controller controller = new Controller(view);
		Application application = new Application(controller);
		application.run();

		while (true)
		{
			try
			{
				Statistics statistics = application.getStatistics();
				application.printStatistics(statistics);
				if (statistics.getPopulation() == 0)
					break;
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}


//		while (true)
//		{
////			if (isMonitoringDone)
//			{
//				MonitoringData monitoringData = application.getMonitoringData();
//				application.printMonitoringData(monitoringData);
////				isMonitoringDone = false;
//				Thread.sleep(IslandConfig.dayLength);
//			}
//		}
	}


//	public static void main(String[] args) throws CreateObjectException, IOException, InterruptedException
//	{
//		Island island = new Island();
//
//		Cell[][] cells = island.getCells();
//
//		Wolf wolf = CreaturesFactory.getInstance(Wolf.class, cells[5][4]);
//		wolf.energy = 50;
//
//		HashSet<Creature> wolves = cells[5][4].getCellCreatures().get(Wolf.class);
//
//		wolves.add(wolf);
//
//		Thread thread = new Thread(wolf);
//		thread.start();
//	}
}
