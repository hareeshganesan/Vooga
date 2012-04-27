package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import levelEditor.mvc.Controller;

public class SaveLevelComponent {

    private Controller myController;

    private JButton mySaveButton;

    public SaveLevelComponent(Controller c) {
        myController = c;
    }

    public JComponent create() {
        JPanel panel = new JPanel();

		mySaveButton = new JButton("SAVE LEVEL");
		mySaveButton.setSize(48, 12);
		mySaveButton.setToolTipText("Click to save level to file.");
		mySaveButton.addActionListener(new SaveActionListener());

		panel.add(mySaveButton);
		return panel;
	}


	private class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			myController.saveAndClose();
		}
	}
	
}
