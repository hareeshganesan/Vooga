package LevelEditor.mvc;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import LevelEditor.gui.LevelSetupComponent;




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
		
		// add children components
		add (new LevelSetupComponent(myController).create());
		
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
