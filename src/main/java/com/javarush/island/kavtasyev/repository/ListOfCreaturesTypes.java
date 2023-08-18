package com.javarush.island.kavtasyev.repository;

import com.javarush.island.kavtasyev.entity.creatures.Creature;

import java.util.HashSet;

public final class ListOfCreaturesTypes
{
	private ListOfCreaturesTypes()
	{
	}

	public static final HashSet<Class<? extends Creature>> listOfCreaturesTypes = new HashSet<>();
}

