package levelEditor.mvc;

import java.util.ArrayList;
import com.golden.gamedev.object.Sprite;


/**
 * Creates object to hold all information associated with a newly designed
 * level, updated as level is built Facilitates writing out level data to
 * external file by serving as intermediary between GUI and file writer
 * 
 * @author Peggy Li (pl59)
 */

public class LevelObject
{

    private String myLevelName;
    private String myBgImageURL;
    private ArrayList<Sprite> mySprites;


    public LevelObject ()
    {

    }


    public String getLevelName ()
    {
        return myLevelName;
    }


    public void setLevelName (String name)
    {
        myLevelName = name;
    }


    public String getBackgroundImage ()
    {
        return myBgImageURL;
    }


    public void setBackgoundImage (String url)
    {
        myBgImageURL = url;
    }


    public void addSprite (Sprite s)
    {
        mySprites.add(s);
    }


    public ArrayList<Sprite> getSprites ()
    {
        return mySprites;
    }


    public String getWriteable ()
    {
        StringBuilder build = new StringBuilder("");

        return build.toString();
    }

}
