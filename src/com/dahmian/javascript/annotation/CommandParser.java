package com.dahmian.javascript.annotation;

import java.util.*;
import java.io.*;
import javax.script.*;

public class CommandParser
{
	protected ArrayList<Command> commandList = new ArrayList<Command>();
	private String currentLine = "";
	private Engine javaScriptEngine;
	
	public CommandParser(File scriptFile, Engine javaScriptEngine)
	{
		this.javaScriptEngine = javaScriptEngine;
		populateAssertCommands();
		populateCommands();
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

	private void populateAssertCommands()
	{
		commandList.add(new RunFunction("assertTrue", "jsa.assert.isTrue"));
		commandList.add(new RunFunction("assertFalse", "jsa.assert.isFalse"));
		commandList.add(new RunFunction("assertEquals", "jsa.assert.equals"));
		commandList.add(new RunFunction("assertUndefined", "jsa.assert.isUndefined"));
		commandList.add(new RunFunction("assertNotUndefined", "jsa.assert.isNotUndefined"));
		commandList.add(new RunFunction("assertNull", "jsa.assert.isNull"));
		commandList.add(new RunFunction("assertNotNull", "jsa.assert.isNotNull"));
		commandList.add(new RunFunction("assertString", "jsa.assert.isString"));
		commandList.add(new RunFunction("assertNumber", "jsa.assert.isNumber"));
		commandList.add(new RunFunction("assertInteger", "jsa.assert.isInteger"));
		commandList.add(new RunFunction("assertArray", "jsa.assert.isArray"));
		commandList.add(new RunFunction("assertFunction", "jsa.assert.isFunction"));
		commandList.add(new RunFunction("assertError", "jsa.assert.error"));
	}

	private void populateCommands()
	{
		commandList.add(new TimeStamp());
		commandList.add(new Load());
		commandList.add(new Execute());
		commandList.add(new Save());
	}
}
