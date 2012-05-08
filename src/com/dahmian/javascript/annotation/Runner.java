package com.dahmian.javascript.annotation;

public class Runner 
{
	public static void main(String[] args)
	{
	        Engine engine = new Engine();
		engine.setUseAssertions(true);
		engine.setUseSave(true);
		engine.loadScript(args);
	}
}
