package action;

import java.awt.geom.Point2D;
import npsprite.FighterBody;
import PhysicsEngine.PhysicsEngine;


/**
 * The MotionAction class specifies that a FighterSprite move in a different
 * direction. This direction is specified by the x and y direction variables,
 * which together determine the unit vector motion of the sprite when the action
 * is performed.
 * 
 * @author Hareesh
 */
public class MotionAction implements Action
{
    double x_direction;
    double y_direction;
    FighterBody myFighter;
    boolean done;
    PhysicsEngine myPhysicsEngine;
    long upTime;


    public MotionAction (FighterBody fighter,
                         Point2D point,
                         PhysicsEngine physicsEngine)
    {
        myFighter = fighter;
        double dist = myFighter.getCurrentLocation().distance(point);
        if (dist > 0)
        {
            x_direction = (point.getX() - myFighter.getX()) / dist;
            y_direction = (point.getY() - myFighter.getY()) / dist;
        }
        myPhysicsEngine = physicsEngine;

    }


    public MotionAction (FighterBody r,
                         double x,
                         double y,
                         PhysicsEngine physicsEngine)
    {
        myFighter = r;
        x_direction = x;
        y_direction = y;
        myPhysicsEngine = physicsEngine;
    }


    @Override
    public void performAction (long elapsed_time)
    {
        if (y_direction >= 0 || myFighter.getMyTimer(0).action(elapsed_time))
        {
            myPhysicsEngine.process(this, elapsed_time);
        }
        done = true;
    }


    public FighterBody getFighterBody ()
    {
        return myFighter;
    }


    public double getVectorX ()
    {
        return x_direction;
    }


    public double getVectorY ()
    {
        return y_direction;
    }


    public static MotionAction LEFT (FighterBody fighter,
                                     PhysicsEngine physicsEngine)
    {
        return new MotionAction(fighter, -1, 0, physicsEngine);
    }


    public static MotionAction RIGHT (FighterBody fighter,
                                      PhysicsEngine physicsEngine)
    {
        return new MotionAction(fighter, 1, 0, physicsEngine);
    }


    public static MotionAction UP (FighterBody fighter,
                                   PhysicsEngine physicsEngine)
    {
        return new MotionAction(fighter, 0, -1, physicsEngine);
    }


    public static MotionAction DOWN (FighterBody fighter,
                                     PhysicsEngine physicsEngine)
    {
        return new MotionAction(fighter, 0, 1, physicsEngine);
    }


    public static MotionAction Gravity (FighterBody fighter,
                                        double gravity,
                                        PhysicsEngine physicsEngine)
    {
        return new MotionAction(fighter, 0, gravity, physicsEngine);
    }


    @Override
    public boolean isDone (long elapsedTime)
    {
        return done;
    }

}
