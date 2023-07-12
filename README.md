# Finch-Navigation
A program where a user can control a Finch Robot through a command line interface. Implemented using Java, Finch library for movement and Swing library for UI. Upon execution, the user should be presented with a user interface where they can type their commands. There is a focus on modularity, allowing developers to easily create more commands and the program will automatically parse it depending upon the parameter types.

<h2> General Usage </h2>
<p> Commands are executed using single letters, followed by the parameters required, such as F 10 5 to make the Finch go forward at speed 10 for 5 seconds. Below outlines the list of commands, where X, Y and Z represent parameters: </p>
<ul>
  <li> <b> F x y</b> - Moves the finch forward at speed x for y seconds. i.e. <b> F 5 10</b></li>
  <li> <b> B x y</b> - Moves the finch backward at speed x for y seconds. i.e. <b> B 2 10</b></li>
  <li> <b> L x y</b> - Turns the finch left at wheelspeed x for y seconds. i.e. <b> L 5 2</b></li>
  <li> <b> R x y</b> - Turns the finch right at wheelspeed x for y seconds. i.e. <b> R 5 2</b></li>
  <li> <b> T x </b> - Repeats the last x steps performed by the finch. i.e. <b> T 4</b></li>
  <li> <b> W </b> - Saves the current finch movements to a file (with a date tag) i.e. <b> W</b></li>
  <li> <b> X </b> - Presents a list of saved Finch files, where a user can select and execute the steps in the saved file i.e. <b> X</b></li>
  <li> <b> D </b> - Displays the commands currently in the tracelist  <b> D </b></li>
  <li> <b> P </b> - Estimates finch position, distance and displacement <b> P</b></li>
  <li> <b> H </b> - Display all available commands for the Finch to execute i.e. <b> H</b></li>
</ul>

<h2> Adding a New Command </h2>
<p>The program comes with its own system of lexing and parsing the user input and matching the commands. See the CommandsArray.Java file. To create a new command, 3 values must be specified - the command character (the letter the user must type to activate the command), the parameter types (an array of strings such as {"Int","Int"} to specify the command takes two integer parameters), and the command description (shown to the user when they type the H command.) The following is the definition for the F command. </p>

```
// F Command
		CurrentCommandChar = 'F';
		CurrentParameterTypes = new String[]  {"Int", "Int"};
		CurrentCommandDesc = "Forward - For x seconds, moves finch forward at Y speed";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
```

<p> To define the functionality of the command, simply add a function in the ExecuteCommand.Java file, and add the function to the case statement for the appropriate letter. The following shows how the first few commands are implemented to allow execution of the Finch in the ExecuteCommand.Java file. </p>

```
		// Switch case observes which command should be executed
		switch (CommandChar)
		{
			case 'F':
				ExecuteF();
				break;
				
			case 'B':
				ExecuteB();
				break;
				
			case 'R':
				ExecuteR();
				break;
    ...
```
