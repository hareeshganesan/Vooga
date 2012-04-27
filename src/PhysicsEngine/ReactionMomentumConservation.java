package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;


/**
 * this reaction is for the case that the collision under the law of momentum
 * conservation and the law of energy conservation
 * 
 * @author Donghe
 */
public class ReactionMomentumConservation extends Reaction
{

    @Override
    public void act (SpriteTemplate spriteOne,
                     SpriteTemplate spriteTwo,
                     PhysicsEngine physicsEngine)
    {
        Point2D moveBy1 = spriteOne.getMoveBy();
        Point2D moveBy2 = spriteTwo.getMoveBy();

        double x1 = moveBy1.getX();
        double y1 = moveBy1.getY();
        double x2 = moveBy2.getX();
        double y2 = moveBy2.getY();
        double m1 = spriteOne.getMass();
        double m2 = spriteTwo.getMass();
        CollisionStatus status1 = spriteOne.getCollisionStatus();
        CollisionStatus status2 = spriteTwo.getCollisionStatus();

        dx1 = x1;
        dx2 = x2;
        dy1 = y1;
        dy2 = y2;

        if ((status1.getLeft() && status2.getRight()) ||
            (status1.getRight() && status2.getLeft()))
        {
            dx1 = getIncrement(m1, m2, x1, x2);
            dx2 = getIncrement(m2, m1, x2, x1);
        }
        if ((status1.getUp() && status2.getDown()) ||
            (status1.getDown() && status2.getUp()))
        {
            dy1 = getIncrement(m1, m2, y1, y2);
            dy2 = getIncrement(m2, m1, y2, y1);
        }

        setNextLocationIncrement(spriteOne, spriteTwo, physicsEngine);

    }


    /**
     * get the increment by the law of momentum conservation and the law of
     * energy conservation
     */
    private double getIncrement (double m1,
                                 double m2,
                                 double speed1,
                                 double speed2)
    {
        return (m1 - m2) * speed1 / (m1 + m2) + 2 * m2 * speed2 / (m1 + m2);
    }

}
