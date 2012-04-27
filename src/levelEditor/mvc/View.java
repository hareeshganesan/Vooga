package levelEditor.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelEditor.gui.BackgroundImage;
import levelEditor.gui.LevelNameComponent;
import levelEditor.gui.SaveLevelComponent;


/**
 * @author Peggy Li (pl59)
 */


@SuppressWarnings({"serial"})
public class View extends JFrame {

	private static final Dimension SIZE = new Dimension(600, 600);
	
	private Controller myController;
	
	public View (Controller controller) {
		myController = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// NORTH
		add (new LevelNameComponent(myController), BorderLayout.NORTH);
		
		// SOUTH
		add (new SaveLevelComponent(myController).create(), BorderLayout.SOUTH);
		
		// WEST
		// add (new SpriteListComponent(myController).create(), BorderLayout.WEST);
		
		// EAST
		//add (new AIEditor(), BorderLayout.EAST);
		
		// CENTER
		// add (new BackgroundImage("src/resources/flame.png"), BorderLayout.CENTER);
		add (new BackgroundImage(), BorderLayout.CENTER);
		
		
		setSize(SIZE);
		
		//set pop-up window centered relative to screen
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public void close () {
		System.exit(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void displayMessage (String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public void displayError (String message) {
		JOptionPane.showMessageDialog(null, message, message, 
				JOptionPane.ERROR_MESSAGE);
	}
	
}
