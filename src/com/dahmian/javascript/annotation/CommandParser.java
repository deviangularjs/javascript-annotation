package com.dahmian.javascript.annotation;

import java.io.*;
import java.util.regex.*;
import javax.script.*;

public class CommandParser
{
	protected Command[] commandList = {new ExecuteCommand()};
	protected String annotationToken = "@jsa";
	private String currentLine = "";
	private ScriptEngine javaScriptEngine;
	
	public CommandParser(File scriptFile, ScriptEngine javaScriptEngine)
	{
		this.javaScriptEngine = javaScriptEngine;
		parseScript(scriptFile);
	}

	private void parseScript(File scriptFile)
	{
		BufferedReader in;
		try
		{
			in = new BufferedReader(new FileReader(scriptFile));

			while ((currentLine = in.readLine()) != null)
			{
				for (Command command : commandList)
				{
					if (containsAnnotationToken() && currentLine.contains(command.getCommand()))
					{
						String args = parseArguments(command.getCommand());
						command.execute(javaScriptEngine, args);
					}
				}
			}

			in.close();
		}
		catch (FileNotFoundException exception)
		{
			Engine.printFileNotFound();
		}
		catch (IOException exception)
		{
			System.out.println("unable to read file");
		}

	}

	private boolean containsAnnotationToken()
	{
		if (currentLine.contains(annotationToken))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private String parseArguments(String command)
	{
		String args = "";
		String regularExpression = ".*" + annotationToken + "\\s*" + command + "\\s*(.*)";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(currentLine);
		if (matcher.matches())
		{
			args = matcher.group(1);
		}
		else
		{
			args = "FAIL";
		}
		return args;
	}
}
