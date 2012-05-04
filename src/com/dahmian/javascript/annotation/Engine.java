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
		CommandParser commandParser = new CommandParser(scriptFile, this);
	}

	public void eval(File file)
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

	public void eval(String string)
	{
		try
		{
			javaScriptEngine.eval(string);
		}
		catch (ScriptException exception)
		{
			Error.printScriptError(exception);
		}
	}

	private void createJavaScriptEngine()
	{
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript");
	}


	private String convertStreamToString(InputStream is)
	{
		if (is != null)
		{
			Writer writer = null;
			try
			{
				writer = new StringWriter();
				char[] buffer = new char[1024];
				Reader reader = new BufferedReader(
				new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1)
				{
					writer.write(buffer, 0, n);
				}
				is.close();
			}
			catch (Exception e)
			{
			}
			return writer.toString();
		}
		else
		{        
			return "";
		}
	}


	private void loadAnnotationJavaScriptFiles()
	{
		InputStream in = getClass().getResourceAsStream("/scripts/jsa.js");
		String test = convertStreamToString(in);
		eval(test);
	}

	private void putFileNameIntoEngine(File scriptFile)
	{
		javaScriptEngine.put(ScriptEngine.FILENAME, scriptFile.getName());
	}
}
