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

	public abstract JavaScriptFile execute(JavaScriptFile script);

	protected boolean hasToken(String line)
	{
		if (Annotation.containsAnnotationToken(line) && line.contains(this.getCommand()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	protected String removeCommand(String line)
	{
		line = line.replaceFirst(this.getCommand() + " ", "");
		return line;
	}

	public String[] getArguments(String line)
	{
		line = Annotation.removeAnnotationToken(line);
		line = removeCommand(line);
		String regularExpression = "\\w";
		Pattern pattern = Pattern.compile(regularExpression);
		if (line.matches("\\w"))
		{
			return pattern.split(line);
		}
		else
		{
			String[] arguments = {line};
			return arguments;
		}
	}
}
