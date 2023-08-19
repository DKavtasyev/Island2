package com.javarush.island.kavtasyev.entity;

public class Coordinates
{
	private final int x;
	private final int y;

	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	@Override
	public String toString()
	{
		return String.format("(%1$d; %2$d)", x, y);
	}
}
