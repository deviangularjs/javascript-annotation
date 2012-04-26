package com.dahmian.javascript.annotation;

import java.util.regex.Pattern;
import javax.script.*;

public abstract class Command 
{
	protected String command = "";

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}

	public abstract void execute(ScriptEngine engine, String annotationCommands);

	static public String[] parseArguments(String annotationCommands)
	{
		String regularExpression = "\\w";
		Pattern pattern = Pattern.compile(regularExpression);
		String[] arguments = pattern.split(annotationCommands);
		return arguments;
	}
}
