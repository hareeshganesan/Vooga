package levelEditor.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import levelEditor.mvc.Controller;

/**
 * @author Peggy Li (pl59)
 */

@SuppressWarnings("serial")
public class LevelNameComponent extends JPanel {

	private static final String PROPERTY = "name";
	
	// default values for text and size of text field box
	private static final String DEFAULT_NAME = "My level";
    private static final int BOX_WIDTH = 25;
    
    private String lastNameEntry;

    private Controller myController;
    private JTextField levelNameTextField;
    private JButton saveNameButton;

    public LevelNameComponent(Controller c) {
        myController = c;
        
        add (new JLabel("Level Name: "), BorderLayout.WEST);
        
        levelNameTextField = new JTextField(DEFAULT_NAME, BOX_WIDTH);
        add(levelNameTextField);
        
        saveNameButton = new JButton("Save Level Name");
        saveNameButton.addActionListener(new LevelNameActionListener());
        add(saveNameButton);
    }

    
    private class LevelNameActionListener implements ActionListener {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		String name = levelNameTextField.getText();
    		
    		// do not repeat if name hasn't changed
    		if (!name.equals(lastNameEntry)) {
    			lastNameEntry = name;
    			myController.setProperty(PROPERTY, name);
    		}
    	}
    }

}
