package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import levelEditor.mvc.Controller;

@SuppressWarnings({ "unused", "serial" })
public class BackgroundImageComponent extends JPanel {

	
	private Controller myController;
	
	private JPanel myPanel;
	private JButton myChooseImageButton;
	private JFileChooser myImageChooser;
	
	private File myBackgroundImage;
	private String myBackgroundImageURL;
	private JLabel myImageURLLabel;
	
	VisualComponent image;

	public BackgroundImageComponent (Controller c) {
		myController = c;
		
		setVisible(true);
	}
	

	public JComponent create() {
		myPanel = new JPanel();
		//image = new VisualComponent();
		//myPanel.add(image, BorderLayout.CENTER);
		
		initializeImageChooser();

		myChooseImageButton = new JButton("Select Background Image");
		myChooseImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				myImageChooser.showOpenDialog(null);
				//myBackgroundImageURL = myImageChooser.getSelectedFile().getName();
				myBackgroundImage = myImageChooser.getSelectedFile();
				
				// image.showImage(myBackgroundImage);
				
			}
		} );
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
