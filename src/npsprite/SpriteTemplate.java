package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprite.SpriteValues;
import sprite.SpriteValues.Id;

import com.golden.gamedev.object.Sprite;

import action.Action;
import action.CollisionEvent;

// all sprites in fighting game extend this template. No animation, that will be handled by spritetree
@SuppressWarnings("serial")
public abstract class SpriteTemplate extends Sprite {
    // defaults
    private int MIN_HEALTH = 1;
    private int MAX_HEALTH = 50;

    double DEFAULT_SPEED = 0.1;
    double DEFAULT_DAMAGE = 0;
    //

    private double myHealth;
    private double myDamage = 0;

    private int myStatus; // IS STATUS NECESSARY?
    private ArrayList<CollisionEvent> myCollisions = new ArrayList<CollisionEvent>();
    private Id myID;

    public SpriteTemplate() {
        super();
        myHealth = MAX_HEALTH;
    }

    public SpriteTemplate(BufferedImage b) {
        super(b);
        myHealth = MAX_HEALTH;
    }

    // WHAT ARE D AND E?
    public SpriteTemplate(BufferedImage image, double d, double e) {
        super(image, d, e);

        myHealth = MAX_HEALTH;
    }

    /**
     * If sprite moves after collision, returns a MotionAction object to the
     * physics engine for further movement and collision checking. TODO the
     * physics engine will have to implement this. TODO COMPARETO SO IT IS ONLY
     * CALLED FOR ONE OF THE SPRITES PARTICIPATING IN COLLISION
     */
    public void collisionAction(SpriteTemplate otherSprite) {
        // TODO: MOVE THIS INTO PHYSICS ENGINE (don't check things that overlap
        // like fighter limbs)
        if (!this.isActive() || !otherSprite.isActive()) {
//            return null; //TODO STOP
        }
        if (otherSprite.getSpriteID() == this.getSpriteID()) {
            // System.out.println("fail");
//            return null; //TODO STOP
        }
        //

        CollisionEvent act = null;
        for (CollisionEvent c : myCollisions) {
            if (c.getSpriteID() == otherSprite.getSpriteID()) {
                act = c;
                break;
            }
        }
        if (act == null) {
//            return null; //TODO STOP
        }

        act.performActionBy(this);
//        return act.getMotion();
    }

    public void setSpriteID(SpriteValues.Id i) {
        myID = i;
    }

    public Id getSpriteID() {
        return myID;
    }

    public void addCollisionEvent(CollisionEvent c) {
        myCollisions.add(c);
    }

    public void setDefaultSpeed(double speed) {
        DEFAULT_SPEED = speed;
    }

    /**
     * speed reset to default value, same for x and y directions for now
     */
    public void resetSpeed() {
        this.setSpeed(DEFAULT_SPEED, DEFAULT_SPEED);
    }

    public double getSpeed() {
        return DEFAULT_SPEED;
    }

    public void setDamage(double d) {
        myDamage = d;
    }

    public double getDamage() {
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

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public void addHealth(double d) {
        myHealth += d;
        wrapHealth();
    }

    private void wrapHealth() {
        if (myHealth > MAX_HEALTH) {
            myHealth = MAX_HEALTH;
        }
        if (myHealth < 0) {
            myHealth = 0;
        }
    }

    public double getHealth() {
        return myHealth;
    }

    // TODO: GET BOUNDS FROM WINDOW SIZE
    protected abstract Point2D confineBounds(double dx, double dy);

    @Override
    public void render(Graphics2D pen) {
        if (this.isActive()) {
            super.render(pen);
        }
    }

    @Override
    public void update(long elapsedTime) {
        if (myHealth <= 0) {
            this.setActive(false);
        }
        if (this.isActive()) { // TODO FIGURE OUT GARBAGE COLLECTION FOR
                               // VOLATILE SPRITES
            super.update(elapsedTime);
        }
    }

}
