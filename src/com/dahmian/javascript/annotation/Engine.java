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

	/** Instantiates a JavaScript engine and loads common annotation JavaScript variables into the JavaScript engine environment.*/
	public Engine()
	{
		createJavaScriptEngine();
		loadAnnotationJavaScriptFiles();
	}

	/** @returns JavaScript Engine, allowing other objects to operate in a current JavaScript engine state */
	public ScriptEngine getEngine()
	{
		return javaScriptEngine;
	}

	/** loads a JavaScript file into the JavaScript engine and executes the script
	@param filename JavaScript filename represented by a string */
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

	/** Accepts a ScriptException and prints a user friendly JavaScript error*/
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

	private void createJavaScriptEngine()
	{
		scriptEngineManager = new ScriptEngineManager();
		javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
	}

	private void loadAnnotationJavaScriptFiles()
	{
		loadFile("scripts/jsa.js");
	}
}
