package com.dahmian.javascript.annotation;

public class main
{
	public static void main(String[] args)
	{
	        Engine engine = new Engine();
                for (String currentArg : args)
                {
                        engine.loadFile(currentArg);
                        engine.parseFile(currentArg);
                }	
	}
}
