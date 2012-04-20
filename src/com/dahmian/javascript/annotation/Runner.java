package com.dahmian.javascript.annotation;

public class Runner 
{
	public static void main(String[] args)
	{
	        Engine engine = new Engine();
                for (String currentArg : args)
                {
                        engine.loadFile(currentArg);
                }	
	}
}
