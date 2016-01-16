# How to insert a timestamp #
  * In this guide, we are assuming that you are working directly in your home directory and have Java installed
  * Download the latest jsa jar: http://code.google.com/p/javascript-annotation/downloads/list and save it to your home directory
  * Create a empty JavaScript named timestamp.js in your home directory.
  * Edit timestamp.js to have the following code
```
// //@timestamp
//@save savedFile.js
```
  * Run this command (update the jar file name as appropriate): `java -jar jsa-0.7.jar timestamp.js`
  * open savedFile.js