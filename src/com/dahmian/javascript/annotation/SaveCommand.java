package com.dahmian.javascript.annotation;

import java.util.*;
import javax.script.*;
import java.util.Date;
import java.io.*;

public class SaveCommand extends Command
{
	public SaveCommand()
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
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(filename));
			for (String line : modifiedScriptArray)
			{
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		}
		catch (FileNotFoundException exception)
		{
			Error.printFileNotFound();
		}
		catch (IOException exception)
		{
			Error.printFileNotReadable();
		}
	}
}
