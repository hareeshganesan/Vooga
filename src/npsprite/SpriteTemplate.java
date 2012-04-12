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

//TODO: COPY AND PASTE SPRITE, REMOVE OTHER ID STUFF SO NO CONFUSION CREATED
// all sprites in fighting game extend this template. No animation, that will be handled by spritetree
@SuppressWarnings("serial")
public abstract class SpriteTemplate extends Sprite{
    double DEFAULT_SPEED = 0.1;

    private int myStatus; // IS STATUS NECESSARY?
    private ArrayList<CollisionEvent> myCollisions = new ArrayList<CollisionEvent>();
    protected SpriteID myID;

    public SpriteTemplate() {
        super();
    }

    public SpriteTemplate(BufferedImage b) {
        super(b);
    }

    /**
     * Creates new Sprite with specified image and location.
     */
    public SpriteTemplate(BufferedImage image, double d, double e) {
        super(image, d, e);
    }

    /**
     * If sprite moves after collision, returns a MotionAction object to the
     * physics engine for further movement and collision checking. TODO the
     * physics engine will have to implement this. 
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

    //TODO: figure out setting sprite ids - is it in subclasses of attachable only?
//    public void setSpriteID(SpriteValues.Id i) {
//        myID = i;
//    }

    public SpriteID getSpriteID() {
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


    @Override
    public void render(Graphics2D pen) {
        if (this.isActive()) {
            super.render(pen);
        }
    }

    @Override
    public void update(long elapsedTime) {
        if (this.isActive()) { // TODO FIGURE OUT GARBAGE COLLECTION FOR
                               // VOLATILE SPRITES
            super.update(elapsedTime);
        }
    }

}
