package levelEditor.gui;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class BackgroundPane extends JFrame
{

    private static final String DEFAULT_BACKGROUND = "src/resources/title.png";

    private Image image;


    public BackgroundPane ()
    {
        this(DEFAULT_BACKGROUND);
    }


    public BackgroundPane (String path)
    {
        this(Toolkit.getDefaultToolkit().getImage(path));
        System.out.println(image != null);
    }


    public BackgroundPane (Image im)
    {
        image = im;
    }


    public JComponent showImage ()
    {

        JScrollPane pane = new JScrollPane();

        System.out.println("Rendering image...");

        pane.add(new ImageComponent((Image) image));

        return pane;
    }


    public void setBackground (Image im)
    {
        image = im;

        showImage();
    }


    public static void main (String[] args)
    {
        JFrame frame = new JFrame();
        BackgroundPane bg = new BackgroundPane("");
        frame.add(bg.showImage());
        frame.setVisible(true);
    }
}
