package levelEditor.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisualComponent extends JPanel {

	private static final long serialVersionUID = 1L;
	
	BufferedImage image;
	Graphics graphics;
	
    public VisualComponent(Graphics g) {
    	graphics = g;
    	
    	setVisible(true);
    }

    public void showImage (File f) {
    	try {
    		File file = new File("src/resources/block.png");
    		image = ImageIO.read(file);
    		System.out.println(image != null);
    		//paint(graphics);
    		
    		//add(new JLabel(new ImageIcon(image)));
    		System.out.println("Image read");
    		repaint();
    	}
    	catch (IOException e) {
    		System.out.println("Image file unread");
    		e.printStackTrace();
    	}
    }
    
    public void paint (Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(image, 50, 50, null);
    }
    
    
}
