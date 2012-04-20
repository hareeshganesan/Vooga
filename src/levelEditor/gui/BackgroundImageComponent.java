package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import levelEditor.mvc.Controller;


@SuppressWarnings("unused")
public class BackgroundImageComponent {

    private Controller myController;

    private JButton myChooseImageButton;
    private JFileChooser myImageChooser;
    private String myBackgroundImageURL;
    private JPanel myPanel;
    private ImagePanel myImage;

    public BackgroundImageComponent(Controller c) {
        myController = c;
    }

    public JComponent create() {
        myPanel = new JPanel();
        initializeImageChooser();

        myChooseImageButton = new JButton("Select Background Image");
        myChooseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myImageChooser.showOpenDialog(null);
                myBackgroundImageURL = myImageChooser.getSelectedFile()
                        .getName();

                /*
                 * myImage = new ImagePanel(myBackgroundImageURL);
                 * myPanel.add(myImage, BorderLayout.SOUTH);
                 */

            }
        });
        myPanel.add(myChooseImageButton);

        return myPanel;
    }

    private void initializeImageChooser() {
        myImageChooser = new JFileChooser(System.getProperties().getProperty(
                "user.dir"));
        myImageChooser.setFileFilter(new FileNameExtensionFilter(
                "JPEG, PNG, GIF", "jpeg", "png", "gif", "jpg"));
    }

}
