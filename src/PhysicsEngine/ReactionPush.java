package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;



public class ReactionPush extends Reaction
{

    @Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2,
			PhysicsEngine physicsEngine)
    {
        
        Point2D p1 = ps1.getMoveBy();
        Point2D p2 = ps2.getMoveBy();
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        // same direction
        if ((p1.getX() > 0 && p2.getX() > 0) ||
            (p1.getX() < 0 && p2.getX() < 0))
        {
            x1 = (p1.getX() + p2.getX()) / 2;
            x2 = (p1.getX() + p2.getX()) / 2;
        }
        if ((p1.getY() > 0 && p2.getY() > 0) ||
            (p1.getY() < 0 && p2.getY() < 0))
        {
            y1 = (p1.getY() + p2.getY()) / 2;
            y2 = (p1.getY() + p2.getY()) / 2;
        }
        // collided toward
        if ((p1.getX() > 0 && p2.getX() < 0 && ps1.getX() < ps2.getX()) ||
            (p1.getX() < 0 && p2.getX() > 0 && ps1.getX() > ps2.getX()))
        {
            x1 = (p1.getX() + p2.getX()) / 2;
            x2 = (p1.getX() + p2.getX()) / 2;
        }
        if ((p1.getY() > 0 && p2.getY() < 0 && ps1.getY() < ps2.getY()) ||
            (p1.getY() < 0 && p2.getY() > 0 && ps1.getY() > ps2.getY()))
        {
            y1 = (p1.getY() + p2.getY()) / 2;
            y2 = (p1.getY() + p2.getY()) / 2;
        }
        // away
        if ((p1.getX() > 0 && p2.getX() < 0 && ps1.getX() >= ps2.getX()) ||
            (p1.getX() < 0 && p2.getX() > 0 && ps1.getX() <= ps2.getX()))
        {
            x1 = p1.getX();
            x2 = p2.getX();
        }
        if ((p1.getY() > 0 && p2.getY() < 0 && ps1.getY() >= ps2.getY()) ||
            (p1.getY() < 0 && p2.getY() > 0 && ps1.getY() <= ps2.getY()))
        {
            y1 = p1.getY();
            y2 = p2.getY();
        }
        // one stop, one toward
        if ((p1.getX() > 0 && p2.getX() == 0 && ps1.getX() <= ps2.getX()) ||
            (p1.getX() < 0 && p2.getX() == 0 && ps1.getX() >= ps2.getX()) ||
            (p2.getX() > 0 && p1.getX() == 0 && ps2.getX() <= ps1.getX()) ||
            (p2.getX() < 0 && p1.getX() == 0 && ps2.getX() >= ps1.getX()))
        {
            x1 = (p1.getX() + p2.getX()) / 2;
            x2 = (p1.getX() + p2.getX()) / 2;
        }
        if ((p1.getY() > 0 && p2.getY() == 0 && ps1.getY() <= ps2.getY()) ||
            (p1.getY() < 0 && p2.getY() == 0 && ps1.getY() >= ps2.getY()) ||
            (p2.getY() > 0 && p1.getY() == 0 && ps2.getY() <= ps1.getY()) ||
            (p2.getY() < 0 && p1.getY() == 0 && ps2.getY() >= ps1.getY()))
        {
            y1 = (p1.getY() + p2.getY()) / 2;
            y2 = (p1.getY() + p2.getY()) / 2;
        }

        // one stop, one away
        if ((p1.getX() > 0 && p2.getX() == 0 && ps1.getX() > ps2.getX()) ||
            (p1.getX() < 0 && p2.getX() == 0 && ps1.getX() < ps2.getX()) ||
            (p2.getX() > 0 && p1.getX() == 0 && ps2.getX() > ps1.getX()) ||
            (p2.getX() < 0 && p1.getX() == 0 && ps2.getX() < ps1.getX()))
        {
            x1 = p1.getX();
            x2 = p2.getX();
        }
        if ((p1.getY() > 0 && p2.getY() == 0 && ps1.getY() > ps2.getY()) ||
            (p1.getY() < 0 && p2.getY() == 0 && ps1.getY() < ps2.getY()) ||
            (p2.getY() > 0 && p1.getY() == 0 && ps2.getY() > ps1.getY()) ||
            (p2.getY() < 0 && p1.getY() == 0 && ps2.getY() < ps1.getY()))
        {
            y1 = p1.getY();
            y2 = p2.getY();
        }

        physicsEngine.setNextLocationIncrement(ps1, x1, y1);
        physicsEngine.setNextLocationIncrement(ps2, x2, y2);
    }

}
