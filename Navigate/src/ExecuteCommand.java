import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ExecuteCommand {

	// In all movement commands FBRL, the time is multiplied by 1000
	// This is because the Finch setWheelVelocities function operate using milliseconds
	
	private static String[] SplittedStringArray;
	private static char CommandChar;
	
	public static void ExecuteCommandString(String CommandString, Tracelist TracelistParameter) throws IOException 
	{
		SetSplittedStringArray(CommandString);
		SetCommandChar();
		
		GlobalFinch.getFinch().setLED(255, 100, 0);	// Set finch to orange light	
		
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
				
			case 'L':
				ExecuteL();
				break;
				
			case 'T':
				ExecuteT(TracelistParameter);
				break;
				
			case 'W':
				FileHandler.SaveTracelist(TracelistParameter);
				break;
				
			case 'X':
				SelectFileForX();
				break;
				
			case 'D':
				TracelistParameter.DisplayTracelist();
				break;
				
			case 'P':
				Position.DisplayPosition();
				break;
				
			case 'H':
				CommandsArray.DisplayCommandsArray();
				break;
				 
			case 'Q':
				NavigateConsole.QuitProgram();
				break;
		}
		
	
		GlobalFinch.getFinch().setLED(0, 255, 0); // Set Finch back to green light			
		}
	
	public static void SetSplittedStringArray(String CommandString)
	{SplittedStringArray = CommandString.split(" ");}
	
	public static void SetCommandChar()
	{CommandChar = SplittedStringArray[0].toCharArray()[0];}
	
	public static void ExecuteF()
	{
		int Time = Integer.parseInt(SplittedStringArray[1]);
		int Speed = Integer.parseInt(SplittedStringArray[2]);
		GlobalFinch.getFinch().setWheelVelocities(Speed, Speed, Time* 1000);
		Position.CalculatePosition(Time, Speed);
		System.out.println("Finch has moved forward at "+Speed+" for "+Time+" seconds");
	}
	
	public static void ExecuteB()
	{
		int Time = Integer.parseInt(SplittedStringArray[1]) ;
		int Speed = Integer.parseInt(SplittedStringArray[2]);
		GlobalFinch.getFinch().setWheelVelocities(-Speed, -Speed, Time* 1000);
		Position.CalculatePosition(Time, -Speed); // CalculatePosition function will use negative speed to indicate backward movement.
		System.out.println("Finch has moved backward at "+Speed+" for "+Time+" seconds");
	}
	
	// Finch will turn right, program will calculate change in direction
	// then move the finch forward before calculating the new position of the Finch
	public static void ExecuteR()
	{
		int Time = Integer.parseInt(SplittedStringArray[1]);
		int Speed = Integer.parseInt(SplittedStringArray[2]);
		
		GlobalFinch.RightTurn();
		Direction.ComputeDirection(CommandChar);
		
		if (Time-1 != 0)
		GlobalFinch.getFinch().setWheelVelocities(Speed, Speed, (Time-1)* 1000);
		
		GlobalFinch.getFinch().stopWheels();
		Position.CalculatePosition(Time-1, Speed); // Turning for 1 second is accounted for
		System.out.println("Finch has turned right at a speed of "+Speed+" for "+Time+" Seconds");
	}
	
	// Finch will turn left, program will calculate change in direction
	// then move the finch forward before calculating the new position of the Finch
	public static void ExecuteL()
	{
		int Time = Integer.parseInt(SplittedStringArray[1]);
		int Speed = Integer.parseInt(SplittedStringArray[2]);
		
		GlobalFinch.LeftTurn();
		Direction.ComputeDirection(CommandChar);
		
		if (Time-1 != 0)
		GlobalFinch.getFinch().setWheelVelocities(Speed, Speed, (Time-1)* 1000);
		
		GlobalFinch.getFinch().stopWheels();
		Position.CalculatePosition(Time-1, Speed); // Turning for 1 second is accounted for
		System.out.println("Finch has turned left at a speed of "+Speed+" for "+Time+" Seconds");
	}
	
	public static void ExecuteT(Tracelist TracelistParameter) throws IOException
	{
		int Index = TracelistParameter.GetTracelistLength() - 1; // Top of tracelist chosen as first index
		int ExecutedMovements = 0;
		int DesiredMovements = Integer.parseInt(SplittedStringArray[1]);
		String CurrentCommand;
		String[] TempSplittedStringArray;
		char CurrentCommandCharacter;
		
		// Loop used to iterate through the tracelist starting from the top, going down
		while (ExecutedMovements != DesiredMovements)
		{
			CurrentCommand = TracelistParameter.GetTracelistCommands().get(Index); // get command string from tracelist index
			TempSplittedStringArray = CurrentCommand.split(" ");
			CurrentCommandCharacter = TempSplittedStringArray[0].toCharArray()[0];
			
			// If the CurrentCommandCharacter is a movement character, 
			// then execute (or more accurately retrace) the command
			// and increment the ExecutedMovements counter
			switch (CurrentCommandCharacter)
			{
			case 'F':
			case 'B':
			case 'R':
			case 'L':
				ExecuteCommand.ExecuteCommandString(CurrentCommand, TracelistParameter);
				ExecutedMovements++;
			}
			Index--;
		}
		System.out.println("Trace command - Finch has executed previous "+DesiredMovements+" movements");
	}
	
	
	// This is the partial command for ExecuteX
	public static void SelectFileForX()
	{
		// If there are no files in the NavigateFolder (excluding NumOfSaves.txt)...
		if (FileHandler.GetArrayOfFileNames().length == 0) 
		{
			System.out.println("There are no files to select! Please save commands in the NavigateFolder.");
	 		GlobalFinch.InvalidInputFinchResponse();
		}
		
		else // then another file exists...
		{
			NavigateGUI.disableFrame(); // close the NavigateGUIFrame so user can't enter command
			SelectFileGUI SFGUI = new SelectFileGUI();
			javax.swing.SwingUtilities.invokeLater(SFGUI); // will open a select file GUI to select a file
		// The rest of the execute code will occur in the SelectFileConsole.java
		}
	}
	
}
