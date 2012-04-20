package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import levelEditor.mvc.Controller;

public class LevelSetupComponent {

    private Controller myController;

    public LevelSetupComponent(Controller c) {
        myController = c;
    }

    public JComponent create() {
        JPanel panel = new JPanel();

        panel.add(new LevelNameComponent(myController).create(),
                BorderLayout.WEST);
        panel.add(new BackgroundImageComponent(myController).create(),
                BorderLayout.EAST);

        panel.setSize(50, 100);

        return panel;
    }

}
