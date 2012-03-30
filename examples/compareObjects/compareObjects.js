/* returns an object that contains object properties that are missing from the second argument when compared to the first argument */
function compareObject(masterObject, overlayObject)
{
	if (typeof masterObject !== "object" || typeof masterObject !== "object")
	{
		throw new TypeError("arguments must be objects");
	}

	var keysMissingFromOverlayObject = {}

	for (item in masterObject)
	{
		if (overlayObject[item] === undefined)
		{
			keysMissingFromOverlayObject[item] = null;
		}
	}
	return keysMissingFromOverlayObject;
}
function printObject(obj)
{
	if (typeof obj !== "object")
	{
		throw new TypeError("argument must be an object");
	}

	for (item in obj)
	{
		if (item !== undefined && obj[item] !== undefined)
		{
			print(item + " : " + obj[item]);
		}
	}
}

