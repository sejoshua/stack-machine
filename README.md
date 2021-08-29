# Stack Machine

### To Play with the Code
#### To build the solution
Run the command below from the project directory
```shell
$ ./gradlew clean build shadowJar
```
Or run this command on Windows
```shell
$ gradle.bat clean build shadowJar
```

#### To run the code
After building the project, run from the project directory
```shell
$ java -jar build/libs/stack-machine-1.0-SNAPSHOT-all.jar
****************************************
*     Welcome to the Stack Machine     *
****************************************
Usage: COMMAND <operand>

"COMMAND" options include:
	PUSH <xyx> or <xyz>:
	       where <xyz> is a valid decimal number.
	       Pushes the numeric value <xyz> to the top of the stack.
	  POP: Removes the top element from the stack.
	CLEAR: Removes all elements from the stack.
	  ADD: Adds the top 2 elements on the stack and pushes the result back to the stack.
	  MUL: Multiplies the top 2 elements on the stack and pushes the result back to the stack.
	  NEG: Negates the top element on the stack and pushes the result back to the stack.
	  INV: Inverts the top element on the stack and pushes the result back to the stack.
	 UNDO: The last instruction is undone leaving the stack in the same state as before that instruction.
	PRINT: Prints all elements that are currently on the stack.
	 QUIT: Exits the program.

Please input your instruction:
```

### Dive into the Code
[StackMachine](src/main/java/org/example/machine/StackMachine.java) is the key class of the application,
which 
* uses `Deque` as a stack,
* holds [StackRenderer](src/main/java/org/example/renderer/StackRenderer.java) implementation to display
the stack contents or errors,
* records executed commands in [CommandHistory](src/main/java/org/example/model/CommandHistory.java),
* leverages [ShutdownManager](src/main/java/org/example/shutdown/ShutdownManager.java) to take care of
exiting the application.

With this design, we can easily switch to different implementations of `StackRenderer` (such as a Java
Swing based renderer), to display the stack.