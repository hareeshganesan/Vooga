package levelEditor.gui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import levelEditor.mvc.Controller;
import levelEditor.mvc.SpriteEditable;

/**
 * @author Peggy Li (pl59)
 */

@SuppressWarnings({"serial", "unused"})
public class AIEditor extends JPanel {

	private static final String AI_TYPE = "SituationalStrategyAgent";
	
	private Controller myController;
	
	private SpriteLocationEditor locationEditor;
	private JTextField speedField;
	
	private double mySpeed;
	
	private JButton addAIButton;
	
	public AIEditor (Controller controller) {
		myController = controller;
		
		locationEditor = new SpriteLocationEditor();
		speedField = new JTextField("0.0", 5);
		
		addAIButton = new JButton("Add AI");
		addAIButton.addActionListener(new AddAIActionListener());
		
		add(new JLabel("Customize AI "));
		add(locationEditor);
		add(new JLabel("speed"));
		add(speedField);
		add(addAIButton);

	}



	private class AddAIActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String type = AI_TYPE;
			Point loc = locationEditor.getLocation();
			
			System.out.println(type + " at " + loc);
			
			SpriteEditable spriteFiller = new SpriteEditable(type);
			spriteFiller.addProperty("x", Integer.toString(loc.x));
			spriteFiller.addProperty("y", Integer.toString(loc.y));
			spriteFiller.addProperty("speed", speedField.getText());
			
			myController.addSprite(spriteFiller);
		}
	}
}