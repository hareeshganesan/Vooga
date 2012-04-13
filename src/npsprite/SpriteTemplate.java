package npsprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import events.CompositeEvent;

import npsprite.SpriteID.GroupID;


//TODO: ensure that all sprites must pass in a groupID when they're created
//TODO: COPY AND PASTE SPRITE, REMOVE OTHER ID STUFF SO NO CONFUSION CREATED
// all sprites in fighting game extend this template. No animation, that will be handled by spritetree
@SuppressWarnings("serial")
public abstract class SpriteTemplate extends Sprite {
    
    double DEFAULT_SPEED = 0.1;
    
    private ArrayList<CompositeEvent> myCollisions = new ArrayList<CompositeEvent>();
    protected SpriteID myID;

    public SpriteTemplate(GroupID g) {
        super();
        createSpriteID(g);
    }

    public SpriteTemplate(BufferedImage b, GroupID g) {
        super(b);
        createSpriteID(g);
    }

    /**
     * Creates new Sprite with specified image and location.
     */
    public SpriteTemplate(BufferedImage image, GroupID g, double d, double e) {
        super(image, d, e);
        createSpriteID(g);
    }
    
    protected abstract void createSpriteID(GroupID g);

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

    public SpriteID getSpriteID() { // I don't know why you would need this
        return myID;
    }

    /* Wrapping spriteID methods for easier calling */
    public GroupID getGroupID() {
        return myID.getGroupID();
    }
    public boolean hasHealth() {
        return myID.hasHealth();
    }
    public boolean doesDamage() {
        return myID.doesDamage();
    }
    public boolean spawns() {
        return myID.spawns();
    }
    public boolean attaches() {
        return myID.attaches();
    }

    
    /* COLLISION STUFF */
    public void addCollisionEvent(CompositeEvent c) {
        myCollisions.add(c);
    }

    /**
     * Called when a collision between this sprite and another is detected. The
     * physics engine will have already checked that these sprites are both
     * active and have different groupIDs
     */
    public void collisionAction(SpriteTemplate otherSprite) {
        CompositeEvent act = null;
        for (CompositeEvent c : myCollisions) {
            if (c.getGroupID() == otherSprite.getGroupID()) {
                act = c;
                break;
            }
        }
        if (act != null) {
            act.performActions(this, otherSprite);
        }
    }


    @Override
    public void render(Graphics2D pen) {
        if (this.isActive()) {
            super.render(pen);
        }
    }

    @Override
    public void update(long elapsedTime) {
        if (this.isActive()) {
            super.update(elapsedTime);
        }
    }

}
