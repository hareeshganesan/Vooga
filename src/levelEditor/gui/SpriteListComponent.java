package levelEditor.gui;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import levelEditor.mvc.Controller;

/**
 * @author Peggy Li
 */

@SuppressWarnings("unused")
public class SpriteListComponent {

	private Controller myController;
	
	private final static String[] myNames = {"Fire", "5x Power-Up", "10x Power-Up", "Shield", "Sword", "Block", "Wall"};
	
	public SpriteListComponent (Controller c) {
		myController = c;
	}
	
	public JComponent create() {
		JList list = new JList(myNames);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.
				HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.
				VERTICAL_SCROLLBAR_AS_NEEDED);
		
		return scroll;
	}
	
	
	
	
}
