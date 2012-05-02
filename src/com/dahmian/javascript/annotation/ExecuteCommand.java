package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;

public class ExecuteCommand extends Command
{
	public ExecuteCommand()
	{
		setCommand("execute");
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
