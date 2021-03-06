package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;
import npsprite.properties.PropertyObject;
import PhysicsEngine.CollisionStatus;
import events.CollisionEvent;
import events.HealthEvent;


// all sprites in fighting game extend this template.
@SuppressWarnings("serial")
public class SpriteTemplate extends Sprite implements Cloneable
{

    //distance moved=default speed*elapsedTime
    double defaultSpeed = 0.3;
    double myMass = 200;

    protected HashMap<String, PropertyObject> myProperties =
        new HashMap<String, PropertyObject>();
    protected HashMap<GroupID, CollisionEvent> myCollisions =
        new HashMap<GroupID, CollisionEvent>();

    protected GroupID myID;

    protected CollisionStatus myCollisionStatus = new CollisionStatus();
    protected Point2D moveBy = new Point2D.Double();

    public SpriteTemplate(GroupID g) {
        super();
        myID = g;
        myCollisions.put(GroupID.PLATFORM, HealthEvent.getInstanceOf());
    }


    public SpriteTemplate (BufferedImage b, GroupID g)
    {
        super(b);
        myID = g;
    }

    /**
     * Creates new Sprite with specified image and location.
     */
    public SpriteTemplate(BufferedImage image, GroupID g, double d, double e) {
        super(image, d, e);
        myID = g;
    }
    public GroupID getGroupID ()
    {
        return myID;
    }
    /**
     * create a new instance of this sprite, with same properties/collisions
     */
    @SuppressWarnings("unchecked")
    public SpriteTemplate clone ()
    {
        SpriteTemplate c = new SpriteTemplate(this.getImage(), myID);
        c.addCollisionEvents((HashMap<GroupID, CollisionEvent>) myCollisions.clone());
        HashMap<String, PropertyObject> newProperties =
            new HashMap<String, PropertyObject>();
        for (Entry<String, PropertyObject> e : myProperties.entrySet())
        {
            newProperties.put(e.getKey(), e.getValue().clone());
        }
        c.addProperties(newProperties);
        return c;
    }


    /* SPEED STUFF */
    public void setDefaultSpeed (double speed)
    {
        defaultSpeed = speed;
    }

    public double getSpeed ()
    {
        return defaultSpeed;
    }


    /* MASS - each sprite has a default mass of 50, used in physics engine */
    public void setMass (double mass)
    {
        myMass = mass;
    }


    public double getMass ()
    {
        return myMass;
    }


    /* PROPERTIES STUFF */
    public void addProperty (String n, PropertyObject p)
    {
        myProperties.put(n, p);
    }


    public void addProperties (HashMap<String, PropertyObject> e)
    {
        myProperties.putAll(e);
    }


    public boolean hasProperty (String name)
    {
        return myProperties.containsKey(name);
    }


    public PropertyObject getProperty (String name)
    {
        return myProperties.get(name);
    }


    /* COLLISION STUFF */
    public void addCollisionEvent (GroupID g, CollisionEvent c)
    {
        myCollisions.put(g, c);
    }


    public void addCollisionEvents (HashMap<GroupID, CollisionEvent> e)
    {
        myCollisions.putAll(e);
    }

    /**
     * Called when a collision between this sprite and another is detected. The
     * physics engine will have already checked that these sprites are both
     * active and have different groupIDs
     */
    public void collisionAction (SpriteTemplate otherSprite)
    {
//        System.out.println(myID+" trying to collide "+myCollisions.size());
        CollisionEvent act = myCollisions.get(otherSprite.getGroupID());
        if (act != null)
        {
//            System.out.println("acting");
            act.performAction(this, otherSprite);
        }
    }

    public CollisionStatus getCollisionStatus() {
        return myCollisionStatus;
    }

    public void setCollisionStatus(CollisionStatus c) {
        myCollisionStatus = c;
    }


    /* PHYSICS ENGINE MOVEMENT */
    public void setNextLocationIncrement (Point2D nextLocation)
    {
        if (!myCollisionStatus.getStatus())
        {
            this.moveBy =
                new Point2D.Double(moveBy.getX() + nextLocation.getX(),
                                   moveBy.getY() + nextLocation.getY());

        }
        else
        {
            moveBy = nextLocation;
        }
        // System.out.println("setting loc"+moveBy.getX()+","+moveBy.getY());
    }

    public Point2D getCurrentLocation ()
    {
        return new Point2D.Double(getX() + moveBy.getX(), getY() +
                                                          moveBy.getY());
    }


    public Point2D getMoveBy ()
    {
        return moveBy;
    }

    @Override
    public void render(Graphics2D pen) {
        if (this.isActive()) {
            super.render(pen);
        }
    }


    public void update (long elapsedTime)
    {
        if (this.isActive())
        {
            myCollisionStatus.setDefault();
            for (PropertyObject p : myProperties.values())
            {
                CollisionEvent c = p.update(elapsedTime);
                if (c != null)
                {
                    c.performAction(this, null); //BE VERY, VERY CAREFUL - maybe TODO put more safety restrictions
                }
            }
            if (moveBy.getX() != 0 || moveBy.getY() != 0)
            {
                move(moveBy.getX(), moveBy.getY());
            }
            moveBy.setLocation(0, 0); // moveBy only work for one time then set to
                                      // zero

            super.update(elapsedTime);
        }
    }

}
