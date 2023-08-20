package com.javarush.island.kavtasyev.view.guiview;

import com.javarush.island.kavtasyev.entity.CustomData;
import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.Statistics;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.view.View;
import com.javarush.island.kavtasyev.view.guiview.util.CreatureViewer;
import com.javarush.island.kavtasyev.view.guiview.util.GUIExchanger;
import com.javarush.island.kavtasyev.view.guiview.util.GUIStarter;
import com.javarush.island.kavtasyev.view.guiview.util.GUISynchronizer;
import javafx.application.Platform;

import java.util.concurrent.BrokenBarrierException;

public class GUIView implements View
{
	private CustomData customData;

	@Override
	public CustomData getCustomerParameters() throws InterruptedException
	{
		GUIStarter guiStarter = new GUIStarter();
		Thread guiStarterThread = new Thread(guiStarter, "GUIStarter");
		guiStarterThread.start();
		customData = GUIExchanger.customDataExchanger.exchange(customData);
		return customData;
	}

	@Override
	public void printStatistics(Statistics statistics) throws InterruptedException
	{
//		GUIExchanger.statisticsExchanger.exchange(statistics);
	}

	@Override
	public void setIsland(Island island) throws InterruptedException
	{
		GUIExchanger.islandExchanger.exchange(island);
	}

	@Override
	public void synchronize() throws BrokenBarrierException, InterruptedException
	{
		GUISynchronizer.BARRIER.await();
		Thread.sleep(2000);
	}

	@Override
	public void movePicture(Creature thisCreature, Cell oldCell)
	{
		Platform.runLater(() -> CreatureViewer.moveCreaturePicture(thisCreature, oldCell));
	}

	@Override
	public void deletePicture(Creature creature)
	{
		Platform.runLater(() -> CreatureViewer.deleteCreaturePicture(creature));
	}

	@Override
	public void showPicture(Creature creature)
	{
		Platform.runLater(() -> CreatureViewer.showCreaturePicture(creature));
	}
}
