package com.dahmian.javascript.annotation;

import java.util.regex.*;

public class Annotation 
{
	static private String annotationToken = "@";

	static boolean containsAnnotationToken(String currentLine)
	{
		if (currentLine.contains(annotationToken))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	static String getAnnotationToken()
	{
		return annotationToken;
	}

	static String removeAnnotationToken(String line)
	{
		line = line.replaceFirst("//", "");
		line = line.replaceFirst(Annotation.getAnnotationToken(), "");
		return line;
	}
}
