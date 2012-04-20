package com.dahmian.javascript.annotation;

import java.io.*;
import java.util.regex.*;
import javax.script.*;

public class Engine
{
	private ScriptEngine javaScriptEngine;

	/** Instantiates a JavaScript engine and loads common annotation JavaScript variables into the JavaScript engine environment.*/
	public Engine()
	{
		createJavaScriptEngine();
		loadAnnotationJavaScriptFiles();
	}

	/** loads a JavaScript file into the JavaScript engine and executes the script
	@param filename JavaScript filename represented by a string */
	public void loadScript(String filename)
	{
		File scriptFile = new File(filename);
		putFileNameIntoEngine(scriptFile);
		evalScriptFile(scriptFile);
		CommandParser commandParser = new CommandParser(scriptFile, javaScriptEngine);
	}

	private void evalScriptFile(File file)
	{
		try
		{
			javaScriptEngine.eval(new FileReader(file));
		}
		catch (ScriptException exception)
		{
			Engine.printScriptError(exception);
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("file not found!");
		}
	}

	/** Accepts a ScriptException and prints a user friendly JavaScript error*/
	static void printScriptError(ScriptException exception)
	{
		System.out.println("JavaScript error: " + exception.getMessage());
		System.exit(1);
	}


	private void createJavaScriptEngine()
	{
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
	}

	private void loadAnnotationJavaScriptFiles()
	{
		loadScript("scripts/jsa.js");
	}

	private void putFileNameIntoEngine(File scriptFile)
	{
		javaScriptEngine.put(ScriptEngine.FILENAME, scriptFile.getName());
	}
}
