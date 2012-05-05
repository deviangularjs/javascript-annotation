package com.dahmian.javascript.annotation;

import java.io.*;
import javax.script.*;

public class CommandParser
{
	protected Command[] commandList = {
		new RunFunction("assertTrue", "jsa.assert.isTrue"),
		new RunFunction("assertFalse", "jsa.assert.isFalse"),
		new RunFunction("assertUndefined", "jsa.assert.isUndefined"),
		new RunFunction("assertNotUndefined", "jsa.assert.isNotUndefined"),
		new RunFunction("assertNull", "jsa.assert.isNull"),
		new RunFunction("assertNotNull", "jsa.assert.isNotNull"),
		new RunFunction("assertString", "jsa.assert.isString"),
		new RunFunction("assertNumber", "jsa.assert.isNumber"),
		new RunFunction("assertInteger", "jsa.assert.isInteger"),
		new RunFunction("assertArray", "jsa.assert.isArray"),
		new RunFunction("assertFunction", "jsa.assert.isFunction"),
		new TimeStamp(),
		new Load(),
		new Execute(),
		new Save()};
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
