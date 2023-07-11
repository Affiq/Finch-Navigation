
public class Position {

	private static int XCoordinate;
	private static int YCoordinate;
	private static int TotalDistance;
	private static int Displacement;

	public static void InitialisePosition() // Sets all attributes to 0 
	{
		XCoordinate = 0;
		YCoordinate = 0;
		TotalDistance = 0;
		Displacement = 0;
	}
	
	public static void CalculatePosition(int Duration, int Speed) // also does displacement and total distance
	{
		int DistanceChange = Duration * Speed; // Can also be negative if Finch is moving backwards
		char CurrentDirection = Direction.GetDirection();
		
		
		switch (CurrentDirection)
		{
		case 'N': // If Finch is facing north, add DistanceChange to YCoordinate
			YCoordinate = YCoordinate + DistanceChange;
			break;
		case 'E': // If Finch is facing east, add DistanceChange to XCoordinate
			XCoordinate = XCoordinate + DistanceChange;
			break;
		case 'S':  // If Finch is facing south, subtract DistanceChange to YCoordinate
			YCoordinate = YCoordinate - DistanceChange;
			break;
		case 'W': // If Finch is facing west, subtract DistanceChange to YCoordinate
			XCoordinate = XCoordinate - DistanceChange;
			break;			
		}
		TotalDistance = TotalDistance + Math.abs(DistanceChange);
		// Absolute value needed to ignore negative value -
		// TotalDistance travelled by the Finch should never decrease
		CalculateDisplacement(XCoordinate, YCoordinate);
	}
	
	// void method as function also deals with changing the Displacement Attribute
	public static void CalculateDisplacement(int XValue, int YValue)
	{
		double CalculatedDisplacement;
		CalculatedDisplacement = Math.sqrt(Math.pow(XValue, 2) + Math.pow(YValue, 2));
		Displacement = (int)CalculatedDisplacement; // typecasting done here, double to integer
	}
	
	// Displays all attributes in a certain format
	public static void DisplayPosition()
	{
		System.out.println("Position:");
		System.out.println("Direction currently facing: "+Direction.GetDirection());
		System.out.println("X Coordinates: "+XCoordinate);
		System.out.println("Y Coordinates: "+YCoordinate);
		System.out.println("Total Distance Travelled: "+TotalDistance);
		System.out.println("Displacement: "+Displacement);
		
	}
	
	
}
