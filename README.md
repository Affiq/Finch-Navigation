# Finch-Navigation
A program where a user can control a Finch Robot through a command line interface. Implemented using Java, Finch library for movement and Swing library for UI. Upon execution, the user should be presented with a user interface where they can type their commands. There is a focus on modularity, allowing developers to easily create more commands and the program will automatically parse it depending upon the parameter types.

<h2> Usage </h2>
<p> Commands are executed using single letters, followed by the parameters required, such as F 10 5 to make the Finch go forward at speed 10 for 5 seconds. Below outlines the list of movement commands, where X, Y and Z represent parameters: </p>
<li> <b> F x y</b> - Moves the finch forward at speed x for y seconds. i.e. <b> F 5 10</b></li>
