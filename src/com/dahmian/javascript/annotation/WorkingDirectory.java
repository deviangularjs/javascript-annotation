package com.dahmian.javascript.annotation;

import java.io.*;

public class WorkingDirectory
{
	static File directory;

	public static void setFile(String file)
	{
		File relativeFile = new File(file);
		File absoluteFile = relativeFile.getAbsoluteFile();
		File absoluteParent = absoluteFile.getParentFile();
		System.out.println(absoluteParent.getPath());
		directory = absoluteParent;
	}

	public static File getFile()
	{
		return directory;
	}
}
