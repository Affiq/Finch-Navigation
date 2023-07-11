import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class GlobalFinch {

	// Finch that imitates a 'global variable'
	private static Finch GlobalFinch;
	private static int TurningVelocity;
	
	public static void setFinch()
	{
		GlobalFinch = new Finch();
		TurningVelocity = 95; // experimental value - ADJUST HERE
		// The value at which one wheel moves forward and the other moves backward
		// Such that the Finch makes an orthogonal turn in 1 second
	}
	
	public static Finch getFinch()
	{
		return GlobalFinch;
	}
	
	// How the finch should respond if the user enters an invalid input
	public static void InvalidInputFinchResponse()
	{
		GlobalFinch.setLED(255, 0, 0,1000);
		GlobalFinch.buzz(1000, 500);
		GlobalFinch.buzz(600, 500);
		GlobalFinch.setLED(0, 255, 0);
	}
	
	public static void RightTurn() // takes one second
	{
		GlobalFinch.setWheelVelocities(TurningVelocity, -TurningVelocity, 1000);
		GlobalFinch.stopWheels();
	}
	
	public static void LeftTurn() // takes one second
	{
		GlobalFinch.setWheelVelocities(-TurningVelocity, TurningVelocity, 1000);
		GlobalFinch.stopWheels();
	}
	
}
