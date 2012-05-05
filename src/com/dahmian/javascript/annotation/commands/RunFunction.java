package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;

public class RunFunction extends Command
{
	private String functionName;

	public RunFunction(String assertToken, String functionName)
	{
		this.functionName = functionName;
		setCommand(assertToken);
	}

	public JavaScriptFile execute(JavaScriptFile script)
	{
		ArrayList<String> scriptArray = script.getFile();
		ArrayList<String> modifiedScriptArray = new ArrayList<String>();
		for (String currentLine : scriptArray)
		{
			if (hasToken(currentLine))
			{
				currentLine = Annotation.removeAnnotationToken(currentLine);
				currentLine = removeCommand(currentLine);
				String[] arguments = getArguments(currentLine);
				modifiedScriptArray.add(functionStringBuilder(this.functionName, arguments));
			}
			else
			{
				modifiedScriptArray.add(currentLine);
			}
		}
		JavaScriptFile newFile = new JavaScriptFile(modifiedScriptArray);
		return newFile;
	}
}
