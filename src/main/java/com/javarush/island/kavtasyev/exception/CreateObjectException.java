package com.javarush.island.kavtasyev.exception;

public class CreateObjectException extends Exception
{
	public CreateObjectException()
	{
	}

	public CreateObjectException(String message)
	{
		super(message);
	}

	public CreateObjectException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CreateObjectException(Throwable cause)
	{
		super(cause);
	}
}
