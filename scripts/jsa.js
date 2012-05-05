jsa = {};
jsa.assert = {};

jsa.assert.isTrue = function(expression1)
{
	jsa.assert.sameValue(expression1, true);
}

jsa.assert.isTruthy = function(expression1)
{
	jsa.assert.similarValue(expression1, true);
}

jsa.assert.isFalse = function(expression1)
{
	jsa.assert.sameValue(expression1, false);
}

jsa.assert.isFalsy = function(expression1)
{
	jsa.assert.similarValue(expression1, false);
}

jsa.assert.sameValue = function(expression1, expression2)
{
	if(expression1 === expression2)
	{
		jsa.assert.pass();
	}
	else
	{
		jsa.assert.fail();
	}
}

jsa.assert.isFunction = function(expression1)
{
	jsa.assert.sameValue(typeof expression1, "function");
}

jsa.assert.isString = function(expression1)
{
	jsa.assert.sameValue(typeof expression1, "string");
}

jsa.assert.isNumber = function(expression1)
{
	jsa.assert.sameValue(typeof expression1, "number");
}

jsa.assert.isArray = function(expression1)
{
	var objectType = Object.prototype.toString.call(expression1);
	jsa.assert.sameValue(objectType, "[object Array]");
}

jsa.assert.similarValue = function(expression1, expression2)
{
	if(expression1 == expression2)
	{
		jsa.assert.pass();
	}
	else
	{
		jsa.assert.fail();
	}
}

jsa.assert.sameType = function(expression1, expression2)
{
	if(typeof expression1 === typeof expression2)
	{
		jsa.assert.pass();
	}
	else
	{
		jsa.assert.fail("expected type " + typeof expression1 + " got " + typeof expression2);
	}
}

jsa.assert.pass = function()
{
	jsa.assert.printMessage("pass");
}

jsa.assert.fail = function()
{
	jsa.assert.printMessage("fail");
	if (runningInJava)
	{
		java.lang.System.exit(1);
	}

	function runningInJava()
	{
		if(typeof java !== "undefined" && java.lang && java.lang.System)
		{
			return true;
		}
	}
}

jsa.assert.printMessage = function(message)
{
	if(typeof java !== "undefined")
	{
		print(message);
	}
	else if(typeof console !== "undefined" && console.log)
	{
		console.log(message);
	}
	else if(typeof alert !== "undefined")
	{
		alert(message);
	}
}
