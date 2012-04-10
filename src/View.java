/**
 * 
 * @author Peggy Li / pl59 
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class View extends JFrame {

	private static final Dimension SIZE = new Dimension(400, 400);
	
	private Controller myController;
	private JList mySpriteTypesList;
	
	private JTextField myLevelNameField;

	private JTextField myXTextArea;
	private JTextField myYTextArea;
	private int myXValue;
	private int myYValue;
	
	
	private JFileChooser myImageSelector;
	
	private JButton mySaveAndFinishButton;
	
	
	public View (Controller controller) {
		myController = controller;
		myImageSelector = new JFileChooser
				(System.getProperties().getProperty("user.dir"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
		setSize(SIZE);
		setVisible(true);
	}
	
	
	/**
	 * Asks user to name level being created, which will be used to
	 * 	name the file to which level data will be written and stored
	 * 
	 * Default name of "new_level" set if no change made by user
	 * 
	 * @TODO: error handling: 
	 * 	empty string for name
	 * 	file with same name already exists
	 * 	(name has invalid chars? only 0-9a-zA-z?)
	 * 
	 * @return name assigned to level by user
	 */
	private JComponent makeLevelName () {
		JPanel panel = new JPanel();
		
		JLabel nameLabel = new JLabel ("Level Name: ");
		panel.add(nameLabel);
		
		myLevelNameField = new JTextField("new_level", 24);
		panel.add(myLevelNameField);
		return panel;
	}
	
	
	/**
	 * Create a scrollable list of the different inanimate Sprite types
	 * 	that the user can add to the level and then edit properties of
	 * @return
	 */
	private JComponent listAllSprites () {
		mySpriteTypesList = new JList();
		
		JScrollPane scroll = new JScrollPane();
		
		scroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		return scroll;
	}

	
	
	/**
	 * @TODO
	 */
	public void close () {
		System.exit(EXIT_ON_CLOSE);
	}
	
	/**
	 * Displays a general or informational message to the user
	 * @param message to be displayed
	 */
	public void displayMessage (String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * Displays an error message to the user
	 * 	for example, used when invalid input is detected
	 */
	public void displayError (String message, String title) {
		JOptionPane.showMessageDialog(this, 
				message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	

	private JComponent makeSaveAndFinishButtons () {
		JPanel panel = new JPanel();

		mySaveAndFinishButton = new JButton("SAVE AND FINISH");
		mySaveAndFinishButton.setPreferredSize(new Dimension(140, 40));

		mySaveAndFinishButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				String levelName = myLevelNameField.getText();
				myController.saveAndClose(levelName);
				System.exit(0);
				
			}
		});

		panel.add(mySaveAndFinishButton, BorderLayout.SOUTH);
		return panel;
	}

	
	
	private JComponent customizeNewSprite () {
		JPanel panel = new JPanel();
		
		
		return panel;
	}
}
