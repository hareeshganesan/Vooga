package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.BorderLayout;
import javax.swing.JPanel;

import levelEditor.mvc.Controller;

@SuppressWarnings("serial")
public class LevelSetupComponent extends JPanel {

    private Controller myController;

    public LevelSetupComponent(Controller c) {
        myController = c;
        
        add (new LevelNameComponent(myController), BorderLayout.NORTH);
        
    }


}
