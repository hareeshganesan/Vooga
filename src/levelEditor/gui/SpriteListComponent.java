package levelEditor.gui;


import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;


import levelEditor.mvc.Controller;

/**
 * @author Peggy Li
 */


public class SpriteListComponent extends AbstractViewComponent {

	private Controller myController;
	
	public SpriteListComponent (Controller c) {
		myController = c;
	}
	
	public JComponent create() {
		JScrollPane scroll = new JScrollPane();
		
		return scroll;
	}
	
	
	private JList findSprites () {
		JList list = new JList();
		
		return list;
	}
	
	
}
