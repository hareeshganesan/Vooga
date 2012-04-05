package LevelEditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * @author Peggy Li pl59
 */

@SuppressWarnings("serial")
public class View extends JFrame
{

    private static final Dimension SIZE = new Dimension(600, 200);

    private Controller myController;

    private JTextField myLevelNameInput;

    private JButton myAddImageButton;
    private JButton myAddBlockButton;
    private String myImagePath;
    private JLabel myURLLabel;

    private JTextField myXTextArea;
    private JTextField myYTextArea;
    private int myXValue;
    private int myYValue;

    private JFileChooser myImageSelector;

    private JButton mySaveAndFinishButton;


    public View (Controller controller)
    {
        myController = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(makeLevelName(), BorderLayout.PAGE_START);
        add(makeNewBlock(), BorderLayout.CENTER);
        add(makeSaveAndFinishButtons(), BorderLayout.PAGE_END);

        setSize(SIZE);
        setVisible(true);

        myImageSelector =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    }


    private Component makeNewBlock ()
    {
        JPanel panel = new JPanel();

        myXTextArea = new JTextField("x", 5);
        myYTextArea = new JTextField("y", 5);
        panel.add(myXTextArea);
        panel.add(myYTextArea);

        myImagePath = "";
        myURLLabel = new JLabel(myImagePath);

        myAddImageButton = new JButton("Add Image");
        panel.add(myAddImageButton);
        panel.add(myURLLabel);

        myAddImageButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent evt)
            {
                myImageSelector.setFileFilter(new FileNameExtensionFilter("JPEG, PNG, GIF",
                                                                          "jpeg",
                                                                          "png",
                                                                          "gif"));
                int retval = myImageSelector.showOpenDialog(null);
                myImagePath = myImageSelector.getSelectedFile().getPath();

                myURLLabel.setText(myImagePath);
            }
        });

        myAddBlockButton = new JButton("Add Block");
        myAddBlockButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent evt)
            {
                myXValue = Integer.parseInt(myXTextArea.getText());
                myYValue = Integer.parseInt(myYTextArea.getText());

                myController.addInanimateSprite(new BlockSprite(),
                                                myImagePath,
                                                myXValue,
                                                myYValue);

            }
        });
        panel.add(myAddBlockButton, BorderLayout.SOUTH);

        return panel;
    }


    /**
     * Asks user to name level being created, which will be used to name the
     * file to which level data will be written and stored Default name of
     * "newLevel" set if no change made by user
     * 
     * @TODO: error handling if user deletes default name and fails to provide a
     *        new name (e.g. empty string in text area)
     * @return name assigned to level by user
     */
    private JComponent makeLevelName ()
    {
        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Level Name: ");
        panel.add(nameLabel);

        myLevelNameInput = new JTextField("newLevel", 30);
        panel.add(myLevelNameInput);

        return panel;
    }


    private JComponent makeSaveAndFinishButtons ()
    {
        JPanel panel = new JPanel();

        mySaveAndFinishButton = new JButton("SAVE AND FINISH");
        mySaveAndFinishButton.setPreferredSize(new Dimension(140, 40));

        mySaveAndFinishButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent evt)
            {
                String levelName = myLevelNameInput.getText();
                try
                {
                    myController.finish(levelName);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                System.exit(0);

            }
        });

        panel.add(mySaveAndFinishButton, BorderLayout.SOUTH);
        return panel;
    }

}
