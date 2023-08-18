package com.javarush.island.kavtasyev.repository;

import java.util.HashMap;

public final class CreaturePopulation
{
	public static final HashMap<String, Integer> population = new HashMap<>();

	static
	{
		population.put("Bear", 5);
		population.put("Boa", 15);
		population.put("Boar", 5);
		population.put("Buffalo", 10);
		population.put("Caterpillar", 1000);
		population.put("Deer", 15);
		population.put("Duck", 20);
		population.put("Eagle", 10);
		population.put("Fox", 15);
		population.put("Goat", 30);
		population.put("Horse", 20);
		population.put("Mouse", 500);
		population.put("Plant", 100);
		population.put("Rabbit", 30);
		population.put("Sheep", 20);
		population.put("Wolf", 15);
	}

	private CreaturePopulation()
	{
	}
}

//		population.put("Bear", 5);
//		population.put("Boa", 15);
//		population.put("Boar", 5);
//		population.put("Buffalo", 10);
//		population.put("Caterpillar", 1000);
//		population.put("Deer", 15);
//		population.put("Duck", 20);
//		population.put("Eagle", 10);
//		population.put("Fox", 15);
//		population.put("Goat", 30);
//		population.put("Horse", 20);
//		population.put("Mouse", 500);
//		population.put("Plant", 100);
//		population.put("Rabbit", 30);
//		population.put("Sheep", 20);
//		population.put("Wolf", 15);