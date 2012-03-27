package com.dahmian.javascript.annotation;

import java.util.regex.Pattern;

public abstract class Command 
{
	protected String commandString = "";

	public String getCommand()
	{
		return this.commandString;
	}

	public void setCommand(String command)
	{
		this.commandString = command;
	}

	public abstract void execute(String currentLine);

	public String[] parseArguments(String currentLine)
	{
		String regularExpression = "\\w";
		Pattern pattern = Pattern.compile(regularExpression);
		String[] arguments = pattern.split(currentLine);
		return arguments;
	}
}
