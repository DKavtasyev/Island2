package com.javarush.island.kavtasyev.entity;

import com.javarush.island.kavtasyev.repository.ResultCode;

public class Result
{
	private final ResultCode resultCode;

	private Exception exception;
	private String dayLength;
	private String dayLoadTime;
	private String statisticsTime;

	public Result(ResultCode resultCode)
	{
		this.resultCode = resultCode;
	}

	public Result(ResultCode resultCode, Exception exception)
	{
		this.resultCode = resultCode;
		this.exception = exception;
	}

	public Result(ResultCode resultCode, Exception exception, String dayLength)
	{
		this.resultCode = resultCode;
		this.exception = exception;
		this.dayLength = dayLength;
	}

	public Result(ResultCode resultCode, String dayLength, String dayLoadTime, String statisticsTime)
	{
		this.resultCode = resultCode;
		this.dayLength = dayLength;
		this.dayLoadTime = dayLoadTime;
		this.statisticsTime = statisticsTime;
	}

	public ResultCode getResultCode()
	{
		return resultCode;
	}

	public Exception getException()
	{
		return exception;
	}

	public String getDayLength()
	{
		return dayLength;
	}

	public String getDayLoadTime()
	{
		return dayLoadTime;
	}

	public String getStatisticsTime()
	{
		return statisticsTime;
	}
}
