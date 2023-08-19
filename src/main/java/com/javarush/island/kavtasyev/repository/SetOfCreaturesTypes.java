package com.javarush.island.kavtasyev.repository;

import com.javarush.island.kavtasyev.entity.creatures.Creature;

import java.util.HashSet;

public final class SetOfCreaturesTypes
{
	private SetOfCreaturesTypes()
	{
	}

	public static final HashSet<Class<? extends Creature>> SET_OF_CREATURES_TYPES = new HashSet<>();
}

