package com.dahmian.javascript.annotation;

import java.util.*;

public class Runner 
{
	public static void main(String[] args)
	{
	        Engine engine = new Engine();

		engine.setUseSave(true);
		engine.setUseAssertions(false);
		for (String currentArgument : args)
		{
			if (currentArgument.matches("-ea"))
			{
				engine.setUseAssertions(true);
				engine.setUseSave(false);
			}
		}
		for (String currentArgument : args)
		{
			if (!currentArgument.matches("-.[a-zA-Z]"))
			{
				engine.loadScript(currentArgument);
			}
		}
	}
}
