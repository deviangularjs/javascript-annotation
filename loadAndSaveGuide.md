# How to load and save a local file #
  * In this guide, we are assuming that you are working directly in your home directory and have Java installed
  * Download the latest jsa jar: http://code.google.com/p/javascript-annotation/downloads/list and save it to your home directory
  * Create a empty JavaScript named loadSaveTest.js in your home directory.
  * Create a second JavaScript file in your home directory, localFile.js, put the following code into it
```
//This comment is located inside localFile.js
```
  * Edit loadSaveTest.js to have the following code
```
//This comment is located inside loadSaveTest.js
//@load localFile.js
//@save savedFile.js
```
  * Run this command (update the jar file name as appropriate): "java -jar jsa-0.7.jar loadSaveTest.js"
  * open savedFile.js

# How to load a remote file #
  * edit loadSaveTest.js to have the following code
```
//This comment is located inside loadSaveTest.js
//@load localFile.js
//@load http://www.dahmian.com/example.js
//@save savedFile.js
```
  * Run this command (update the jar file name as appropriate): "java -jar jsa-0.7.jar loadSaveTest.js"
  * open savedFile.js