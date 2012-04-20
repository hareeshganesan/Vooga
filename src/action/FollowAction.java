package action;

import npsprite.FighterBody;

//import sprite.FighterSprite;

public class FollowAction extends Action
{
    FighterBody myEnemy;
    FighterBody myFighter;

    public FollowAction (FighterBody fighter, FighterBody enemy)
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
