package action;

import java.awt.geom.Point2D;
import java.util.Vector;
import sprite.BasicPhysicsEngine;


public class MotionAction extends Action
{
    double x_speed;
    double y_speed;


    public MotionAction (Point2D point)
    {
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
        //Call Physics Engine with x and y speed

        System.out.println(myFighter.getName() + " is moving " +
                           Double.toString(x_speed) +
                           " in the x direction and " +
                           Double.toString(y_speed) + " in the y direction.");
        BasicPhysicsEngine.process(myFighter, x_speed, y_speed, elapsed_time);
    }

}
