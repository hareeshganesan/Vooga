package action;

import java.awt.geom.Point2D;
import sprite.FighterSprite;
import PhysicsEngine.FightPhysicsEngine;


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

    public MotionAction(){
        //created for subclasses
    }
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

    public MotionAction (FighterSprite r, double x, double y)
    {
        myFighter = r;
        x_direction = x;
        y_direction = y;
    }
    


    @Override
    public void performAction (long elapsed_time)
    {
    	 FightPhysicsEngine physicsEngine=new FightPhysicsEngine(this);
         physicsEngine.process(elapsed_time);
    }
    
    public FighterSprite getFighterSprite(){
    	return myFighter;
    }
    public double getVectorX(){
    	return x_direction;
    }
    public double getVectorY(){
    	return y_direction;
    }
    public static MotionAction LEFT(FighterSprite fighter){
        return new MotionAction(fighter, -1, 0);
    }
    public static MotionAction RIGHT(FighterSprite fighter){
        return new MotionAction(fighter, 1, 0);
    }
    public static MotionAction UP(FighterSprite fighter){
        return new MotionAction(fighter, 0, -1);
    }
    public static MotionAction DOWN(FighterSprite fighter){
        return new MotionAction(fighter, 0, 1);
    }
}
