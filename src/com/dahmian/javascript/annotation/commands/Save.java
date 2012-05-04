package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;
import java.util.Date;
import java.io.*;

public class Save extends Command
{
	public Save()
	{
		setCommand("save");
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
				writeFile(modifiedScriptArray, arguments[0]);
			}
			else
			{
				modifiedScriptArray.add(currentLine);
			}
		}
		JavaScriptFile newFile = new JavaScriptFile(modifiedScriptArray);
		return newFile;
	}	

	private void writeFile(ArrayList<String> modifiedScriptArray, String filename)
	{
		File absoluteFile = WorkingDirectory.getRelativeFile(filename);
		JavaScriptFile file = new JavaScriptFile(modifiedScriptArray);
		file.writeFile(absoluteFile);
	}
}
