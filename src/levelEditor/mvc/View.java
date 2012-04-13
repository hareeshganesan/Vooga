package levelEditor.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelEditor.gui.LevelSetupComponent;
import levelEditor.gui.SaveLevelComponent;
import levelEditor.gui.SpriteListComponent;

/**
 * @author Peggy Li (pl59)
 */


@SuppressWarnings({"serial"})
public class View extends JFrame {

	private static final Dimension SIZE = new Dimension(600, 200);
	
	private Controller myController;
	
	public View (Controller controller) {
		myController = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// NORTH
		add (new LevelSetupComponent(myController).create(), BorderLayout.NORTH);
		
		// SOUTH
		add (new SaveLevelComponent(myController).create(), BorderLayout.SOUTH);
		
		// WEST
		add (new SpriteListComponent(myController).create(), BorderLayout.WEST);
		
		// EAST
		
		// CENTER
		
		
		
		setSize(SIZE);
		setVisible(true);
	}
	
	
	public void close () {
		System.exit(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void displayMessage (String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
}
