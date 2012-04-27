package PhysicsEngine;

import npsprite.SpriteTemplate;
import action.MotionAction;
import com.golden.gamedev.GameEngine;


/**
 * This is the super class of physics engine if developers wants to create their
 * own engine just make a subclass
 * 
 * @author Donghe
 */
public abstract class PhysicsEngine
{

    protected double myBoundUp = 0;
    protected double myBoundLeft = 0;
    protected double myBoundRight;
    protected double myBoundDown;

    private GameEngine myGameEngine;


    public PhysicsEngine (GameEngine gameEngine)
    {
        myGameEngine = gameEngine;
        myBoundRight = myGameEngine.getWidth();
        myBoundDown = myGameEngine.getHeight();
    }


    /**
     * deal with any MotionAction from inputHandler, calculate new location
     * 
     * @param motionAction the sprite's motion
     * @param elapsedTime the update frequency
     */
    public abstract void process (MotionAction motionAction, long elapsedTime);


    /**
     * set this sprite's location increment
     * 
     * @param sprite
     * @param dx the horizontal increment
     * @param dy the vertical increment
     */
    public abstract void setNextLocationIncrement (SpriteTemplate sprite,
                                                   double dx,
                                                   double dy);


    /**
     * check whether the sprite is out of left bound
     * 
     * @param sprite
     * @param dx the horizontal increment
     * @return whether the sprite is out of left bound
     */
    protected boolean isOutLeft (SpriteTemplate sprite, double dx)
    {
        return sprite.getX() + dx < myBoundLeft;
    }


    /**
     * check whether the sprite is out of right bound
     * 
     * @param sprite
     * @param dx the horizontal increment
     * @return whether the sprite is out of right bound
     */
    protected boolean isOutRight (SpriteTemplate sprite, double dx)
    {
        return sprite.getX() + dx + sprite.getWidth() > myBoundRight;
    }


    /**
     * check whether the sprite is out of up bound
     * 
     * @param sprite
     * @param dy the vertical increment
     * @return whether the sprite is out of up bound
     */
    protected boolean isOutUp (SpriteTemplate sprite, double dy)
    {
        return sprite.getY() + dy < myBoundUp;
    }


    /**
     * check whether the sprite is out of down bound
     * 
     * @param sprite
     * @param dy the vertical increment
     * @return whether the sprite is out of down bound
     */
    protected boolean isOutDown (SpriteTemplate sprite, double dy)
    {
        return sprite.getY() + dy + sprite.getHeight() > myBoundDown;
    }


    /**
     * set the bound for this engine the default bound is the bound of this game
     * 
     * @param up the up bound
     * @param down the down bound
     * @param left the left bound
     * @param right the right bound
     */
    public void setBound (double up, double down, double left, double right)
    {
        myBoundUp = up;
        myBoundLeft = left;
        myBoundRight = right;
        myBoundDown = down;
    }
}
