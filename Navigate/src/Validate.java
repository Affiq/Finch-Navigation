
public class Validate {

	private static boolean ValidFlag;
	private static String[] SplittedStringArray;
	
	// splits a string into an array of substrings
	public static void SetSplittedStringArray(String StringParameter)
		{SplittedStringArray = StringParameter.split(" ");}
	
	
	// If statements to avoid unnecessary computation - also avoids errors
	public static boolean ValidateString(String StringParameter, Tracelist TracelistParameter)
	{
		ValidFlag = true;
		SetSplittedStringArray(StringParameter);
		
		// 1st validation - Check first substring is one character
		CheckFirstElementIsOneCharacter();
		
		// 2nd validation - Check if the first substring is a recognised command
		if (ValidFlag == true)
			{ CheckFirstElementIsCommandCharacter(); } 
		
		// 3rd validation - Check there are the correct number of substrings
		if (ValidFlag == true)
			{ CheckCorrectNumOfParameters(); }
		
		// 4th validation - Check the types of the substrings correspond to the command character
		if (ValidFlag == true)
			{ CheckParameterTypes(); }
		
		// 5th validation - Semantic checks such as out of bound values
		if (ValidFlag == true)
			{ CheckParameterSemantics(TracelistParameter); }

		return ValidFlag;
	}
	
	
	
	public static void CheckFirstElementIsOneCharacter()
	{
		String FirstElement = SplittedStringArray[0];
		int ElementLength = FirstElement.length();
		
		if (ElementLength != 1)
		{
			System.out.println("Invalid Command entered. Commands must be a single character");
			ValidFlag = false;	
		}
	}
	
	
	
	public static void CheckFirstElementIsCommandCharacter()
	{
		boolean RecognisedCommandFlag = false;
		char CommandCharacter = SplittedStringArray[0].toCharArray()[0]; // convert string of one character to char
		RecognisedCommandFlag = CommandsArray.IsCharacterInCommandsArray(CommandCharacter);
		if (RecognisedCommandFlag == false)
		{
			System.out.println("Invalid command character entered.");
			ValidFlag = false;
		}
	}
	
	
	
	public static void CheckCorrectNumOfParameters()
	{
		char CommandCharacter = SplittedStringArray[0].toCharArray()[0]; // convert string of one character to char
		int NumOfParameterElements = SplittedStringArray.length - 1; // number of entered parameters in validate
		int ExpectedNumber = CommandsArray.GetNumOfParameters(CommandCharacter); // expected number of parameters of command character
		if (NumOfParameterElements != ExpectedNumber)
		{
			System.out.println("Incorrect number of parameters entered. Command "+CommandCharacter+" requires "+ExpectedNumber+" parameters");
			ValidFlag = false;
		}
	}
	
	
	
	public static void CheckParameterTypes()
	{
		char CommandCharacter = SplittedStringArray[0].toCharArray()[0]; // convert string of one character to char
		int NumOfParameterElements = SplittedStringArray.length - 1;
		int Index = 0;
		String[] ExpectedTypes = CommandsArray.GetParameterTypes(CommandCharacter);
		
		while (Index != NumOfParameterElements)
		{
			if (ExpectedTypes[Index].equals("Int"))
			{
				IntTypeCheck(SplittedStringArray[Index+1]);
			}
			else if (ExpectedTypes[Index].equals("String"))
			{
				StringTypeCheck(SplittedStringArray[Index+1]);
			}
			Index++;
		}
		
		if (ValidFlag == false)
		{
			System.out.print("Wrong parameters entered. Command "+CommandCharacter+" expects types:");
			Index = 0;
			while (Index != NumOfParameterElements) // loop to print out expected parameter types error
			{
				System.out.print(" "+ExpectedTypes[Index]);
				Index++;
			}
			
		}
		
	}
	
	
	
