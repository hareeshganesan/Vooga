package action;

import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import PhysicsEngine.PhysicsEngine;
import game.CombatInstance;
import npsprite.FighterBody;
import npsprite.SpriteTemplate;

//import sprite.FighterSprite;

/**
 * Now with gravity and ability not to run into things and get stuck!!!
 * @author Hareesh
 *
 */
public class AdvancedFollowAction implements Action
{
    FighterBody myEnemy;
    FighterBody myFighter;
    CombatInstance myLevel;
    PhysicsEngine myPhysicsEngine;

    boolean done;
    
    public AdvancedFollowAction (FighterBody fighter, FighterBody enemy, CombatInstance c, PhysicsEngine p)
    {
        myFighter = fighter;
        myEnemy = enemy;
        myLevel = c;
        myPhysicsEngine = p;

    }


    @Override
    public void performAction (long elapsedTime)
    {
        Point2D dest = myEnemy.getCurrentLocation();        
        double increment = 10;
        AffineTransform a;

        while(isPathCollision(dest) && increment<181){
            a = AffineTransform.getRotateInstance(Math.toRadians(increment), myFighter.getX(), myFighter.getY());
            dest = a.transform(dest, dest);
            fixPoint(dest);
            System.out.println(increment+" "+dest.toString());
            if(!isPathCollision(dest))
                break;
            else{
                a = AffineTransform.getRotateInstance(Math.toRadians(increment), myFighter.getX(), myFighter.getY());
                dest = a.transform(dest, dest);
                fixPoint(dest);
                if(!isPathCollision(dest))
                    break;
                else{
                    dest = myEnemy.getCurrentLocation();
                    increment += 4;
                }
                    
            }
        }
        

        MotionAction f =
                new MotionAction(myFighter, dest, myPhysicsEngine);
        f.performAction(elapsedTime);
        done = true;
    }

    
    private void fixPoint (Point2D dest)
    {
        double x = dest.getX();
        double y = dest.getY();
        if(x<0)
            x = 0;
        if(y<0)
            y=0;
        dest.setLocation(x, y);
    }


    public boolean isPathCollision(Point2D dest){
        ArrayList<SpriteTemplate> objects = myLevel.getObstacles();
        Line2D line = new Line2D.Double(myFighter.getCurrentLocation(), dest);
        for(SpriteTemplate s : objects)
            if(line.intersects(new Rectangle2D.Double(s.getX(),s.getY(),s.getWidth(), s.getHeight()))){
                return true;
            }
        System.out.println("clear");
        return false;
    }
    

    public boolean isDone (long elapsedTime)
    {
        return done;
    }

}
