package com.javarush.island.kavtasyev.util;

import java.util.concurrent.ThreadLocalRandom;

public class GetRandom
{
	public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

	private GetRandom()
	{
	}
}
