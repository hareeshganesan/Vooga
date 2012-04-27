package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import SpriteTree.GraphicsTest;

import npsprite.SpriteValues.DIR;

//The sprite for things that can overlap and own other sprites
//less specific than limbs, attach whatever you want to them
public class NodeSprite extends SpriteTemplate{

    protected String myName; //please make sure each name is distinct
    
    protected NodeSprite Parent;
    protected ArrayList<NodeSprite> children = new ArrayList<NodeSprite>();
 
    private Point2D moveBy;
    
    protected boolean flipped = false;

    protected double dx=0;
    protected double dy=0;

    protected double defaultTheta;
    protected double mutableTheta;
    protected BufferedImage myCurrImage;
    protected BufferedImage myOrigImage;
    protected HashMap<Integer, BufferedImage> myPreGenImgs =new HashMap<Integer, BufferedImage>();
    protected HashMap<Integer,BufferedImage> myFlippedImgs = new HashMap<Integer,BufferedImage>();

    protected GroupID currGroupID; //different from initial groupID if this is attached to something else
    
    public NodeSprite(BufferedImage image, GroupID g, double x, double y){
        super(image,g, x, y);
        moveBy = new Point2D.Double();

        currGroupID=myID;
        
    }
    
    public NodeSprite(NodeSprite parent, BufferedImage image,double dx, double dy, int baseTheta){
        super(image,parent.getGroupID(), parent.getX()+dx, parent.getY()+dy);
        setParent(parent);
        this.setDefaultSpeed(Parent.getSpeed());
        moveBy = new Point2D.Double();
        
        currGroupID=myID;
    
    }
    public void setPosition(int moveX, int moveY) {
        this.setX(this.getX() + moveX);
        this.setY(this.getY() + moveY);
    }

    public Point2D getCurrentLocation ()
    {
        return new Point2D.Double(getX() + moveBy.getX(), getY() +
                                                          moveBy.getY());
    }

    public void setNextLocationIncrement (Point2D nextLocation)
    {
        this.moveBy =
            new Point2D.Double(nextLocation.getX(), nextLocation.getY());
    }


    /*          */
    /* TREE AREA */
    /*          */

    protected void setParent (NodeSprite parent)
    {
        Parent = parent;
        if (Parent==null){
            currGroupID=myID;
        }
        this.setDefaultSpeed(Parent.getSpeed());
    }

    public NodeSprite getParent() {
        return Parent;
    }

    public void addChild (NodeSprite child)
    {
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

    public void removeChild (NodeSprite child)
    {
        if (children == null)
        {
            // do nothing
        }
        else if (!children.contains(child))
        {
            for (NodeSprite c : children)
            {
                c.removeChild(child);
            }
        }
        children.remove(child);
        child.setParent(null);
        currGroupID=this.getGroupID(); //resets id

    }

    public ArrayList<NodeSprite> getChildren(){
        return this.children;
    }
    

    public String getName(){
        return this.myName;
    }

    public void flip(boolean flipped) {
        this.flipped=flipped;
        for (NodeSprite child:children){
            child.flip(flipped);
        }
    }

    public Integer roundTheta(double theta) {
        Integer n = 0;
        n = (int) Math.round(theta);
        return n;
    }

    public void draw(double x, double y, double theta) {
        this.setX(x);
        this.setY(y);
        Integer roundedTheta = roundTheta(theta);

        if (this.flipped == true) {
            if (this.Parent != null) {
                // coordinate flipped sprite images
                double dxFromCenter = (this.Parent.getX() + this.Parent
                        .getWidth() / 2) - x;
                if (dxFromCenter > 0) {
                    this.setX((this.Parent.getX() + this.Parent.getWidth() / 2)
                            + dxFromCenter);
                }
                if (dxFromCenter < 0) {

                    this.setX((this.Parent.getX() + this.Parent.getWidth() / 2)
                            - dxFromCenter);
                }
            }

            if (myFlippedImgs.containsKey(roundedTheta)) {
                this.setImage(myFlippedImgs.get(roundedTheta));
            } else {
                this.myCurrImage = GraphicsTest.rotate(this.myOrigImage, theta);
                this.myCurrImage = GraphicsTest.horizFlip(this.myCurrImage);
                this.setImage(this.myCurrImage);
                myFlippedImgs.put(roundTheta(theta), this.myCurrImage);
            }
        } else {

            if (myPreGenImgs.containsKey(roundedTheta)) {
                this.setImage(myPreGenImgs.get(roundedTheta));
            } else {
                this.myCurrImage = GraphicsTest.rotate(this.myOrigImage, theta);
                this.setImage(this.myCurrImage);
                myPreGenImgs.put(roundTheta(theta), this.myCurrImage);
            }
        }
    }

    public void render(Graphics2D pen,double baseX, double baseY, int baseTheta){
        super.render(pen);
        
        double dx =Math.cos(Math.toRadians(baseTheta)) * this.dx - Math.sin(Math.toRadians(baseTheta)) * this.dy;
        double dy =Math.sin(Math.toRadians(baseTheta)) * this.dx + Math.cos(Math.toRadians(baseTheta)) * this.dy;

        draw((baseX + dx), (baseY + dy), this.mutableTheta + baseTheta);
        
        for(NodeSprite limb: this.children){
            limb.render(pen, (baseX + dx), (baseY + dy), (int) (this.mutableTheta
                + baseTheta));
        }
    }

}
