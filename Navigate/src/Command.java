
public class Command {
	private char CommandCharacter;
	private String[] ParameterTypes;
	private String CommandDescription;
	
	public Command (char CommandChar, String[] ParameterInputs, String CommandDesc)
	{
		CommandCharacter = CommandChar;
		ParameterTypes = ParameterInputs;
		CommandDescription = CommandDesc;
	}
	
	public char GetCommandCharacter()
	{return CommandCharacter;}
	
	public String[] GetParameterTypes()
	{return ParameterTypes;}
	
	// Displays the command character, the types of parameters and it's description
	// In the format 
	// CommandCharacter Types
	// CommandDescription
	public void DisplayCommand()
	{
		System.out.print(CommandCharacter);
		int Index = 0;
		int NumOfTypes = ParameterTypes.length;
		
		while ((Index == NumOfTypes) == false)
			{
			System.out.print(" " + ParameterTypes[Index]);
			Index++;
			}
		
		System.out.println("\n" + CommandDescription);
	}
	
	
}
