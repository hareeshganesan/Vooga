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
	
	private String[] myNames;
	
	public SpriteListComponent (Controller c) {
		myController = c;
		
		//myNames = null;
		
	}
	
	public JComponent create() {
		JList list = new JList(myNames);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.
				HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.
				VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setSize(100, 100);
		
		return scroll;
	}

}
	
