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

	public static void main(String[] args) throws Exception
	{

		Engine engine = new Engine();
		for (String currentArg : args)
		{
			engine.loadFile(currentArg);
			engine.parseFile(currentArg);
		}
	}

	public Engine()
	{
		scriptEngineManager = new ScriptEngineManager();
		javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
	}

	public ScriptEngine getEngine()
	{
		return javaScriptEngine;
	}

	public void loadFile(String filename) throws Exception
	{
		javaScriptEngine.eval(new FileReader(filename));
	}

	private void parseFile(String filename) throws Exception 
	{
		BufferedReader in;
		in = new BufferedReader(new FileReader(filename));

		while ((currentLine = in.readLine()) != null)
		{
			for (Command command : commandList)
			{
				if (this.containsAnnotationToken() && currentLine.contains(command.getCommand()))
				{
					String args = this.parseArguments(command.getCommand());
					command.execute(this.getEngine(), args);
				}
			}
		}

		in.close();
	}

	private boolean containsAnnotationToken()
	{
		if (currentLine.contains(this.annotationToken))
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
		String regularExpression = ".*" + this.annotationToken + "\\s*" + command + "\\s*(.*)";
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
