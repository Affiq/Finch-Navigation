import java.util.ArrayList;

// CommandsArray is used primarily for syntax validation
public class CommandsArray {
	
	// Will hold all the recognised commands
	private final static ArrayList<Command> RecognisedCommandsArray = new ArrayList<Command>();
	
	
	public CommandsArray() // Constructor will automatically initialise array into default hard-coded values
	{InitializeCommandsArray();}
	
	
	// Function to check if a passed character is a recognised command
	public static boolean IsCharacterInCommandsArray(char CharacterToSearch)
	{
		boolean CharacterFoundFlag = false;
		int Index = 0;
		int CommandsArrayLength = RecognisedCommandsArray.size();
		
		// Jump out of the loop if either the flag has been toggled or if the max index has been reached
		while (((Index == CommandsArrayLength) || (CharacterFoundFlag == true)) == false) 
		{
			if (RecognisedCommandsArray.get(Index).GetCommandCharacter() == CharacterToSearch)
				{CharacterFoundFlag = true;}
			else
				{Index++;}
		}
		return CharacterFoundFlag;
	}
	
	
	public static int GetNumOfParameters(char CharacterToSearch)
	{return GetParameterTypes(CharacterToSearch).length;}
	
	
	public static String[] GetParameterTypes(char CharacterToSearch)
	{
		int Index = 0;
		
		// Jump out of the loop once you find the index for the character to be searched
		while ((RecognisedCommandsArray.get(Index).GetCommandCharacter() == CharacterToSearch) == false) 
		{
			Index++;
		}
		String[] ParameterTypes = RecognisedCommandsArray.get(Index).GetParameterTypes();
		return ParameterTypes;
	}
	
	
	public static void DisplayCommandsArray()
	{
		int CommandsArrayLength = RecognisedCommandsArray.size();
		int Index = 0;
		while ((Index == CommandsArrayLength) == false)
			{
				System.out.println();
				RecognisedCommandsArray.get(Index).DisplayCommand();
				Index++;
			}
			
	}
	
	
	public static void InitializeCommandsArray() // 
	{
		 //RecognisedCommandsArray
		char CurrentCommandChar;
		String[] CurrentParameterTypes;
		String CurrentCommandDesc;
		Command CurrentCommand;

		// Hard-coding the recognised commands - their characters, their parameters and their descriptions
		
		// F Command
		CurrentCommandChar = 'F';
		CurrentParameterTypes = new String[]  {"Int", "Int"};
		CurrentCommandDesc = "Forward - For x seconds, moves finch forward at Y speed";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// B Command
		CurrentCommandChar = 'B';
		CurrentParameterTypes = new String[]  {"Int", "Int"};
		CurrentCommandDesc = "Backwards - For x seconds, moves finch backwards at Y speed";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// R Command
		CurrentCommandChar = 'R';
		CurrentParameterTypes = new String[]  {"Int", "Int"};
		CurrentCommandDesc = "Right - Will turn the finch right for x seconds, at a speed of Y";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// L Command
		CurrentCommandChar = 'L';
		CurrentParameterTypes = new String[]  {"Int", "Int"};
		CurrentCommandDesc = "Left - Will turn the finch left for x seconds, at a speed of Y";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// T Command
		CurrentCommandChar = 'T';
		CurrentParameterTypes = new String[]  {"Int"};
		CurrentCommandDesc = "Trace - Will trace x previous movements of the Finch (not including T commands)";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// W Command
		CurrentCommandChar = 'W';
		CurrentParameterTypes = new String[]  {};
		CurrentCommandDesc = "Write - Will save the Finch file";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// X Command
		CurrentCommandChar = 'X';
		CurrentParameterTypes = new String[]  {};
		CurrentCommandDesc = "Execute - Will execute all movements in a saved file with at least 3 movements";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);

		// Q Command
		CurrentCommandChar = 'Q';
		CurrentParameterTypes = new String[]  {};
		CurrentCommandDesc = "Quit - Will quit the program";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// D Command
		CurrentCommandChar = 'D';
		CurrentParameterTypes = new String[]  {};
		CurrentCommandDesc = "Display - Will display all commands currently in the tracelist";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);
		
		// P Command
		CurrentCommandChar = 'P';
		CurrentParameterTypes = new String[]  {};
		CurrentCommandDesc = "Position - Will display the estimated position, total distance travelled and displacement";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);

		// H Command
		CurrentCommandChar = 'H';
		CurrentParameterTypes = new String[]  {};
		CurrentCommandDesc = "Help - Will display all commands";
		CurrentCommand = new Command(CurrentCommandChar, CurrentParameterTypes, CurrentCommandDesc);
		RecognisedCommandsArray.add(CurrentCommand);		
	}
	
	
}
