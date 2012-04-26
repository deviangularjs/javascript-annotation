package com.dahmian.javascript.annotation;

import javax.script.*;

public class Error
{
	/** Accepts a ScriptException and prints a user friendly JavaScript error*/
	static void printScriptError(ScriptException exception)
	{
		System.out.println("JavaScript error: " + exception.getMessage());
		System.exit(1);
	}

	static void printFileNotFound()
	{
		System.out.println("file not found!");
	}

	static void printFileNotReadable()
	{
		System.out.println("unable to read file");
	}
}
