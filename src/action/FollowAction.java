package action;

import sprite.FighterSprite;


public class FollowAction implements Action
{
    FighterSprite myEnemy;
    FighterSprite myFighter;
    boolean done;


    public FollowAction (FighterSprite fighter, FighterSprite enemy)
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
