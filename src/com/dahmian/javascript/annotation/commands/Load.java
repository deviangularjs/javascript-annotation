package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;
import java.util.Date;
import java.io.*;
import java.net.*;

public class Load extends Command
{
	public Load()
	{
		setCommand("load");
	}

	public JavaScriptFile execute(JavaScriptFile script)
	{
		ArrayList<String> scriptArray = script.getFile();
		ArrayList<String> modifiedScriptArray = new ArrayList<String>();
		for (String currentLine : scriptArray)
		{
			if (hasToken(currentLine) && !hasUrl(currentLine))
			{
				loadFiles(modifiedScriptArray, currentLine);
			}
			else if (hasToken(currentLine) && hasUrl(currentLine))
			{
				loadFileFromUrl(modifiedScriptArray, currentLine);
			}
			else
			{
				modifiedScriptArray.add(currentLine);
			}
		}
		JavaScriptFile newFile = new JavaScriptFile(modifiedScriptArray);
		return newFile;
	}	

	private void loadFiles(ArrayList script, String currentLine)
	{
		String[] arguments = getArguments(currentLine);
		for (String currentArgument : arguments)
		{
			String file = loadFileToString(currentArgument);
			script.add(file);
		}
	}

	private String loadFileToString(String filename)
	{
		File absoluteFile = WorkingDirectory.getRelativeFile(filename);
		JavaScriptFile file = new JavaScriptFile(absoluteFile);
		return file.getString();
	}

	private boolean hasUrl(String currentLine)
	{
		if (currentLine.contains("http") && currentLine.contains(":") && currentLine.contains("/"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void loadFileFromUrl(ArrayList script, String currentLine)
	{
		String[] arguments = getArguments(currentLine);
		String urlString = arguments[0];
		String responseBody = loadUrl(urlString);
		script.add(responseBody);
	}

	private String loadUrl(String urlString)
	{
		try
		{
			InputStream response = new URL(urlString).openStream();
			String responseBody = convertStreamToString(response);
			return responseBody;
		}
		catch (IOException exception)
		{
			System.out.println("failed to load " + urlString);
			System.exit(1);
			return "";
		}
	}

	private String convertStreamToString(java.io.InputStream is)
	{
		try
		{
			return new java.util.Scanner(is).useDelimiter("\\A").next();
		}
		catch (java.util.NoSuchElementException e)
		{
			return "";
		}
	}
}
