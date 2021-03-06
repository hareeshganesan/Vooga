package PhysicsEngine;

import npsprite.SpriteTemplate;


/**
 * this works for the case that one sprite punch another to a certain position
 * For example, punch some one to the wall
 * 
 * @author Donghe
 */
public class ReactionPunch extends Reaction
{

    private final double STOP = 0;
    private double BoundLeft = 0;
    private double BoundUp = 0;
    private double BoundRight = 544;
    private double BoundDown = 544;


    @Override
    public void act (SpriteTemplate spriteOne,
                     SpriteTemplate spriteTwo,
                     PhysicsEngine physicsEngine)
    {
        dx1 = spriteOne.getMoveBy().getX();
        dy1 = spriteOne.getMoveBy().getY();
        dx2 = spriteTwo.getMoveBy().getX();
        dy2 = spriteTwo.getMoveBy().getY();
        CollisionStatus status1 = spriteOne.getCollisionStatus();
        CollisionStatus status2 = spriteTwo.getCollisionStatus();

        if (status1.getRight() && status2.getLeft())
        {
            if (spriteOne.getMoveBy().getX() == STOP) dx1 = getLeft(spriteOne);
            if (spriteTwo.getMoveBy().getX() == STOP) dx2 = getRight(spriteTwo);
        }
        else if (status1.getLeft() && status2.getRight())
        {
            if (spriteOne.getMoveBy().getX() == STOP) dx1 = getRight(spriteOne);
            if (spriteTwo.getMoveBy().getX() == STOP) dx2 = getLeft(spriteTwo);
        }
        if (status1.getDown() && status2.getUp())
        {
            if (spriteOne.getMoveBy().getY() == STOP) dy1 = getDown(spriteOne);
            if (spriteTwo.getMoveBy().getY() == STOP) dy2 = getUp(spriteTwo);
        }
        else if (status1.getUp() && status2.getDown())
        {
            if (spriteOne.getMoveBy().getY() == STOP) dy1 = getUp(spriteOne);
            if (spriteTwo.getMoveBy().getY() == STOP) dy2 = getDown(spriteTwo);
        }
        setNextLocationIncrement(spriteOne, spriteTwo, physicsEngine);
    }


    /**
     * set the bound that the sprite needs to go to
     */
    public void setBound (double up, double down, double left, double right)
    {
        BoundUp = up;
        BoundDown = down;
        BoundRight = right;
        BoundDown = down;
    }


    /**
     * the left bound of this sprite
     */
    private double getLeft (SpriteTemplate sprite)
    {
        return BoundLeft - sprite.getX();
    }


    /**
     * the right bound of this sprite
     */
    private double getRight (SpriteTemplate sprite)
    {
        return BoundRight - sprite.getWidth() - sprite.getX();
    }


    /**
     * the up bound of this sprite
     */
    private double getUp (SpriteTemplate sprite)
    {
        return BoundUp - sprite.getY();
    }


    /**
     * the left down of this sprite
     */
    private double getDown (SpriteTemplate sprite)
    {
        return BoundDown - sprite.getHeight() - sprite.getY();
    }

}
