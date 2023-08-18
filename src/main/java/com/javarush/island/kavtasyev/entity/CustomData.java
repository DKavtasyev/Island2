package com.javarush.island.kavtasyev.entity;

public class CustomData
{
	private final int width;
	private final int height;
	private final long dayLength;

	public CustomData(int width, int height, long dayLength)
	{
		this.width = width;
		this.height = height;
		this.dayLength = dayLength;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public long getDayLength()
	{
		return dayLength;
	}
}
