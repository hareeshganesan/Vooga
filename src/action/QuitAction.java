package action;

import game.GameState;
import game.MainGame;

public class QuitAction extends Action
{

    MainGame myEngine;
    public QuitAction(MainGame arg0){
        myEngine = arg0;
    }
    @Override
    public void performAction (long elapsedTime)
    {
        GameState m = ((GameState) myEngine.getCurrentGame());
        m.transitionState();
    }

}
