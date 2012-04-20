package levelEditor.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import levelEditor.mvc.Controller;


/**
 * @author Peggy Li (pl59)
 */

@SuppressWarnings("unused")
public class LevelNameComponent
{

    private static final int BOX_WIDTH = 12;

    private Controller myController;
    private JTextField myLevelName;


    public LevelNameComponent (Controller c)
    {
        myController = c;
    }


    public JComponent create ()
    {
        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Level Name: ");
        panel.add(nameLabel, BorderLayout.WEST);

        myLevelName = new JTextField("new_level", BOX_WIDTH);
        myLevelName.addActionListener(new ActionListener()
        {

            public void actionPerformed (ActionEvent e)
            {
                String name = myLevelName.getText();
                if (!myController.levelNameAvailable(name))
                {

                    System.out.println(name + " is already taken");

                }
            }
        });

        panel.add(myLevelName);

        return panel;
    }
}
