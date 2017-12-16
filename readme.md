This is the a simple project I had to complete for an interview.

# The Challenge
You're given the task of writing a simple console version of a drawing program. In a nutshell, the program should work as follows:
1. create a new canvas
2. start drawing on the canvas by issuing various commands
3. quit

At the moment, the program should support the following commands:
- __C w h__ Should create a new canvas of width w and height h.
- __L x1 y1 x2 y2__ Should create a new line from _(x1,y1)_ to _(x2,y2)_. Currently only horizontal or vertical lines are - supported. Horizontal and vertical lines will be drawn using the 'x' character.
- __R x1 y1 x2 y2__ Should create a new rectangle, whose upper left corner is _(x1,y1)_ and lower right corner is _(x2,y2)_. Horizontal and vertical lines will be drawn using the 'x' character.
- __B x y c__ Should fill the entire area connected to (x,y) with "colour" c. The behaviour of this is the same as that of the "bucket fill" tool in paint programs.
- __Q__ Should quit the program.

Below is a sample run of the program. User input is prefixed with `enter command:`.
```
enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 16 1 20 3
----------------------
|               xxxxx|
|xxxxxx         x   x|
|     x         xxxxx|
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooooxxxxx|
|xxxxxxooooooooox   x|
|     xoooooooooxxxxx|
|     xoooooooooooooo|
----------------------
```

<br/>

# Solution

### Project Structure
The project has the following structure:

![UML Model](./doc/canvas_uml.jpg)


```
canvas
|-logs
|   |-canvas.log 
|-src
|  |-main
|  |  |-java
|  |      |-com... 				
|  |  |-resources
|  |      |-log4j.xml 
|  |-test
|      |-java
|          |-com...				
|-target
|   |-lib
|   |   |-.jar  
|   |-canvas.jar 
|-canvas.bat 
|-pom.xml  
|-readme.md
```

The project is composed by the following packages:
- __com.ea.examples.canvas__
    General classes. Here is the entry point of the application.
- __com.ea.examples.canvas.cmd__
	Commands package. Here can be found the commands supported by the application. The string inserted by the user during the interaction is converted in one of this commands. For creating a command we make use of a factory.
- __com.ea.examples.canvas.core__
	Core of the application.
- __com.ea.examples.exception__
	All checked exceptions thrown by the application.


Decided to use Maven for building and packaging the project.
External libraries used are:
- Junit, for testing the software
- Logging libraries, for logging the software.  Log4j logging will be outputed in the file _./logs/canvas.log_ so in this way we leave the console free for user interactions. 

In a real application would be a good idea to trace in the log file all the interactions present in the console so the developer can follow step-by-step the user flow until the application went in Exception.
This means that each `System.out.println()` will be followed by `logger.debug()`. For keeping the code cleaner I decided to print in the file only user input and eventual errors.


	

###	A Working build

A working build is present in the path ./target:
-	lib -> folder with external jars packaged by maven, needed for application to be run.
-	canvas.jar -> application packaged as a jar file.

Use ./canvas.bat for an easy and fast way to test the application. The .bat calls `java canvas.jar`

###	Tradeoffs/Decisions/Reflections
Actually we loop in the main() entrypoint, reading input strings which represent commands, until the user decide to insert Quit command for interrumpting the flow.
	For this simple specific I decided to go for java Scanner.
	A consideration I did was regards "org.apache.commons.cli" as a Command Line framework. For the actual specific apache.cli is "too much" and do not solve our problem.
	Where would be useful "org.apache.commons.cli" ? 
	In case we decide that the application can handle only one Command (no do/while loop) by time.
	In that case, for each desired command, we have to call the application like: 
		java canvas.jar C 20 4
		java canvas.jar L 1 1 8 1
		java canvas.jar R 1 1 8 4
	This is the case where "apache.cli" perfectly matches, but in this case our application must be changed in two different point:
		1. Create a batch for user interactions and call the canvas.jar depending on the user input. For each Command a new call to canvas.jar.
		2. Make the Canvas preserving the state from one call to another and make the access Singleton (only one instance of the Canvas shared between different users)
	Not a winning solution, but we can discuss if this an explicit client request.
	Other reflection is if the client need only one session to be present (single Canvas shared between different users) or for each user create a new Canvas(actual solution)?
	
�	Bucket filler "algorithm"
	- The approach used for filling the area is as follows:
		1. Call the method which fill only one pixel (if free)
		2. Call recursively same method for the 4 adjacent pixels: x+1, y; x, y+1; x-1, y; x, y-1;
		3. Method exit when finds borders or already filled pixel.
		
	- Complexity of the algorithm is: NumberOfFreePixels x 4.
	So if we have an empty Canvas[20x5] the filler will make 400 calls, so 400 access to the matrix[][].
	If in the canvas are present Lines and Rectangles, the number of free pixels will be fewer, so the calls will be < 400.
	Number of updates to the matrix[][] is equal to the colored pixel.
	

4.	Tests

Used junit for testing the functionalities of the canvas.
"TDD" approach was followed for implementing the solution.
This means that unit tests were not created at the end, but during all the development phase.
For each functionality is present a different file:
�	CommandBucketFillTest.java -> Test cases for BucketFiller command.
�	CommandLineTest.java -> Test cases for drawing a line command.
�	CommandRectangleTest.java -> Test cases for drawing a rectangle command.

Unit tests are divided in three categories
	� Happy Paths - testHP_nomeTest wich are the tests that has to succeed. Usually we assert at the end of the method what we are expecting
	� Case Limit  - testCL_nomeTest wich are the tests that test a Limit Case. Example, x, y are 0 ore same as width/height. 
					Usually we assert what we are expecting or pay attention of eventual exceptions
	� Exceptions  - testEX_nomeTest wich are the tests that test exception cases. In this cases we define the exception we are waiting to be thrown.

In case of new enhancements or different implementation for the Canvas.java the unit tests will acts as Integration Tests. 
Before committing new changes to repository everything must be "green" :) 


5.	Timeline

Total time spend for the release 13 hours subdivided as follows:
	9 Hours development + Unit tests  (3 days from 3 hours each)
	2 Hours testing User Interface canvas.jar  / Bug fixing
	2 Hours documentation