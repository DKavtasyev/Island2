package com.javarush.island.kavtasyev.util;

import java.time.Instant;

public interface Timeable
{
	default long getMillis(Instant start, Instant stop)
	{
		int nanos1 = stop.getNano() - start.getNano();
		long seconds = stop.getEpochSecond() - start.getEpochSecond();
		int nanos2 = nanos1;
		if (nanos1 < 0)
		{
			nanos2 = nanos1 + 1_000_000_000;
			seconds -= 1;
		}
		return seconds * 1000 + nanos2 / 1_000_000;
	}
}


//	default String getTime(Instant start, Instant stop)
//	{
//		int nanos = stop.getNano() - start.getNano();
//		long seconds = stop.getEpochSecond() - start.getEpochSecond();
//		int nanos3 = nanos;
//		if (nanos < 0)
//		{
//			nanos3 = nanos + 1_000_000_000;
//			seconds -= 1;
//		}
//		int millis = nanos3 / 1_000_000;
//		int micros = (nanos3 / 1_000) % 1000;
//		int nanos1 = nanos3 % 1000;
//
//		return String.format("Время выполнения равно %1$d c %3$d мс\t%3$d мкс\t%4$d нс.\t%n", seconds, millis, micros, nanos1);
//	}