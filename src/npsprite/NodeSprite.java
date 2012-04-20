package npsprite;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//The sprite for things that can overlap and own other sprites
//less specific than limbs, attach whatever you want to them
public class NodeSprite extends SpriteTemplate{

    protected String myName; //please make sure each name is distinct
    
    protected NodeSprite Parent;
    protected ArrayList<NodeSprite> children = new ArrayList<NodeSprite>();
 
    private Point2D moveBy;

    protected GroupID currGroupID; //different from initial groupID if this is attached to something else
    
    public NodeSprite(BufferedImage image, GroupID g, double x, double y){
        super(image,g, x, y);
        moveBy = new Point2D.Double();

        currGroupID=myID;
        
    }
    
    public NodeSprite(NodeSprite parent, BufferedImage image,double dx, double dy, int baseTheta){
        super(image,parent.getGroupID(), parent.getX()+dx, parent.getY()+dy);
        setParent(parent);
        moveBy = new Point2D.Double();
    
    }
    public void setPosition(int moveX, int moveY) {
        this.setX(this.getX() + moveX);
        this.setY(this.getY() + moveY);
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
        //TODO figure out how this is going to be communicated to the physics engine?
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
    

    public String getName(){
        return this.myName;
    }
}
