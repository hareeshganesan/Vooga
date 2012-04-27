package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import PhysicsEngine.CollisionStatus;

import npsprite.properties.PropertyObject;
import events.CollisionEvent;


// all sprites in fighting game extend this template. 
@SuppressWarnings("serial")
public class SpriteTemplate extends Sprite implements Cloneable{
    
    //distance moved=default speed*elapsedTime
    double defaultSpeed = 0.3;
    double myMass=200;
    
    private HashMap<String,PropertyObject> myProperties = new HashMap<String,PropertyObject>();
    private HashMap<GroupID,CollisionEvent> myCollisions = new HashMap<GroupID,CollisionEvent>();
    
    protected GroupID myID;
    

//    private boolean myCollisionStatus=false;
    protected CollisionStatus myCollisionStatus = new CollisionStatus();
    protected Point2D moveBy=new Point2D.Double();


    public SpriteTemplate (GroupID g)
    {
        super();
        myID=g;
    }
    public SpriteTemplate(BufferedImage b, GroupID g) {
        super(b);
        myID=g;
    }
    /**
     * Creates new Sprite with specified image and location.
     */
    public SpriteTemplate (BufferedImage image, GroupID g, double d, double e)
    {
        super(image, d, e);
        myID=g;
    }
    
    public GroupID getGroupID() {
        return myID;
    }
    /**
     * create a new instance of this sprite, with same properties/collisions
     */
    @SuppressWarnings("unchecked")
    public SpriteTemplate clone(){
        SpriteTemplate c=new SpriteTemplate(myID);
        c.addCollisionEvents((HashMap<GroupID, CollisionEvent>) myCollisions.clone()); //TODO: SHALLOW CLONE FOR THIS is okay, right?
        HashMap<String, PropertyObject> newProperties=new HashMap<String, PropertyObject>();
        for (Entry<String, PropertyObject> e:myProperties.entrySet()){
            newProperties.put(e.getKey(), e.getValue().clone());
        }
        c.addProperties(newProperties);
        return c;
    }

    /* SPEED STUFF */
    public void setDefaultSpeed(double speed) {
        defaultSpeed = speed;
    }
    public double getSpeed() {
        return defaultSpeed;
    }
    /* MASS - each sprite has a default mass of 50, used in physics engine */
    public void setMass(double mass){
        myMass=mass;
    }
    public double getMass(){
        return myMass;
    }
    
    /* PROPERTIES STUFF */
    public void addProperty(String name,PropertyObject p){
        myProperties.put(name, p);
    }
    public void addProperties(HashMap<String,PropertyObject>e){
        myProperties.putAll(e);
    }
    public boolean hasProperty(String name){
        return myProperties.containsKey(name);
    }
    public PropertyObject getProperty(String name){
        return myProperties.get(name);
    }

    /* COLLISION STUFF */
    public void addCollisionEvent(GroupID g,CollisionEvent c) {
        myCollisions.put(g, c);
    }
    public void addCollisionEvents(HashMap<GroupID,CollisionEvent>e) {
        myCollisions.putAll(e);
    }
    /**
     * Called when a collision between this sprite and another is detected. The
     * physics engine will have already checked that these sprites are both
     * active and have different groupIDs
     */
    public void collisionAction(SpriteTemplate otherSprite) {
        
        CollisionEvent act=myCollisions.get(otherSprite.getGroupID());
        if (act != null) {
            act.performAction(this, otherSprite);
        }
    }

//    public void setCollisionStatus(boolean b){
//        myCollisionStatus=b;
//    }
    

    /* PHYSICS ENGINE MOVEMENT */
    public void setNextLocationIncrement(Point2D nextLocation) {
        if(!myCollisionStatus.getStatus()){
            this.moveBy = new Point2D.Double(moveBy.getX()+nextLocation.getX(),
                moveBy.getY()+nextLocation.getY());
            
        }else{
            moveBy=nextLocation;
        }
//        System.out.println("setting loc"+moveBy.getX()+","+moveBy.getY());
    }
    public Point2D getCurrentLocation() {
        return new Point2D.Double(getX() + moveBy.getX(), getY()
                + moveBy.getY());
    }
    public Point2D getMoveBy() {
        return moveBy;
    }
    

    
    @Override
    public void render (Graphics2D pen)
    {
        if (this.isActive())
        {
            super.render(pen);
        }
    }


    public void update(long elapsedTime) {
        if (this.isActive()) {
//            if (moveBy.getX()!=0 || moveBy.getY()!=0){
//                System.out.println("move");
//                move(moveBy.getX(), moveBy.getY());
//            }
        	myCollisionStatus.setDefault();
            super.update(elapsedTime);
        }
    }
    
    public CollisionStatus getCollisionStatus(){
    	return myCollisionStatus;
    }
    
    public void setCollisionStatus(CollisionStatus c){
    	myCollisionStatus = c;
    }

}
