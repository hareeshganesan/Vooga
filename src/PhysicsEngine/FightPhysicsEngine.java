package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;
import action.MotionAction;
import com.golden.gamedev.GameEngine;


/**
 * the subclass of PhysicsEngine hope this can deal with most kinds of physics
 * problems
 * 
 * @author Donghe
 */
public class FightPhysicsEngine extends PhysicsEngine
{

    private double myBackgroundFactor = 1.0;
    private double myOutBoundDistance = 1;
    private double mySpeedFactor = 0.5;


    public FightPhysicsEngine (GameEngine gameEngine)
    {
        super(gameEngine);
    }


    @Override
    public void process (MotionAction motionAction, long elapsedTime)
    {
        SpriteTemplate sprite = motionAction.getFighterBody();
        double myVectorX = motionAction.getVectorX() * mySpeedFactor;
        double myVectorY = motionAction.getVectorY() * mySpeedFactor;
        double speed = sprite.getSpeed();
        double x = speed * elapsedTime * myVectorX;
        double y = speed * elapsedTime * myVectorY;
        setCollisionStatus(sprite, y);
        setNextLocationIncrement(sprite, x, y);
    }


    @Override
    public void setNextLocationIncrement (SpriteTemplate sprite,
                                          double dx,
                                          double dy)
    {

//        System.out.println("fightbefore"+dx+","+dy);

        double finalX = dx * myBackgroundFactor;
        double finalY = dy * myBackgroundFactor;

        if (isOutLeft(sprite, dx)) finalX = myOutBoundDistance;
        if (isOutRight(sprite, dx)) finalX = -myOutBoundDistance;
        if (isOutUp(sprite, dy)) finalY = myOutBoundDistance;
        if (isOutDown(sprite, dy))
        {
            finalY = myBoundDown - sprite.getY() - sprite.getHeight();
        }
//		System.out.println("fight"+finalX+","+finalY);
        sprite.setNextLocationIncrement(new Point2D.Double(finalX, finalY));

        // for debug
        // System.out.println(sprite.getGroupID() + "  Left:" + sprite.getX()
        // + "    Right:" + (sprite.getWidth() + sprite.getX())
        // + "    Top:" + sprite.getY() + "    Bottom:"
        // + (sprite.getHeight() + sprite.getY()));
    }


    /**
     * set the collision standing status
     */
    private void setCollisionStatus (SpriteTemplate sprite, double dy)
    {
        sprite.getCollisionStatus().setStandOnGound(isOutDown(sprite, dy));
    }


    /**
     * set a new speed factor for this physics engine its default value is 1.0
     * For example, if we develop this game in the water, maybe we need to set
     * it to 0.8
     * 
     * @param backgroundFactor the new factor
     */
    public void setBackgroundFactor (double backgroundFactor)
    {
        myBackgroundFactor = backgroundFactor;
    }


    /**
     * when sprites hit the up bound, left bound and right bound, we make them a
     * rebound distantce
     * 
     * @param outBoundDistance the rebound distance
     */
    public void setOutBoundDistance (double outBoundDistance)
    {
        myOutBoundDistance = outBoundDistance;
    }


    /**
     * a speed factor works for motion's speed, its default value is 0.5
     * 
     * @param speedFactor speed factor
     */
    public void setSpeedFactor (double speedFactor)
    {
        mySpeedFactor = speedFactor;
    }
}
