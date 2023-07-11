import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class SelectFileConsole extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private static JButton SelectFileButton;
	private JLabel SelectFileLabel;
	private static JList FileNamesList;
	private static boolean FileSelectedFlag;
	private static String SelectedFileName;

	public SelectFileConsole()
	{
		// Set FileSelectedFlag = false
		FileSelectedFlag = false;
		
		// Create the Select File Button Component
		SelectFileButton = new JButton();
		SelectFileButton.setText("Select File");
		SelectFileButton.setVerticalTextPosition(AbstractButton.CENTER);
		SelectFileButton.setHorizontalTextPosition(AbstractButton.CENTER);
		SelectFileButton.setActionCommand("FileSelected");
		
		// Create the Select File Label Component
		SelectFileLabel = new JLabel("Select a file to load and execute");
	    SelectFileLabel.setHorizontalAlignment(AbstractButton.CENTER);
		
	    // Create the Select File List Component
	    FileNamesList = new JList(FileHandler.GetArrayOfFileNames()); // Gets name of files that are NOT NumOfSaves.txt
	    FileNamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // set single selection mode - one file chosen only
	    
	    // Add listeners
	    SelectFileButton.addActionListener(this);
	    
	    // Add the components
	    add(SelectFileLabel);
	    add(FileNamesList);
	    add(SelectFileButton);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	// Removed feature - if user pressed enter to select file
	public void keyTyped(KeyEvent e) 
	{ExecuteX();}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("FileSelected"))
		{ExecuteX();}	
	}

	public static boolean GetFileSelectedFlag()
	{return FileSelectedFlag;}
	
	// Main sequence here
	public static void ExecuteX()
	{
		if (FileNamesList.isSelectionEmpty()) // If the user does not select an option...
		{System.out.println("No textfile selected. Please select a file");}
		else
		{ // So if user has selected an option...
			 SelectedFileName = FileNamesList.getSelectedValue().toString();
			 try { // Retrieve the text in the file to an array, and validate the array
				 
				 String[] SplittedTextFileArray = FileHandler.ParseFileToArray(SelectedFileName);
				 boolean ValidTextFile = ValidateTextFileArray(SplittedTextFileArray);
					
				 // So if the textfile is invalid (from having an incorrect format or <3 commands...)
				 if (ValidTextFile == false) 
				 {
					System.out.println("Invalid textfile selected. Textfile must be of correct format and have at least 3 commands");
				 	GlobalFinch.InvalidInputFinchResponse();
					QuitSelectFile();
				 }
				 else
				 {
					ExecuteTextFileArray(SplittedTextFileArray);
					System.out.println("Execute File Command - program has successfully executed the file "+ SelectedFileName);
					QuitSelectFile();
				 }
				 } 
			 catch (IOException e) {}
		}
	}
	
	
	// The textfile has to be entirely validated for it all to run.
	// The moment one command is considered invalid by the program,
	// The program will consider the entire file as an invalid command
	public static boolean ValidateTextFileArray(String[] SplittedTextFileArray)
	{
		boolean ValidTextFileFlag = true;
		int Index = 0;
		int NumOfCommands = SplittedTextFileArray.length;
		Tracelist TempTracelist = new Tracelist();
		
		String CurrentCommandString; // Used as a temporary holder
		
		// While the Index is less than NumOfCommands AND the ValidTextFileFlag is still true
		while ((Index < NumOfCommands) && (ValidTextFileFlag == true))
		{
			CurrentCommandString = SplittedTextFileArray[Index];
			// Temp tracelist needed to check if the txt FILE is able to retrace itself independent of the programs tracelist
			ValidTextFileFlag = Validate.ValidateString(CurrentCommandString, TempTracelist);
			TempTracelist.AddToTracelist(CurrentCommandString);
			Index++;
		}
		
		if (NumOfCommands >= 3) // If there are more than 3 commands in the textfile...
			return ValidTextFileFlag;
		else
			return false;
	}
	
	
	// Will execute all commands in an array (that has already been validated)
	public static void ExecuteTextFileArray(String[] SplittedTextFileArray) throws IOException
	{
		 Tracelist TempTracelist = new Tracelist();
		 int Index = 0;
		 int NumOfCommands = SplittedTextFileArray.length;
		 String CurrentCommandString;
		 
		 while (Index != NumOfCommands)
		 {
			 CurrentCommandString = SplittedTextFileArray[Index];
			 ExecuteCommand.ExecuteCommandString(CurrentCommandString, TempTracelist);
			 TempTracelist.AddToTracelist(CurrentCommandString);
			 Index++;
		 }
	}
	
	// Closes the SelectFileGUI and opens up the NavigateGUI instead
	public static void QuitSelectFile()
	{
 		JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(SelectFileButton); // was orgiginally on this instead of inputcommandbutton
     	parent.setVisible(false);
 		parent.dispose();
 		NavigateGUI.enableFrame();
	}
}
