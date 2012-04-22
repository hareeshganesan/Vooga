package levelEditor.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class BackgroundPane extends JFrame {

	Image image;

	// im = default image
	public BackgroundPane (Image im) {
		image = im;
	}

	public BackgroundPane (String path) {

		image = Toolkit.getDefaultToolkit().getImage(path);
		
	}

	public JComponent showImage () {
		
		JScrollPane pane = new JScrollPane();

		System.out.println("Rendering image...");
		
		pane.add(new ImageComponent((Image) image));

		return pane;
	}


	public void setBackground (String filepath) {

	}

	public void setBackground (Image im) {
		image = (BufferedImage) im;
		showImage();
	}

	public void setBackground (File file) {

	}
}
