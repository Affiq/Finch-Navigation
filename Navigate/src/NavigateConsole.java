import javax.swing.AbstractButton;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

//Our class needs to inherit functionality from 'JPanel' and 'JActionListener'
public class NavigateConsole extends JPanel implements ActionListener, KeyListener {

	// Unique form identifier (copied off FinchControl.java)
    private static final long serialVersionUID = 1862962349L;

	private JLabel CommandLabel;
	private JTextField CommandTextField;
	private static JButton InputCommandButton;
	private String UserInput;
	private Tracelist MainTracelist = new Tracelist();
	private boolean ValidInputFlag;
	
	
	
	public NavigateConsole()
	{
		InitialiseProgram();
	
		// Create new "Input Command" button, centre the caption and add event
		InputCommandButton = new JButton("Input Command");
        InputCommandButton.setVerticalTextPosition(AbstractButton.CENTER);
        InputCommandButton.setHorizontalTextPosition(AbstractButton.CENTER);
        InputCommandButton.setActionCommand("InputCommand");

        // Create command text area and centres it
        CommandTextField = new JTextField("");
        CommandTextField.setPreferredSize(new Dimension(120,30));
        CommandTextField.setHorizontalAlignment(AbstractButton.CENTER);
        
        // Creates a label
        CommandLabel = new JLabel("Input Command Here:");
        CommandLabel.setHorizontalAlignment(AbstractButton.CENTER);
        
        // What appears when hovering
        InputCommandButton.setToolTipText("Input user command");
        
        // Add the action listeners
        InputCommandButton.addActionListener(this);
        InputCommandButton.addKeyListener(this);
        CommandTextField.addKeyListener(this);
    
        // Add the components
        add(CommandLabel);
        add(CommandTextField);
        add(InputCommandButton);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Occurs when user presses Enter key when command textbox is selected
		// Or when user clicks on the input command button
		if (e.getActionCommand().equals("InputCommand")) 
        {
			try {InputCommand();} 
			catch (IOException e1) {e1.printStackTrace();}
        }
	}

	// Set default values at the start of the program...
	public void InitialiseProgram()
	{
		GlobalFinch.setFinch(); // Instantiates a static Finch that imitates a global variable, as well as its turning velocity
		CommandsArray.InitializeCommandsArray(); // Initialises the commands that the program should accept
		Position.InitialisePosition(); // Sets XCoordinates, YCoordinates, TotalDistance and Displacement to 0
		Direction.SetInitialDirection(); // Sets the Direction value to 'N' (indicating North)
		GlobalFinch.getFinch().setLED(0, 255, 0); // Finch set on green light
	}
	
	// Also handles button disabling, Capitalising input and emptying the textfield
	public void InputCommand() throws IOException
	{
		InputCommandButton.setEnabled(false);
        UserInput = CommandTextField.getText().toUpperCase();
        CommandTextField.setText("");
     	System.out.println(UserInput);

     	// Checks if the input is a valid command
     	ValidInputFlag = Validate.ValidateString(UserInput, MainTracelist);
     	
        if (ValidInputFlag)
        {
        	// process 3.4 here - execute the command and then add it to the tracelist
        	ExecuteCommand.ExecuteCommandString(UserInput, MainTracelist);
        	MainTracelist.AddToTracelist(UserInput);
        }
        else
        {
        	// let the user know the input is invalid
        	GlobalFinch.InvalidInputFinchResponse(); 
        }
     	
     	System.out.println("");
        InputCommandButton.setEnabled(true);
	}

	public static void QuitProgram()
	{
	  	System.out.println("Quitting program...");
     	GlobalFinch.getFinch().quit();
 		JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(InputCommandButton); // was orgiginally on this instead of inputcommandbutton
     	parent.setVisible(false);
 		parent.dispose();
	}
	
	
	@Override
	// The user can also input the command by pressing the Enter Key
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			try {InputCommand();} 
			catch (IOException e1) 
			{e1.printStackTrace();}
		}		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{		
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	

}
