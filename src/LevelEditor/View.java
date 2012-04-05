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
public class View extends JFrame {
	private static final Dimension SIZE = new Dimension(600, 600);

    private Controller myController;

    private JTextField myLevelNameInput;
    private JButton myAddBlockButton;

    private JTextField myXTextArea;
    private JTextField myYTextArea;
    private int myXValue;
    private int myYValue;

    private JButton myAddFighterButton;
    private JTextField myFighterName;
    private JTextField myMaxHealth;
    private JTextField mySpeed;
    private JTextField myFighterX;
    private JTextField myFighterY;
    private JFileChooser myFighterImageSelector = new JFileChooser();

    private String FighterName;
    private int MaxHealth;
    private double Speed;
    private int FighterX;
    private int FighterY;

    private JFileChooser myImageSelector;

    private JButton mySaveAndFinishButton;


    public View (Controller controller)
    {
        myController = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(makeLevelName(), BorderLayout.PAGE_START);
        add(makeNewBlock(), BorderLayout.CENTER);
        add(makeNewFighter(), BorderLayout.EAST);
        add(makeSaveAndFinishButtons(), BorderLayout.PAGE_END);

        setSize(SIZE);
        setVisible(true);

        myImageSelector =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
        myFighterImageSelector =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    }


    private Component makeNewFighter ()
    {
        JPanel fighterPanel = new JPanel();
        myFighterName = new JTextField("fighter name", 5);
        myMaxHealth = new JTextField("enter HP", 5);
        mySpeed = new JTextField("enter speed", 5);
        myFighterX = new JTextField("fighter x", 5);
        myFighterY = new JTextField("fighter y", 5);
        fighterPanel.add(myFighterName);
        fighterPanel.add(myMaxHealth);
        fighterPanel.add(mySpeed);
        fighterPanel.add(myFighterX);
        fighterPanel.add(myFighterY);

        myAddFighterButton = new JButton("Add Fighter");

        myAddFighterButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent evt)
            {
                FighterName = myFighterName.getText();
                MaxHealth = Integer.parseInt(myMaxHealth.getText());
                Speed = Double.parseDouble(mySpeed.getText());
                FighterX = Integer.parseInt(myFighterX.getText());
                FighterY = Integer.parseInt(myFighterY.getText());

                myFighterImageSelector.setFileFilter(new FileNameExtensionFilter("JPEG, PNG, GIF",
                                                                                 "jpeg",
                                                                                 "png",
                                                                                 "gif"));
                int retval = myFighterImageSelector.showOpenDialog(null);
                String url =
                    myFighterImageSelector.getSelectedFile().getAbsolutePath();

                myController.addFighterSprite(new FighterSprite(MaxHealth,
                                                                FighterX,
                                                                FighterY,
                                                                FighterName,
                                                                Speed), url);

            }
        });

        fighterPanel.add(myAddFighterButton, BorderLayout.WEST);

        return fighterPanel;

    }


    private Component makeNewBlock ()
    {
        JPanel panel = new JPanel();

        myXTextArea = new JTextField("x", 5);
        myYTextArea = new JTextField("y", 5);
        panel.add(myXTextArea);
        panel.add(myYTextArea);

        JButton myAddBlockButton = new JButton("Add Block");

        myAddBlockButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent evt)
            {
                myXValue = Integer.parseInt(myXTextArea.getText());
                myYValue = Integer.parseInt(myYTextArea.getText());

                myImageSelector.setFileFilter(new FileNameExtensionFilter("JPEG, PNG, GIF",
                                                                          "jpeg",
                                                                          "png",
                                                                          "gif"));
                int retval = myImageSelector.showOpenDialog(null);
                String url =
                    myImageSelector.getSelectedFile().getAbsolutePath();

                myController.addInanimateSprite(new BlockSprite(),
                                                url,
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
