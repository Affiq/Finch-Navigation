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

