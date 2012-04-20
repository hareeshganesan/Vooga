package action;

import java.awt.geom.Point2D;

import npsprite.FighterBody;
//import sprite.FighterSprite;


public class AvoidAction implements Action
{
    FighterBody myEnemy;
    FighterBody myFighter;

    public AvoidAction (FighterBody fighter, FighterBody enemy)
    {
        myFighter = fighter;
        myEnemy = enemy;
    }


    @Override
    public void performAction (long elapsedTime)
    {
        double dist =
            myFighter.getCurrentLocation()
                     .distance(myEnemy.getCurrentLocation());
        double x_direction = -(myEnemy.getX() - myFighter.getX());
        double y_direction = -(myEnemy.getY() - myFighter.getY());
        if (dist < 200)
        {
            MotionAction f =
                new MotionAction(myFighter, new Point2D.Double(x_direction,
                                                               y_direction));
            f.performAction(elapsedTime);
        }
        done = true;
    }


    @Override
    public boolean isDone (long elapsedTime)
    {
        return done;
    }

}
