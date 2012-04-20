package sprite;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;


// default speed is 0 - stationary
// TODO: compareto?
// most basic sprite
// TODO How will non-player sprites interact with physics engine?
public abstract class NonPlayerSprite extends SpriteTemplate
{

    //is this necessary?
    protected boolean isHit;


    public NonPlayerSprite (BufferedImage[] b)
    {
        super(b);
        isHit = false;
    }


    public NonPlayerSprite ()
    {}


    protected Point2D confineBounds (double dx, double dy)
    {
        if (!this.isOnScreen())
        {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY());
        }
        return new Point2D.Double(dx, dy);
    }


    public void update (long elapsedTime)
    {
        if (this.isActive())
        { //DEFAULT: DON'T UPDATE IF INACTIVE
            super.update(elapsedTime);
            confineBounds(DEFAULT_DAMAGE, DEFAULT_DAMAGE);
        }
    }
}
