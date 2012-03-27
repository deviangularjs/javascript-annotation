package com.dahmian.javascript.annotation;
import javax.script.*;

public class ExecuteCommand extends Command
{
	public ExecuteCommand()
	{
		this.setCommand("execute");
	}

	public void execute(String currentLine)
	{
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
		try
		{
			jsEngine.eval(currentLine);
		}
		catch (ScriptException ex)
		{
			ex.printStackTrace();
		}    
	}	
}
