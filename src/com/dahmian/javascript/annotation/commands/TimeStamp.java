package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;
import java.util.Date;

public class TimeStamp extends Command
{
	public TimeStamp()
	{
		setCommand("timestamp");
	}

	public JavaScriptFile execute(JavaScriptFile script)
	{
		ArrayList<String> scriptArray = script.getFile();
		ArrayList<String> modifiedScriptArray = new ArrayList<String>();
		for (String currentLine : scriptArray)
		{
			if (hasToken(currentLine))
			{
				String date = (new Date()).toString();
				currentLine = Annotation.removeAnnotationToken(currentLine);
				currentLine = currentLine.replace(this.getCommand(), date);
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
