package LevelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    private BufferedImage myImage;

    public ImagePanel(String imagePath) {
        try {
            myImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, null);
    }

}
