package com.dahmian.javascript.annotation;

import java.util.*;

public class Runner 
{
	public static void main(String[] args)
	{
	        Engine engine = new Engine();

		engine.disableAssertions();
		for (String currentArgument : args)
		{
			if (currentArgument.matches("-ea") || currentArgument.matches("-enableassertions"))
			{
				engine.enableAssertions();
			}
		}
		for (String currentArgument : args)
		{
			if (!currentArgument.matches("-[a-zA-Z]*"))
			{
				engine.loadScript(currentArgument);
			}
		}
	}

}
