package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;
import java.util.Date;
import java.io.*;

public class Load extends Command
{
	public Load()
	{
		setCommand("load");
	}

	public JavaScriptFile execute(JavaScriptFile script)
	{
		ArrayList<String> scriptArray = script.getFile();
		ArrayList<String> modifiedScriptArray = new ArrayList<String>();
		for (String currentLine : scriptArray)
		{
			if (hasToken(currentLine))
			{
				String[] arguments = getArguments(currentLine);
				for (String currentArgument : arguments)
				{
					modifiedScriptArray.add(loadFile(currentArgument));
				}
			}
			else
			{
				modifiedScriptArray.add(currentLine);
			}
		}
		JavaScriptFile newFile = new JavaScriptFile(modifiedScriptArray);
		return newFile;
	}	

	private String loadFile(String filename)
	{
		File absoluteFile = WorkingDirectory.getRelativeFile(filename);
		JavaScriptFile file = new JavaScriptFile(absoluteFile);
		return file.getString();
	}
}
