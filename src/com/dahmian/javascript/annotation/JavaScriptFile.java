package com.dahmian.javascript.annotation;

import java.io.*;
import java.util.*;

public class JavaScriptFile
{
	private ArrayList<String> fileArray;

	public JavaScriptFile(File file)
	{
		setFile(file);
	}

	public void setFile(File file)
	{
		BufferedReader reader;
		String currentLine;
		try
		{
			reader = new BufferedReader(new FileReader(file));

			while ((currentLine = reader.readLine()) != null)
			{
				fileArray.add(currentLine);
			}

			reader.close();
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

	public ArrayList<String> getFile()
	{
		return fileArray;
	}
}
