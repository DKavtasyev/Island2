package com.javarush.island.kavtasyev.view.guiview.util;

import java.util.concurrent.CyclicBarrier;

public class GUISynchronizer
{
	public static final CyclicBarrier BARRIER = new CyclicBarrier(2, () -> System.err.println("***************************************** Барьер пройден! *****************************************"));

	private GUISynchronizer()
	{
	}
}
