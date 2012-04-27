package levelEditor.gui;

import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JList;
import levelEditor.mvc.Controller;
import com.golden.gamedev.object.Sprite;


public class RemoveSpriteWindow
{

    Controller myController;

    ArrayList<Sprite> sprites;


    public RemoveSpriteWindow (Controller c)
    {
        sprites = new ArrayList<Sprite>();

        myController = c;
    }


    public JComponent display (String[] sprites)
    {

        JList list = new JList(sprites);

        return list;
    }

}
