package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import sprite.SpriteValues;

//The sprite for things that can overlap and own other sprites
public class NodeSprite extends SpriteTemplate{

    //TODO: do i really want all these variables? maybe further abstraction with specific body parts?
    double DEFAULT_DAMAGE = 0;
    private double myDamage = 0;
    
    protected NodeSprite Parent;
    protected ArrayList<NodeSprite> children = new ArrayList<NodeSprite>();
    
    // to be implemented
    double damageMultiplier=1; // if this node is arm, damageMultiplier less than
                             // if node were torso
    double damageDealt = -5;

    private int myDirection; //this may be needed later on for more refined collisionEvents
    private Point2D moveBy;

    protected GroupID currGroupID;
    
    //constructor for parent
    public NodeSprite(BufferedImage image, GroupID g, double x, double y){
        super(image,g, x, y);
        myDamage=DEFAULT_DAMAGE;
        moveBy = new Point2D.Double();
    }
    
    //WHAT IS BASE THETA?
    public NodeSprite(NodeSprite parent, BufferedImage image,double dx, double dy, int baseTheta){
        super(image,parent.getGroupID(), parent.getX()+dx, parent.getY()+dy);
        this.Parent = parent;
        myDamage=DEFAULT_DAMAGE;
        moveBy = new Point2D.Double();
    
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
            currGroupID=myID;
        }
    }

    public void addChild(NodeSprite child) {
        child.setParent(this);
        child.changeGroupID(this.getGroupID());
        children.add(child);
        // System.out.println(children.size());
    }

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

    public ArrayList<NodeSprite> getChildren(){
        return this.children;
    }
}
