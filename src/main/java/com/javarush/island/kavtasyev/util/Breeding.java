package com.javarush.island.kavtasyev.util;

import com.javarush.island.kavtasyev.config.IslandConfig;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.exception.CreateObjectException;
import com.javarush.island.kavtasyev.factory.CreaturesFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Breeding
{
	private static Cell currentCell;

	private Breeding()
	{
	}

	public static Creature availablePair(Creature thisCreature) throws InterruptedException
	{
		currentCell = thisCreature.getCurrentCell();
		HashSet<Creature> relatives = currentCell.getCellCreatures().get(thisCreature.getClass());						// Получаем ссылку на множество всех сородичей в текущей локации

		for (Creature pair : relatives)
		{
			if (pair == thisCreature)																					// С собой размножиться не получится
				continue;
			if( pair.getLock().tryLock(IslandConfig.dayLength / 10, TimeUnit.MILLISECONDS) )						// Если получилось занять животное pair, проверяем, подходит ли оно
			{
				if (pair.isMale() != thisCreature.isMale() && !pair.isBred() && pair.getAge() > pair.getMatureAge())	// Проверка, подходящая ли особь для размножения. Если да, тогда пытаемся её занять и возвращаем его
					return pair;
				else																									// Если нет, тогда отпускаем
					if (pair.getLock().isHeldByCurrentThread())
						pair.getLock().unlock();
			}
		}
		return null;																									// Если подходящего животного не нашлось, тогда возвращаем null
	}

	public static boolean breed(Creature thisCreature, Creature pair) throws CreateObjectException, IOException
	{
		currentCell = thisCreature.getCurrentCell();

		if (pair == null)																								// Проверка на то, нашлась ли пара для размножения
			return false;																								// Если не нашлась, то возвращаем false

		try																												// Если нашлась, то размножаемся с ней
		{
			Creature newCreature = CreaturesFactory.getInstance(thisCreature.getClass(), currentCell);
			newCreature.setAge(0);
			newCreature.setWantToEat(0);
			pair.setBred(true);
			thisCreature.setBred(true);
			currentCell.getCellCreatures().get(newCreature.getClass()).add(newCreature);								// TODO синхронизовать список животных для записи
			System.out.printf("Животное %1$s размножилось с животным %2$s. В результате размножения появилось животное %3$s.%n", thisCreature.getClass().getSimpleName() + thisCreature.hashCode(), pair.getClass().getSimpleName() + pair.hashCode(), newCreature.getClass().getSimpleName() + newCreature.hashCode());
			return true;																								// Подтверждаем размножение, бежать в другую локацию искать пару не нужно
		}
		finally
		{
			pair.getLock().unlock();																					// Отпускаем пару после размножения
		}
	}
}
