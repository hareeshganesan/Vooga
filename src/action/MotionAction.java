package action;

import java.awt.geom.Point2D;
import java.util.Vector;

import npsprite.FighterSprite;


/**
 * The MotionAction class specifies that a FighterSprite move in a different
 * direction. This direction is specified by the x and y direction variables,
 * which together determine the unit vector motion of the sprite when the action
 * is performed.
 * 
 * @author Hareesh
 */
public class MotionAction extends Action
{
    double x_direction;
    double y_direction;


    public MotionAction (FighterSprite fighter, Point2D point)
    {
        myFighter = fighter;
        double dist = myFighter.getCurrentLocation().distance(point);
        if (dist > 0)
        {
            x_direction = (point.getX() - myFighter.getX()) / dist;
            y_direction = (point.getY() - myFighter.getY()) / dist;
        }

    }


    public MotionAction ()
    {
        x_direction = 0;
        y_direction = 0;
    }


    @Override
    public void performAction (long elapsed_time)
    {
        PhysicsEngine.BasicPhysicsEngine.process(myFighter,
                                                 x_direction,
                                                 y_direction,
                                                 elapsed_time);
    }

}
