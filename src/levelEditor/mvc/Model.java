package levelEditor.mvc;

import java.util.ArrayList;
import levelEditor.output.LevelProxy;
import com.golden.gamedev.object.Sprite;


/**
 * @author Peggy Li (pl59)
 */

public class Model
{

    private Controller myController;

    private LevelProxy myLevel;

    private ArrayList<Sprite> mySprites;


    public Model (Controller controller)
    {
        myController = controller;
    }


    public void addSprite (Sprite s)
    {
        mySprites.add(s);

        System.out.println("Successfully added new sprite");
    }


    /**
     * @TODO implement
     */
    public void createLevelObject ()
    {

    }


    /**
     * @TODO implement
     */
    public void saveToFile ()
    {

    }


    /*
     * =================================== INPUT CHECKING METHODS
     * ===================================
     */

    /**
     * Checks whether a level with the given name already exists
     * 
     * @return true if name has not already been used
     * @TODO implement file checking
     */
    public boolean verifyNameAvailable (String name)
    {
        return true;
    }


    /**
     * Checks whether a new Sprite can be placed at location (x, y) e.g. there
     * is not already a Sprite at that location or within _____ distance
     * 
     * @return if location conflict occurs
     */
    public boolean findLocationConflict (int x, int y, int radius)
    {
        for (Sprite s : mySprites)
        {
            double xSquared = Math.pow(s.getX() - x, 2);
            double ySquared = Math.pow(s.getY() - y, 2);
            if (Math.sqrt(xSquared + ySquared) > radius)
            {
                myController.displayMessageToUser(String.format("Sprite already exists within %d of location (%d, %d)",
                                                                radius,
                                                                x,
                                                                y));
                return true;
            }
        }
        return false;
    }

}
