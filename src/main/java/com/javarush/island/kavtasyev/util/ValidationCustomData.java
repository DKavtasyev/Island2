package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.config.IslandConfig;

public class ValidationCustomData
{
	private ValidationCustomData()
	{
	}

	public static int validateWidth(String width)
	{
		if (width.equals(""))
			return IslandConfig.width;

		int n;
		try
		{
			n = Integer.parseInt(width);
			if (n >= 10 && n <= 200)
				return n;
			else
				return -1;
		}
		catch (NumberFormatException e)
		{
			return -1;
		}
	}

	public static int validateHeight(String height)
	{
		if (height.equals(""))
			return IslandConfig.height;

		int n;
		try
		{
			n = Integer.parseInt(height);
			if (n >= 5 && n <= 100)
				return n;
			else
				return -1;
		}
		catch (NumberFormatException e)
		{
			return -1;
		}
	}

	public static long validateDayLength(String dayLength)
	{
		if (dayLength.equals(""))
			return IslandConfig.dayLength / 1000;

		long n;
		try
		{
			n = Long.parseLong(dayLength);
			if (n >= 1 && n <= 10)
				return n;
			else
				return -1;
		}
		catch (NumberFormatException e)
		{
			return -1;
		}
	}
}
