package sprite;

import java.awt.Graphics2D;


import java.awt.image.BufferedImage;


//default speed is 0 - stationary
//TODO: compareto?
//most basic sprite
//TODO How will non-player sprites interact with physics engine?
public abstract class NonPlayerSprite extends SpriteTemplate {
    protected double mySpeed;
    protected double myDamage;

    //is this necessary?
    protected boolean isHit;

    // defaults
    final double DEFAULT_SPEED = 0;
    final double DEFAULT_DAMAGE = 0;


    //

    public NonPlayerSprite (BufferedImage[] b)
    {
        super(b);
        mySpeed = DEFAULT_SPEED;
        myDamage = DEFAULT_DAMAGE;
        isHit = false;
    }

    public void setSpeed(double speed) {
        mySpeed = speed;
    }


    public void setDamage (int d)
    {
        myDamage = d;
    }


    public double getDamage ()
    {
        return myDamage;
    }


    protected void confineBounds ()
    {
        if (!this.isOnScreen())
        {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY());
        }
    }


    public void render (Graphics2D g)
    {
        super.render(g);
    }


    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
        confineBounds();
        //TODO QUADTREE CHECKS here?
    }
}
