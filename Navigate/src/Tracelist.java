import java.util.ArrayList;

public class Tracelist {

	private ArrayList<String> TracelistCommands;
	
	public Tracelist()
	{ TracelistCommands = new ArrayList<String>(); }
	
	public int GetTracelistLength()
	{ return TracelistCommands.size(); }
	
	public void AddToTracelist(String EnteredString)
	{ TracelistCommands.add(EnteredString); }
	 
	public ArrayList<String> GetTracelistCommands()
	{ return TracelistCommands; }
	
	
	// Display the contents of the tracelist line by line
	public void DisplayTracelist()
	{
		int TracelistLength = TracelistCommands.size();
		int Index = TracelistLength - 1;
		String CurrentCommand;
		
		System.out.println("Top of Tracelist");
		while ((Index < 0)== false) // while index is not negative
		{
			CurrentCommand = TracelistCommands.get(Index);
			System.out.println(CurrentCommand);
			Index--;
		}
		System.out.println("Bottom of Tracelist");
	}
	
	
	// Returns the number of movement commands currently in the tracelist
	// Mainly used for validation for the Trace command
	public int GetNumOfMovements()
	{
		int NumOfMovements = 0;
		int Index = 0;
		int TracelistLength = TracelistCommands.size();
		char CommandCharacter;
		String CurrentCommand;
		
		while ((Index == TracelistLength) == false)
			{
				CurrentCommand = TracelistCommands.get(Index);
				CommandCharacter = CurrentCommand.toCharArray()[0];
				switch (CommandCharacter) // All movement characters are hardcoded here - add here if new movements are added
				{
					case 'F':
					case 'B':
					case 'R':
					case 'L':
						NumOfMovements++;
				}
				Index++;
			}
		return NumOfMovements;
	}
}