	public static void IntTypeCheck(String CheckedString) // changed the implementation to include array of characters
	{
		 boolean IntegerFlag = true;
		 int Index = 0;
		 int StringLength = CheckedString.length();
		 char[] CheckedStringArray = CheckedString.toCharArray();
		 
		 while ((Index < StringLength) && (IntegerFlag == true))
			 {
			 	if (Character.isDigit(CheckedStringArray[Index])==false) // checks if char is a digit
			 	{
			 		IntegerFlag = false;
			 	}
			 	Index++;
			 }
		 
		 if (IntegerFlag == false)
			 {
			 	System.out.println("Integer expected as parameter");
			 	ValidFlag = false;
			 } 
	}
	
	
	
	public static void StringTypeCheck(String CheckedString)
	{
		 boolean AlphabeticCharacterFlag = true;
		 int Index = 0;
		 int StringLength = CheckedString.length();
		 char[] CheckedStringArray = CheckedString.toCharArray();
		 
		 while ((Index < StringLength) && (AlphabeticCharacterFlag == true))
			 {
			 	if (Character.isLetter(CheckedStringArray[Index])==false) // checks if char is a letter
			 	{
			 		AlphabeticCharacterFlag = false;
			 	}
			 	Index++;
			 }
		 
		 if (AlphabeticCharacterFlag == false)
			 {
			 	System.out.println("Alphabetic string expected as parameter");
			 	ValidFlag = false;
			 }
		 
	}
	
	
	
	public static void CheckParameterSemantics(Tracelist CheckedTracelist)
	{
		char CommandCharacter = SplittedStringArray[0].toCharArray()[0];
		switch (CommandCharacter)
		{
			// All movement commands need the duration and speed checked
			case 'F':
			case 'B':
			case 'R':
			case 'L':
				int MoveDuration = Integer.parseInt(SplittedStringArray[1]);
				int WheelSpeed = Integer.parseInt(SplittedStringArray[2]);
				CheckMoveDurationBoundary(MoveDuration);
				CheckWheelSpeedBoundary(WheelSpeed);
				break;
				
			// Trace command check needed
			case 'T':
				int EnteredTraceValue = Integer.parseInt(SplittedStringArray[1]);
				CheckNumOfMovements(EnteredTraceValue, CheckedTracelist);
				break;
		}
	}
	
	// Used by the CheckWheelSpeedBoundary and CheckMoveDurationBoundary functions
	// Checks if a CheckedValue is above the lowerbound and less than or equal to the upperbound
	public static void CheckIntBoundary(int LowerBound, int UpperBound, int CheckedValue)
	{
		if (((CheckedValue > LowerBound)&&(CheckedValue <= UpperBound)) == false)
		{
			System.out.println("Parameter "+CheckedValue+" not valid. Must be between "+LowerBound+" and "+UpperBound);
			ValidFlag = false;
		}
	}
	
	// Checks if a value is in between 0 exclusive or 200 inclusive
	public static void CheckWheelSpeedBoundary(int CheckedValue)
	{	CheckIntBoundary(0,200, CheckedValue);	}
	
	// Checks if a value is in between 0 exclusive or 6 inclusive
	public static void CheckMoveDurationBoundary(int CheckedValue)
	{	CheckIntBoundary(0,6, CheckedValue);	}
	
	
	// Checks if an input for retracing does not exceed number of movements
	// as well as 0 or negative integers
	public static void CheckNumOfMovements(int EnteredValue, Tracelist CheckedTracelist)
	{
		int NumOfMovements = CheckedTracelist.GetNumOfMovements();
		if (EnteredValue <= 0)
		{
			System.out.println("Entered value must be a positive non-zero integer");
			ValidFlag = false;
		}
		else if (EnteredValue > NumOfMovements)
		{
			System.out.println("Invalid number entered. Input exceeds number of movements inside the tracelist. There are currently "+NumOfMovements+" movements in the tracelist");
			ValidFlag = false;
		}
	}
	
}
