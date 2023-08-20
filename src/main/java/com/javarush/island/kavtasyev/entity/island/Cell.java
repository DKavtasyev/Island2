package com.javarush.island.kavtasyev.entity.island;

import com.javarush.island.kavtasyev.entity.Coordinates;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Cell extends StackPane
{
	private final Coordinates coordinates;
	private final Cell[][] allCells;
	private final ConcurrentHashMap<Class<? extends Creature>, Set<Creature>> cellCreatures = new ConcurrentHashMap<>();
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

	public ConcurrentHashMap<Class<? extends Creature>, Set<Creature>> getCellCreatures()
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