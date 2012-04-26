package com.dahmian.javascript.annotation;

import java.io.*;
import javax.script.*;

public class CommandParser
{
	protected Command[] commandList = {new ExecuteCommand()};
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
					if (Annotation.containsAnnotationToken(currentLine) && currentLine.contains(command.getCommand()))
					{
						String args = Annotation.parseArguments(currentLine, command.getCommand());
						command.execute(javaScriptEngine, args);
					}
				}
			}

			in.close();
		}
		catch (FileNotFoundException exception)
		{
			Error.printFileNotFound();
		}
		catch (IOException exception)
		{
			Error.printFileNotReadable();
		}

	}

}
