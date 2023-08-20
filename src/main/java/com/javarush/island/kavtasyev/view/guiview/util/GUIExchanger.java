package com.javarush.island.kavtasyev.view.guiview.util;

import com.javarush.island.kavtasyev.entity.CustomData;
import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.Statistics;

import java.util.concurrent.Exchanger;

public class GUIExchanger
{
	public static Exchanger<CustomData> customDataExchanger = new Exchanger<>();
	public static Exchanger<Statistics> statisticsExchanger = new Exchanger<>();
	public static Exchanger<Island> islandExchanger = new Exchanger<>();

	private GUIExchanger()
	{
	}
}
