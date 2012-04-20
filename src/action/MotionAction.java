package action;

import java.awt.geom.Point2D;

import npsprite.FighterBody;
//import sprite.FighterSprite;
import PhysicsEngine.FightPhysicsEngine;


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

    public MotionAction (FighterBody fighter, Point2D point)
    {
        myFighter = fighter;
        double dist = myFighter.getCurrentLocation().distance(point);
        if (dist > 0)
        {
            x_direction = (point.getX() - myFighter.getX()) / dist;
            y_direction = (point.getY() - myFighter.getY()) / dist;
        }

    }

    public MotionAction (FighterBody r, double x, double y)
    {
        myFighter = r;
        x_direction = x;
        y_direction = y;
    }


    @Override
    public void performAction (long elapsed_time)
    {
        FightPhysicsEngine physicsEngine = new FightPhysicsEngine(this);
        physicsEngine.process(elapsed_time);
        done = true;
    }
    
    public FighterBody getFighterBody(){
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
    public static MotionAction LEFT(FighterBody fighter){
        return new MotionAction(fighter, -1, 0);
    }
    public static MotionAction RIGHT(FighterBody fighter){
        return new MotionAction(fighter, 1, 0);
    }
    public static MotionAction UP(FighterBody fighter){
        return new MotionAction(fighter, 0, -1);
    }
    public static MotionAction DOWN(FighterBody fighter){
        return new MotionAction(fighter, 0, 1);
    }

}
