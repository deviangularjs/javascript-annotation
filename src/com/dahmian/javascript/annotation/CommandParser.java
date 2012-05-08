package com.dahmian.javascript.annotation;

import java.util.*;
import java.io.*;
import javax.script.*;

public class CommandParser
{
	protected ArrayList<Command> commandList = new ArrayList<Command>();
	private String currentLine = "";
	private Engine javaScriptEngine;
	private boolean useAssertions = true;
	private boolean useSave = true;
	
	public CommandParser(Engine javaScriptEngine)
	{
		this.javaScriptEngine = javaScriptEngine;
	}

	public void setUseAssertions(boolean useAssertions)
	{
		this.useAssertions = useAssertions;
	}

	public void setUseSave(boolean useSave)
	{
		this.useSave = useSave;
	}

	public void parseScript(File scriptFile)
	{
		JavaScriptFile javaScriptFile = new JavaScriptFile(scriptFile);
		populateCommands();

		for (Command command : commandList)
		{
			javaScriptFile = command.execute(javaScriptFile);
		}
		javaScriptEngine.eval(javaScriptFile.getString());
	}

	private void populateCommands()
	{
		populateRegularCommands();
		if (useAssertions)
		{
			populateAssertCommands();
		}
		if (useSave)
		{
			populateSaveCommand();
		}
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
		commandList.add(new Execute());
	}

	private void populateRegularCommands()
	{
		commandList.add(new TimeStamp());
		commandList.add(new Load());
	}

	private void populateSaveCommand()
	{
		commandList.add(new Save());
	}
}
