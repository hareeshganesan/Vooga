package npsprite;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;


// all sprites in fighting game extend this template. No animation, that will be handled by spritetree
public abstract class SpriteTemplate extends Sprite
{
    // defaults
    
    //TODO: DEFAULT SPRITES HAVE 0 HEALTH
    private int MIN_HEALTH = 5;
    private int MAX_HEALTH = 50;
    //WILL NEED TO OVERRIDE HEALTHS IN SUBSPRITES
    
    double DEFAULT_SPEED = 0;
    double DEFAULT_DAMAGE = 0;
    
    private int myHealth=0;
    private double myDamage=0;
    
    private int myDirection;
    private int myStatus; //IS STATUS NECESSARY?

    public SpriteTemplate ()
    {
        super();
        
    }

    public SpriteTemplate (BufferedImage b)
    {
        super(b);
    }

    public SpriteTemplate(BufferedImage image, double d, double e) {
        super(image,d, e);
    }

    public abstract void collisionAction (SpriteTemplate otherSprite);

    public void setDefaultSpeed (double speed){
        DEFAULT_SPEED=speed;
    }
    /**
     * speed reset to default value, same for x and y directions for now
     */
    public void resetSpeed(){
        this.setSpeed(DEFAULT_SPEED, DEFAULT_SPEED);
    }

    
    public void setDamage (double d)
    {
        myDamage = d;
    }
    public double getDamage ()
    {
        return myDamage;
    }
    /**
     * Changes maximum health to @param change Resets to full health
     */
    public void setMaxHealth(int change) {
        if (change <= MIN_HEALTH) {
            MAX_HEALTH = MIN_HEALTH;
            myHealth = MAX_HEALTH;
        } else {
            MAX_HEALTH = change;
            myHealth = MAX_HEALTH;
        }
    }

    public int getMaxHealth ()
    {
        return MAX_HEALTH;
    }
    
    public void addHealth (int h)
    {
        myHealth += h;
        wrapHealth();
    }

    private void wrapHealth ()
    {
        if (myHealth > MAX_HEALTH)
        {
            myHealth = MAX_HEALTH;
        }
        if (myHealth < 0)
        {
            myHealth = 0;
        }
    }



    public int getHealth ()
    {
        return myHealth;
    }

    // TODO: GET BOUNDS FROM WINDOW SIZE
    protected abstract Point2D confineBounds (double dx, double dy);

    //TODO: MOVE THESE INTO SUBCLASSES - IMMOBILE BLOCKS HAVE NO DIRECTION/SPEED
    public double getSpeed() {
        return DEFAULT_SPEED;
    }

    public int getDirection() {
        return myDirection;
    }

    protected void setDirection(int dir) {
        myDirection=dir;
    }

    
}
