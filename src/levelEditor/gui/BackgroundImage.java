package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class BackgroundImage extends JPanel
{

    private static final String DEFAULT_IMAGE = "src/resources/title.png";

    private JLabel imageURL = new JLabel();
    private JLabel imageBox = new JLabel();

    private JButton uploadImageButton;

    private JFileChooser myImageChooser;


    public BackgroundImage ()
    {

        // initializations
        myImageChooser =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
        myImageChooser.setFileFilter(new FileNameExtensionFilter("JPEG, PNG, GIF",
                                                                 "jpeg",
                                                                 "png",
                                                                 "gif",
                                                                 "jpg"));

        uploadImageButton = new JButton("Upload Background Image");
        uploadImageButton.addActionListener(new ChangeImageAction());
        uploadImageButton.setSize(getPreferredSize());

        // additional panels for layout managemnet
        JPanel left = new JPanel();
        left.add(uploadImageButton, BorderLayout.NORTH);

        imageURL.setText("Default background");
        left.add(imageURL, BorderLayout.SOUTH);

        JPanel right = new JPanel();
        imageBox.setIcon(createImageIcon(DEFAULT_IMAGE));
        right.add(imageBox, BorderLayout.CENTER);

        setSize(getPreferredSize());
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

    }


    protected ImageIcon createImageIcon (String path)
    {

        Image image;
        try
        {
            image = ImageIO.read(new File(path));
            image = getScaledImage(image, 150, 150);
            return new ImageIcon(image);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Resize image Code from
     * <url>http://docs.oracle.com/javase/tutorial/uiswing
     * /components/icon.html</url>
     */
    private Image getScaledImage (Image image, int w, int h)
    {
        BufferedImage resizedImage =
            new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, w, h, null);
        g2.dispose();
        return resizedImage;
    }

    private class ChangeImageAction implements ActionListener
    {

        @Override
        public void actionPerformed (ActionEvent arg0)
        {
            myImageChooser.showOpenDialog(null);
            String path = myImageChooser.getSelectedFile().getAbsolutePath();

            imageBox.setIcon(createImageIcon(path));
            imageURL.setText("..." + path.substring(path.length() - 32));
        }

    }
}
