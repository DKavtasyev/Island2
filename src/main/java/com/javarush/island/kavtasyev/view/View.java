package com.javarush.island.kavtasyev.view;

import com.javarush.island.kavtasyev.entity.CustomData;
import com.javarush.island.kavtasyev.entity.Statistics;

public interface View
{
	CustomData getCustomerParameters();
	void printStatistics(Statistics statistics);
}
