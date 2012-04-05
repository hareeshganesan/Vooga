package LevelEditor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import com.golden.gamedev.object.Sprite;


public class Model
{

    private Controller myController;

    private String myLevelName;

    // maps each Sprite to its corresponding image
    private HashMap<Sprite, String> myInanimateSprites;


    public Model (Controller controller)
    {
        myController = controller;

        myInanimateSprites = new HashMap<Sprite, String>();
    }


    public void setLevelName (String name)
    {
        myLevelName = name;
        //System.out.println("Name of level set to " + name);
    }


    public void addNonFighterSprite (Sprite item, String url)
    {
        myInanimateSprites.put(item, url);

        //System.out.println("Sprite added with image at " + url);
    }


    public void save () throws FileNotFoundException
    {
        if (myInanimateSprites.size() > 0)
        {
            XMLWriter writer = new XMLWriter(myLevelName, myInanimateSprites);
            writer.save();
        }

    }

}
