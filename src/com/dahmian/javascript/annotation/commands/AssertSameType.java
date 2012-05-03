package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;

public class AssertSameType extends Command
{
	public AssertSameType()
	{
		setCommand("assertSameType");
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
				currentLine = "jsa.assert.sameType(" + arguments[0] + "," + arguments[1] + ");";
				modifiedScriptArray.add(currentLine);
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
