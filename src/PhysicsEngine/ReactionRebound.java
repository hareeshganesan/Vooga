package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;


/**
 * this class works for the collision between sprite and a block
 * 
 * @author Donghe
 */
public class ReactionRebound extends Reaction
{

    private final double STOP = 0;
    private double reboundFactor = 20.0;
    private boolean reboundUp = false;
    private boolean reboundDown = false;
    private boolean reboundLeft = false;
    private boolean reboundRight = false;


    public ReactionRebound ()
    {

    }


    public ReactionRebound (double factor)
    {
        reboundFactor = factor;
    }


    @Override
    public void act (SpriteTemplate spriteOne,
                     SpriteTemplate spriteTwo,
                     PhysicsEngine physicsEngine)
    {
        if (spriteOne.getClass().equals(PlatformBlock.class))
        {
            rebound(spriteTwo, spriteOne, physicsEngine);
        }
        else if (spriteTwo.getClass().equals(PlatformBlock.class))
        {
            rebound(spriteOne, spriteTwo, physicsEngine);
        }

    }


    /**
     * the rebound distance
     */
    private void rebound (SpriteTemplate fighterSprite,
                          SpriteTemplate block,
                          PhysicsEngine physicsEngine)
    {
        Point2D moveBy = fighterSprite.getMoveBy();
        double dx = moveBy.getX();
        double dy = moveBy.getY();

        if (!reboundUp && fighterSprite.getCollisionStatus().getDown())
        {
            dy = Math.min(dy, STOP);
        }
        else if (!reboundDown && fighterSprite.getCollisionStatus().getUp())
        {
            dy = Math.max(dy, STOP);
        }
        else if (!reboundRight && fighterSprite.getCollisionStatus().getLeft())
        {
            dx = Math.max(dx, STOP);
        }
        else if (!reboundLeft && fighterSprite.getCollisionStatus().getRight())
        {
            dx = Math.min(dx, STOP);
        }
        else
        {
            dx = -moveBy.getX() * reboundFactor;
            dy = -moveBy.getY() * reboundFactor;
        }

        physicsEngine.setNextLocationIncrement(fighterSprite, dx, dy);
    }


    /**
     * set the factor so that we get a rebound distance
     * 
     * @param factor the rebound factor
     */
    public void setReboundFactor (double factor)
    {
        reboundFactor = factor;
    }


    /**
     * active the up rebound
     */
    public void setUp (boolean up)
    {
        reboundUp = up;
    }


    /**
     * active the down rebound
     */
    public void setDown (boolean down)
    {
        reboundDown = down;
    }


    /**
     * active the left rebound
     */
    public void setLeft (boolean left)
    {
        reboundLeft = left;
    }


    /**
     * active the right rebound
     */
    public void setRight (boolean right)
    {
        reboundRight = right;
    }

}
