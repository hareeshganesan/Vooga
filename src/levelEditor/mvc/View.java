package levelEditor.mvc;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import levelEditor.gui.AIEditor;
import levelEditor.gui.BackgroundImage;
import levelEditor.gui.LevelNameComponent;
import levelEditor.gui.SaveLevelComponent;
import levelEditor.gui.SpriteEditor;


/**
 * @author Peggy Li (pl59)
 */


@SuppressWarnings({"serial"})
public class View extends JFrame {

	private static final Dimension SIZE = new Dimension(600, 640);
	
	private Controller myController;
	
	public View (Controller controller) {
		myController = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(200, 400);
		
		BoxLayout l = new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
		this.setLayout(l);

		add (new LevelNameComponent(myController));
		
		add (new BackgroundImage());
		
		add (new SpriteEditor(myController));
		
		add (new AIEditor(myController));
		
		add (new JLabel("Note: Please save level name before clicking Save!"));
		add (new SaveLevelComponent(myController).create());
		
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
