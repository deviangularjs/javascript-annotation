jsa = {};
jsa.assert = {};

jsa.assert.pass = function()
{
	print("pass");
}

jsa.assert.fail = function()
{
	print("fail");
}

jsa.assert.sameType = function(expression1, expression2)
{
	if (typeof expression1 === typeof expression2)
	{
		jsa.assert.pass();
	}
	else
	{
		jsa.assert.fail("expected type " + typeof expression1 + " got " + typeof expression2);
	}
}
