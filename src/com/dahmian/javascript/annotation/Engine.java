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

	public Engine(String[] filenames)
	{
		this();
		for (String currentFile : filenames)
		{
			loadScript(currentFile);
		}
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
			Error.printScriptError(exception);
		}
		catch (FileNotFoundException exception)
		{
			Error.printFileNotFound();
		}
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
