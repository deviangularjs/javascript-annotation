unitTest = {}

unitTest.pass = function()
{
	print("pass");
}

unitTest.fail = function(errorString)
{
	print("fail: " + errorString);
}

unitTest.assertEquals = function assertEquals(expression1, expression2)
{
	if (typeof expression1 !== "object")
	{
		assertScalar();
	}
	else
	{
		assertComposite();
	}

	function assertScalar()
	{
		if (expression1 == expression2)
		{
			unitTest.pass();
		}
		else
		{
			unitTest.fail("expected " + expression1 + " got " + expression2);
		}
	}

	function assertComposite()
	{
		if (typeof expression1 !== typeof expression2)
		{
			unitTest.fail();
			return false;
		}

		if (expression1.length !== expression2.length)
		{
			unitTest.fail();
			return false;
		}

		for (var item in expression1)
		{
			if (expression1[item] !== expression2[item])
			{
				unitTest.fail();
				return false;
			}
		}
	}
}

unitTest.assertType = function(expression1, expression2)
{
	if (typeof expression1 === typeof expression2)
	{
		unitTest.pass();
	}
	else
	{
		unitTest.fail("expected type " + typeof expression1 + " got " + typeof expression2);
	}
}

unitTest.assertError = function(expression1)
{
	try
	{
		expression1();
	}
	catch(error)
	{
		unitTest.pass();
		return true;
	}
	unitTest.fail("expected error");
}

