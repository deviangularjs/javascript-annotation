package com.dahmian.javascript.annotation;

import java.io.*;
import java.util.*;

public class JavaScriptFile
{
	private ArrayList<String> fileArray = new ArrayList<String>();

	public JavaScriptFile(File file)
	{
		setFile(file);
	}

	public JavaScriptFile(ArrayList<String> file)
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

	public void setFile(ArrayList<String> fileArray)
	{
		this.fileArray = fileArray;
	}

	public ArrayList<String> getFile()
	{
		return fileArray;
	}

	public void writeFile(String filename)
	{
		writeFile(new File(filename));
	}

	public void writeFile(File filename)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for (String currentLine : fileArray)
			{
				writer.write(currentLine);
				writer.newLine();
			}
			writer.flush();
		}
		catch (IOException exception)
		{
		}
	}

	public String getString()
	{
		String fileString = new String();
		String lineSeparator = System.getProperty("line.separator");
		for (String currentLine : fileArray)
		{
			fileString += currentLine + lineSeparator;
		}
		return fileString;
	}
}
