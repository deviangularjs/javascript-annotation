package com.dahmian.javascript.annotation;

import java.io.*;
import java.util.regex.*;
import javax.script.*;

public class Engine
{
	protected Command[] commandList = {new ExecuteCommand()};
	protected String annotationToken = "@jsa";
	private String currentLine = "";
	private ScriptEngineManager scriptEngineManager;
	private ScriptEngine javaScriptEngine;

	public Engine()
	{
		scriptEngineManager = new ScriptEngineManager();
		javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		loadFile("scripts/jsa.js");
	}

	public ScriptEngine getEngine()
	{
		return javaScriptEngine;
	}

	public void loadFile(String filename)
	{
		try
		{
			javaScriptEngine.put(ScriptEngine.FILENAME, filename.toString());
			javaScriptEngine.eval(new FileReader(filename));
		}
		catch (ScriptException exception)
		{
			printScriptError(exception);
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("file not found!");
		}
	}

	static public void printScriptError(ScriptException exception)
	{
		System.out.println("JavaScript error: " + exception.getMessage());
		System.exit(1);
	}

	public void parseFile(String filename)
	{
		BufferedReader in;
		try
		{
			in = new BufferedReader(new FileReader(filename));

			while ((currentLine = in.readLine()) != null)
			{
				for (Command command : commandList)
				{
					if (containsAnnotationToken() && currentLine.contains(command.getCommand()))
					{
						String args = parseArguments(command.getCommand());
						command.execute(getEngine(), args);
					}
				}
			}

			in.close();
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("file not found!");
			
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
