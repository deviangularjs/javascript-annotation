package com.dahmian.javascript.annotation;

import java.util.regex.Pattern;
import javax.script.*;

public abstract class Command 
{
	protected String commandString = "";

	public String getCommand()
	{
		return commandString;
	}

	public void setCommand(String command)
	{
		this.commandString = command;
	}

	public abstract void execute(ScriptEngine engine, String annotationCommands);

	public String[] parseArguments(String annotationCommands)
	{
		String regularExpression = "\\w";
		Pattern pattern = Pattern.compile(regularExpression);
		String[] arguments = pattern.split(annotationCommands);
		return arguments;
	}
}
