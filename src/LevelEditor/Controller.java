package LevelEditor;

import java.io.FileNotFoundException;
import com.golden.gamedev.object.Sprite;


public class Controller
{

    private Model myModel;
    @SuppressWarnings("unused")
    private View myView;


    public Controller ()
    {
        myModel = new Model(this);
        myView = new View(this);
    }


    public void addInanimateSprite (Sprite item, String imageURL, int x, int y)
    {
        item.setLocation(x, y);
        myModel.addNonFighterSprite(item, imageURL);

    }


    public void finish (String levelName) throws FileNotFoundException
    {
        myModel.setLevelName(levelName);
        myModel.save();
    }

}
