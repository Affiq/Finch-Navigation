import javax.swing.JFrame;

//This class creates the Select File form
public class SelectFileGUI implements Runnable
{
	
    private static JFrame frame = new JFrame("Select File Console");

	
	//Since the class 'Runnable' is an implementation we have to define this method
	public void run() 
	{
		//Create and set up the Window
        //Disable the exit 'X' and close menu
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        SelectFileConsole newContentPane = new SelectFileConsole();
        newContentPane.setOpaque(true);
        //Put the Pane in the Window!
        frame.setContentPane(newContentPane);
        frame.setBounds(600,600,800,800);
        frame.setVisible(true);   
    }
	
	// Static method needed to hide the SelectFileGUI when the NavigateGUI pops up
	// NOT USED as the SelectFileGUI is disposed of instead of not being made visible
	public static void disableFrame()
	{
		frame.setVisible(false);
	}
	
	// Static method needed to enable the SelectFileGUI when the NavigateGUI pops up
	// NOT USED as a new instance of SelectFileGUI is instantiated and disposed of every time the SelectFileGUI appears 
	public static void enableFrame()
	{
		frame.setVisible(true);
	}
	
	
}