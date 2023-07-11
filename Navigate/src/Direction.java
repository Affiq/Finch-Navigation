
public class Direction {

	private static char Direction;
	// Can only be values N, E, S or W which correspond to North, East, South and West 
	
	public static void SetInitialDirection()
	{SetDirection('N');}
	
	public static void SetDirection(char DirectionValue)
	{Direction = DirectionValue;}
	
	public static char GetDirection()
	{return Direction;}
	
	// Changes the Direction variable based upon if the user enters R or L as the TurnType
	// Which corresponds to right or left accordingly
	public static void ComputeDirection(char TurnType)
	{
		switch (Direction)
		{
			case 'N':
				if (TurnType == 'R')
					SetDirection('E');
				else
					SetDirection('W');
				break;
			case 'E':
				if (TurnType == 'R')
					SetDirection('S');
				else
					SetDirection('N');
				break;	
			case 'S':
				if (TurnType == 'R')
					SetDirection('W');
				else
					SetDirection('E');
				break;	
			case 'W':
				if (TurnType == 'R')
					SetDirection('N');
				else
					SetDirection('S');
				break;
		}
	}
	
}
