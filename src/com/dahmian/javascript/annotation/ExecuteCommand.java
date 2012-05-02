package com.dahmian.javascript.annotation;
import javax.script.*;

public class ExecuteCommand extends Command
{
	public ExecuteCommand()
	{
		setCommand("execute");
	}

	public void execute(Engine engine, String annotationCommands)
	{
		engine.eval(annotationCommands);
	}	
}
