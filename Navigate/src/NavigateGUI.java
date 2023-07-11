import javax.swing.JFrame;

//This class creates the main Command input GUI
public class NavigateGUI implements Runnable
{
	
	private static JFrame frame = new JFrame("NavigateConsole");
	
	//Since the class 'Runnable' is an implementation we have to define this method
	public void run() 
	{
	    //Create and set up the Window
        //Disable the exit 'X' and close menu
		frame.setLocationRelativeTo(null);
		frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        NavigateConsole newContentPane = new NavigateConsole();
        newContentPane.setOpaque(true);
        //Put the Pane in the Window!
        frame.setContentPane(newContentPane);
        frame.setBounds(600,600,800,800);
        frame.setVisible(true);
    }
	
	// Static method needed to hide NavigateGUI when the SelectFileGUI pops up
	// Used in ExecuteCommand.java (SelectFileForX function)
	public static void disableFrame()
	{
		frame.setVisible(false);
	}
	
	// Static method needed to redisplay the NavigateGUI when the user is finished selecting a file
	// Used in SelectFileConsole,java (QuitSelectFile function)
	public static void enableFrame()
	{
		frame.setVisible(true);
	}
}