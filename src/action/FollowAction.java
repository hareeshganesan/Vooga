package action;

import npsprite.FighterBody;

//import sprite.FighterSprite;


public class FollowAction implements Action
{
    FighterBody myEnemy;
    FighterBody myFighter;
    boolean done;
    
    public FollowAction (FighterBody fighter, FighterBody enemy)
    {
        myFighter = fighter;
        myEnemy = enemy;
    }


    @Override
    public void performAction (long elapsedTime)
    {
        MotionAction f =
            new MotionAction(myFighter, myEnemy.getCurrentLocation());
        f.performAction(elapsedTime);
        done = true;
    }

    public boolean isDone (long elapsedTime)
    {
        return done;
    }

}
