package com.dahmian.javascript.annotation;

import java.util.regex.*;

public class Annotation 
{
	static private String annotationToken = "@jsa";

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

	static String parseArguments(String currentLine, String command)
	{
		String args = "";
		String regularExpression = ".*" + annotationToken + "\\s*" + command + "\\s*(.*)";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(currentLine);
		if (matcher.matches())
		{
			args = matcher.group(1);
		}
		else
		{
			args = "FAIL";
		}
		return args;
	}
}
