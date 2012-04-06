package action;

import java.awt.geom.Point2D;
import java.util.Vector;


public class MotionAction extends Action
{
    double x_speed;
    double y_speed;
    

    public MotionAction (FighterSprite fighter, Point2D point)
    {
        myFighter = fighter;
        double dist = myFighter.getCurrentLocation().distance(point);
        if (dist > 0)
        {
            x_speed = (point.getX() - myFighter.getX()) / dist;
            y_speed = (point.getY() - myFighter.getY()) / dist;
        }

    }


    public MotionAction ()
    {
        x_speed = 0;
        y_speed = 0;
    }

    @Override
    public void performAction (long elapsed_time)
    {
        PhysicsEngine.BasicPhysicsEngine.process(myFighter, x_speed, y_speed, elapsed_time);
    }

}
