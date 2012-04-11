package action;

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
        myEngine.getCurrentGame().finish();
    }

}
