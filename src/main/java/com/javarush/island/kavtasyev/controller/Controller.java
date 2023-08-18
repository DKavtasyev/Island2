package com.javarush.island.kavtasyev.controller;

import com.javarush.island.kavtasyev.view.View;

public class Controller
{
	private final View view;

	public Controller(View view)
	{
		this.view = view;
	}

	public View getView()
	{
		return view;
	}
}
