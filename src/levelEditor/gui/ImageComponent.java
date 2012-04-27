package levelEditor.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class ImageComponent extends JComponent
{
    Image image;
    Dimension size;


    public ImageComponent (Image image)
    {
        this.image = image;
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 0);
        try
        {
            mt.waitForAll();
        }
        catch (InterruptedException e)
        {
            // error ...
        }
        ;

        size = new Dimension(image.getWidth(null), image.getHeight(null));
        setSize(size);
    }


    public void paint (Graphics g)
    {
        g.drawImage(image, 0, 0, this);
    }


    public Dimension getPreferredSize ()
    {
        return size;
    }
}
