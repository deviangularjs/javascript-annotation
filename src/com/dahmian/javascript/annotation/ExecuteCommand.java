package com.dahmian.javascript.annotation;
import javax.script.*;

public class ExecuteCommand extends Command
{
	public ExecuteCommand()
	{
		this.setCommand("execute");
	}

	public void execute(ScriptEngine engine, String annotationCommands)
	{
		try
		{
			engine.eval(annotationCommands);
		}
		catch (ScriptException ex)
		{
			ex.printStackTrace();
		}    
	}	
}
