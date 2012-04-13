package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import npsprite.SpriteID.GroupID;
import sprite.SpriteValues;
import SpriteTree.GraphicsTest;

//The sprite for things that can overlap and own other sprites - the tree for animation
public class NodeSprite extends SpriteTemplate implements Attachable,Health,Damage {

    private BufferedImage myCurrImage;
    private BufferedImage myOrigImage;

    private int allAngles = 0;
    private int currAngle = 0;
    
    private double dx;
    private double dy;
    private int theta;
    
    // defaults
    private int MIN_HEALTH = 1;
    private int MAX_HEALTH = 50;
    private double myHealth;

    //TODO: do i really want all these variables? maybe further abstraction with specific body parts?
    double DEFAULT_DAMAGE = 0;
    private double myDamage = 0;
    
    private NodeSprite Parent;
    private ArrayList<NodeSprite> children = new ArrayList<NodeSprite>();
    
    // to be implemented
    double damageMultiplier=1; // if this node is arm, damageMultiplier less than
                             // if node were torso
    double damageDealt = -5;

    private int myDirection; //this may be needed later on for more refined collisionEvents
    private Point2D moveBy;
    
    private GroupID currGroupID;
    
    //constructor for parent
    public NodeSprite(BufferedImage image, GroupID g, double x, double y){
        super(image,g, x, y);
        this.myOrigImage = image;
        myHealth=MAX_HEALTH;
        myDamage=DEFAULT_DAMAGE;
    }
    
    //WHAT IS BASE THETA?
    public NodeSprite(NodeSprite parent, BufferedImage image,double dx, double dy, int baseTheta){
        super(image,parent.getGroupID(), parent.getX()+dx, parent.getY()+dy);
        this.myOrigImage= image;
        this.Parent = parent;
        this.theta = baseTheta;
        this.dx = dx;
        this.dy = dy;
        moveBy = new Point2D.Double();
        myHealth=MAX_HEALTH;
        myDamage=DEFAULT_DAMAGE;
    
    }
    @Override
    protected void createSpriteID(GroupID g) {
        myID=new SpriteID(g, health, damages, false, attaches);
    }

    public void setPosition(int moveX, int moveY) {
        this.setX(this.getX() + moveX);
        this.setY(this.getY() + moveY);
        if (moveX < 0) {
            setDirection(SpriteValues.LEFT);
        } else if (moveY > 0) {
            setDirection(SpriteValues.RIGHT);
        }
    }

    public int getDirection() {
        return myDirection;
    }

    private void setDirection(int dir) {
        myDirection = dir;
    }

    public void addHealth(double h) {
        if (Parent != null) {
            Parent.addHealth(h);
        } else {
            myHealth += h;
            wrapHealth();
        }
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

    public void setDamage(double d) {
        myDamage = d;
    }

    public double getDamage() {
        return myDamage;
    }

    // TODO: implement
    // for the connections with physics engine
    public Point2D getCurrentLocation() {
        return new Point2D.Double(getX() + moveBy.getX(), getY()
                + moveBy.getY());
    }

    public void setNextLocationIncrement(Point2D nextLocation) {
        this.moveBy = new Point2D.Double(nextLocation.getX(),
                nextLocation.getY());
    }

    /*          */
    /* TREE AREA */
    /*          */

    protected void setParent(NodeSprite parent) {
        Parent = parent;
        if (Parent==null){
            currGroupID=myID.getGroupID(); //SHOULD RESET TO OLD ID
        }
    }

    public void addChild(NodeSprite child) {
        child.setParent(this);
        child.changeGroupID(this.getGroupID());
        children.add(child);
        // System.out.println(children.size());
    }

    @Override
    public void changeGroupID(GroupID g) {
        currGroupID=g;
        //figure out how this is going to be communicated to the physics engine
    }
    public GroupID getGroupID(){
        return currGroupID;
    }

    public void removeChild(NodeSprite child) {
        if (children == null) {
            // do nothing
        } else if (!children.contains(child)) {
            for (NodeSprite c : children) {
                c.removeChild(child);
            }
        }
        children.remove(child);
        child.setParent(null); // WHAT WILL THE CHILD DO WITH THEIR ID?
                               // PRESUMABLY THEY CAN NOW COLLIDE WITH THIS
                               // FORMER PARENT...

    }
    
    public void rotate(int dTheta)
    {   
        this.theta += dTheta;
    }

    public void draw(double x, double y, int theta){
        this.setX(x);
        this.setY(y);
        this.myCurrImage =GraphicsTest.rotate(this.myOrigImage,theta);
        this.setImage(this.myCurrImage);
    }

    public void render(Graphics2D pen,double baseX, double baseY, int baseTheta){
        
        
        super.render(pen);
        
        double dx =Math.cos(Math.toRadians(baseTheta)) * this.dx - Math.sin(Math.toRadians(baseTheta)) * this.dy;
        double dy =Math.sin(Math.toRadians(baseTheta)) * this.dx + Math.cos(Math.toRadians(baseTheta)) * this.dy;

        
        draw((baseX + dx), (baseY + dy),this.theta+baseTheta);
        
        
        for(NodeSprite limb: this.children){
            limb.render(pen, (baseX + dx), (baseY + dy), baseTheta);
        }
        

    }
    
    public void update(long elapsedTime){
        if (myHealth <= 0) {
            this.setActive(false);
        }
        super.update(elapsedTime);
    }
}
