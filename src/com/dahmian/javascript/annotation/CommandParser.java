package com.dahmian.javascript.annotation;

import java.io.*;
import javax.script.*;

public class CommandParser
{
	protected Command[] commandList = {new TimeStampCommand(), new LoadCommand(), new ExecuteCommand(), new SaveCommand()};
	private String currentLine = "";
	private Engine javaScriptEngine;
	
	public CommandParser(File scriptFile, Engine javaScriptEngine)
	{
		this.javaScriptEngine = javaScriptEngine;
		parseScript(scriptFile);
	}

	private void parseScript(File scriptFile)
	{
		JavaScriptFile javaScriptFile = new JavaScriptFile(scriptFile);
		for (Command command : commandList)
		{
			javaScriptFile = command.execute(javaScriptFile);
		}
		javaScriptEngine.eval(javaScriptFile.getString());
	}

}
