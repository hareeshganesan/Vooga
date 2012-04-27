package levelEditor.gui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import levelEditor.mvc.Controller;
import levelEditor.mvc.SpriteEditable;

@SuppressWarnings("serial")
public class SpriteEditor extends JPanel {

	private Controller myController;

	private JButton addButton;
	
	private SpriteLocationEditor propertiesEditor;
	private SpriteListComponent spriteList;

	public SpriteEditor (Controller controller)  {
		myController = controller;

		addButton = new JButton("Add");
		//addButton.setEnabled(false);
		addButton.addActionListener(new AddSpriteAction());
		
		propertiesEditor = new SpriteLocationEditor();
		spriteList = new SpriteListComponent(myController);

		add(spriteList.getScrollable());
		add(propertiesEditor);
		add(addButton);		

	}


	private class AddSpriteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String type = spriteList.getSelectedType();
			Point loc = propertiesEditor.getLocation();
			
			System.out.println(type + " at " + loc);
			
			SpriteEditable spriteFiller = new SpriteEditable(type);
			spriteFiller.addProperty("x", Integer.toString(loc.x));
			spriteFiller.addProperty("y", Integer.toString(loc.y));
		
			myController.addSprite(spriteFiller);

		}
	}

}
