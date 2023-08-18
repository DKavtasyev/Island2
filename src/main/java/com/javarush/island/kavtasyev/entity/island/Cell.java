package com.javarush.island.kavtasyev.entity.island;

import com.javarush.island.kavtasyev.entity.Coordinates;
import com.javarush.island.kavtasyev.entity.creatures.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Cell
{
	private final Coordinates coordinates;
	private final Cell[][] allCells;
	private HashMap<Class<? extends Creature>, HashSet<Creature>> cellCreatures = new HashMap<>();
	private final ArrayList<Cell> neighbors = new ArrayList<>();

	public Cell(Coordinates coordinates, Cell[][] allCells)
	{
		this.allCells = allCells;
		this.coordinates = coordinates;
	}

	public ArrayList<Cell> getNeighbors()
	{
		return neighbors;
	}

	public HashMap<Class<? extends Creature>, HashSet<Creature>> getCellCreatures()
	{
		return cellCreatures;
	}

	public Cell[][] getAllCells()
	{
		return allCells;
	}

	public Coordinates getCoordinates()
	{
		return coordinates;
	}
}