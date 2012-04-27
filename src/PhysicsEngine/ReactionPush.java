package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;


/**
 * this class works for the case that one push the other and go together
 * 
 * @author Donghe
 */
public class ReactionPush extends Reaction
{

    @Override
    public void act (SpriteTemplate spriteOne,
                     SpriteTemplate spriteTwo,
                     PhysicsEngine physicsEngine)
    {
        Point2D p1 = spriteOne.getMoveBy();
        Point2D p2 = spriteTwo.getMoveBy();
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        dx1 = x1;
        dy1 = y1;
        dx2 = x2;
        dy2 = y2;
        CollisionStatus status1 = spriteOne.getCollisionStatus();
        CollisionStatus status2 = spriteTwo.getCollisionStatus();

        //horizontal collision
        if (status1.getRight() && status2.getLeft())
        {
            if (x1 > x2)
            {
                dx1 = (x1 + x2) / 2;
                dx2 = (x1 + x2) / 2;
            }
        }
        else if (status1.getLeft() && status2.getRight())
        {
            if (x1 < x2)
            {
                dx1 = (x1 + x2) / 2;
                dx2 = (x1 + x2) / 2;
            }
        }

        //vertical collision
        if (status1.getDown() && status2.getUp())
        {
            if (spriteTwo.getCollisionStatus().getStandOnSth())
            {
                dy1 = Math.min(y1, 0);
            }
            else
            {
                if (y1 > y2)
                {
                    dy1 = (y1 + y2) / 2;
                    dy2 = (y1 + y2) / 2;
                }
            }
        }
        else if (status1.getUp() && status2.getDown())
        {
            if (spriteOne.getCollisionStatus().getStandOnSth())
            {
                dy2 = Math.min(y2, 0);
            }
            else
            {
                if (y1 < y2)
                {
                    dy1 = (y1 + y2) / 2;
                    dy2 = (y1 + y2) / 2;
                }
            }

        }

        setNextLocationIncrement(spriteOne, spriteTwo, physicsEngine);
    }

}
