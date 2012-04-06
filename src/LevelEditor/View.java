package LevelEditor;
/**
 * 
 * @author Peggy Li / pl59 
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.*;

import LevelEditor1.BlockSprite;

@SuppressWarnings({"serial", "unused"})
public class View extends JFrame {

	private static final Dimension SIZE = new Dimension(400, 400);
	
	private Controller myController;
	private JList mySpriteTypesList;
	
	private JTextField myLevelNameField;
	
	private JButton mySetBackgroundButton;
	private String myBackgroundImageURL;

	private JTextField myXTextArea;
	private JTextField myYTextArea;
	private int myXValue;
	private int myYValue;
	
	private JButton myAddImageButton;
	private JButton myAddSpriteButton;
	private String myImagePath;
	private JLabel myURLLabel;
	
	private JFileChooser myImageSelector;
	
	private JButton mySaveAndFinishButton;
	
	
	/* ======================================== END OF INSTANCE VARIABLES ======================================== */
	
	
 	public View (Controller controller) {
		myController = controller;
		
		myImageSelector = new JFileChooser
				(System.getProperties().getProperty("user.dir"));
		myImageSelector.setFileFilter(new FileNameExtensionFilter(
				"JPEG, PNG, GIF", "jpeg", "png", "gif"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(makeLevelSettings(), BorderLayout.PAGE_START);
		add(makeNewSprite(), BorderLayout.CENTER);
		add(makeSaveAndFinishButtons(), BorderLayout.PAGE_END);
		
	
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
	
	
	private JComponent getBackgroundImage () {
		JPanel panel = new JPanel();
		
		mySetBackgroundButton = new JButton("Set Background");
		panel.add(mySetBackgroundButton);
		
		mySetBackgroundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				myImageSelector.showOpenDialog(null);
				myBackgroundImageURL = myImageSelector.getSelectedFile().getPath();
			
			}
		} );
		
		return panel;
	}
	
	
	private JComponent makeLevelSettings () {
		JPanel panel = new JPanel();
		
		panel.add(makeLevelName(), BorderLayout.NORTH);
		panel.add(getBackgroundImage(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Create a scrollable list of the different inanimate Sprite types
	 * 	that the user can add to the level and then edit properties of
	 * 
	 * @TODO: use Reflection to get list of all Sprites
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
	 * Closes the current active GUI window
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

	
	// this method was called makeNewBlock
	private JComponent makeNewSprite () {
		JPanel panel = new JPanel();
		
		myXTextArea = new JTextField("x", 5);
		myYTextArea = new JTextField("y", 5);
		panel.add(myXTextArea);
		panel.add(myYTextArea);
		
		myImagePath = "";
		myURLLabel = new JLabel(myImagePath);
		
		myAddImageButton = new JButton("Add Image");
		panel.add(myAddImageButton);
		panel.add(myURLLabel);
		
		myAddImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				myImageSelector.showOpenDialog(null);
				myImagePath = myImageSelector.getSelectedFile().getPath();
				
				myURLLabel.setText(myImagePath);
			}
		} );
	
		
		myAddSpriteButton = new JButton("Add Sprite");
		myAddSpriteButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evt) {
				myXValue = Integer.parseInt(myXTextArea.getText());
				myYValue = Integer.parseInt(myYTextArea.getText());
			
				// should no longer use blocksprite as example
				myController.addSprite(
						new BlockSprite(), myImagePath, myXValue, myYValue);

			}
		} );
		panel.add(myAddSpriteButton, BorderLayout.SOUTH);
		
		
		return panel;
	}
}
