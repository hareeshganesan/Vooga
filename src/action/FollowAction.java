package action;

import sprite.FighterSprite;

public class FollowAction extends Action
{
    FighterSprite myEnemy;

    public FollowAction (FighterSprite fighter, FighterSprite enemy)
    {
        myFighter = fighter;
        myEnemy = enemy;
    }

    @Override
    public void performAction (long elapsedTime)
    {
        MotionAction f = new MotionAction(myFighter, myEnemy.getCurrentLocation());
        f.performAction(elapsedTime);
        
    }

}
