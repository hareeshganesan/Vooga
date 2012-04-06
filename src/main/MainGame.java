package main;

import java.awt.Dimension;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;


public class MainGame extends GameEngine
{

    public static final int TITLE = 0, GAME = 1;


    @SuppressWarnings("static-access")
    public void initResources ()
    {
        bsIO.setMode(bsIO.WORKING_DIRECTORY);
        nextGameID = TITLE;
    }


    /**
     * Generates the correct game object based on the menu screen selected
     */
    public GameObject getGame (int GameID)
    {
        switch (GameID)
        {
            case TITLE:
                return new Title(this);
            case GAME:
                return new CombatInstance(this);

        }
        return null;
    }


    public static void main (String[] args)
    {
        //OpenGLGameLoader game = new OpenGLGameLoader();
        GameLoader game = new GameLoader();
        game.setup(new MainGame(), new Dimension(544, 544), false);
        game.start();
    }

    {
        distribute = true;
    }
}
