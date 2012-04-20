package action;

import game.GameState;
import game.MainGame;


public class QuitAction implements Action
{

    MainGame myEngine;
    boolean done;


    public QuitAction (MainGame arg0)
    {
        myEngine = arg0;
    }


    @Override
    public void performAction (long elapsedTime)
    {
        GameState m = ((GameState) myEngine.getCurrentGame());
        m.transitionState();
        done = true;
    }


    @Override
    public boolean isDone (long elapsedTime)
    {
        return done;
    }

}
