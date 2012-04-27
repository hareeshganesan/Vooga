package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import SpriteTree.GraphicsTest;

import npsprite.properties.DamageProperty;
import npsprite.properties.PropertyObject;

//The sprite for things that can overlap and own other sprites
//less specific than limbs, attach whatever you want to them
public class NodeSprite extends SpriteTemplate {

    protected String myName; // please make sure each name is distinct

    protected NodeSprite Parent;
    protected ArrayList<NodeSprite> children = new ArrayList<NodeSprite>();

    private Point2D moveBy;

    public boolean isLimb = false;

    protected boolean flipped = false;

    protected double dx = 0;
    protected double dy = 0;

    protected FighterBody myPointer;

    double damageMultiplier = 1;

    protected double defaultTheta;
    protected double mutableTheta;
    protected BufferedImage myCurrImage;
    protected BufferedImage myOrigImage;
    protected HashMap<Integer, BufferedImage> myPreGenImgs = new HashMap<Integer, BufferedImage>();
    protected HashMap<Integer, BufferedImage> myFlippedImgs = new HashMap<Integer, BufferedImage>();

    protected GroupID currGroupID; // different from initial groupID if this is
                                   // attached to something else

    /**
     * constructor for torso/base nodes
     */
    public NodeSprite(String n, BufferedImage image, GroupID g, double x,
            double y, double damage) {
        super(image, g, x, y);
        this.myOrigImage = image;
        myName = n;
        moveBy = new Point2D.Double();
        this.addProperty(DamageProperty.NAME, new DamageProperty(damage
                * damageMultiplier));
        currGroupID = myID;

    }

    /**
     * constructor for other things
     */
    public NodeSprite(String n, NodeSprite parent, BufferedImage image,
            double dx, double dy, double damage, int baseTheta) {
        super(image, parent.getGroupID(), parent.getX() + dx, parent.getY()
                + dy);

        this.myOrigImage = image;
        this.mutableTheta = baseTheta;
        this.defaultTheta = baseTheta;
        this.dx = dx;
        this.dy = dy;
        setParent(parent);
        myName = n;
        this.setDefaultSpeed(Parent.getSpeed());
        moveBy = new Point2D.Double();

        currGroupID = myID;
        this.addProperty(DamageProperty.NAME, new DamageProperty(damage
                * damageMultiplier));
    }

    public void setFighter(FighterBody fighterBody) {
        myPointer = fighterBody;
        myID = fighterBody.getGroupID();
    }

    public void setPosition(int moveX, int moveY) {
        this.setX(this.getX() + moveX);
        this.setY(this.getY() + moveY);
    }

    public Point2D getCurrentLocation() {
        return new Point2D.Double(this.getX() + moveBy.getX(), this.getY()
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
        if (Parent == null) {
            currGroupID = myID;
        }
        this.setDefaultSpeed(Parent.getSpeed());
    }

    public NodeSprite getParent() {
        return Parent;
    }

    public void addChild(NodeSprite child) {
        child.setParent(this);
        child.changeGroupID(this.getGroupID());
        children.add(child);
        child.setFighter(myPointer);
        myPointer.childAdded(child);
    }

    public void changeGroupID(GroupID g) {
        currGroupID = g;
        // TODO figure out how this is going to be communicated to the physics
        // engine?
    }

    public GroupID getGroupID() {
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
        child.setParent(null);
        currGroupID = this.getGroupID(); // resets id

    }

    public boolean hasProperty(String name) {
        if (myPointer.hasProperty(name)) {
            return true;
        }
        return super.hasProperty(name);
    }

    public PropertyObject getProperty(String name) {
        PropertyObject o = myPointer.getProperty(name);
        if (o == null) {
            return super.getProperty(name);
        }
        return o;
    }

    public void setMass(double mass) {
        myPointer.setMass(mass);
    }

    public double getMass() {
        return myPointer.getMass();
    }

    public void rotate(int dTheta) {
        this.mutableTheta += dTheta;
    }

    public double getTheta() {
        return this.mutableTheta;
    }

    public double getDefaultTheta() {
        return this.defaultTheta;
    }

    public void setTheta(double expTheta) {
        this.mutableTheta = expTheta;
    }

    public FighterBody getMyPointer() {
        return myPointer;
    }

    public ArrayList<NodeSprite> getChildren() {
        return this.children;
    }

    public String getName() {
        return this.myName;
    }

    public void flip(boolean flipped) {
        this.flipped = flipped;
        for (NodeSprite child : children) {
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

    public void render(Graphics2D pen, double baseX, double baseY, int baseTheta) {
        super.render(pen);

        double dx = Math.cos(Math.toRadians(baseTheta)) * this.dx
                - Math.sin(Math.toRadians(baseTheta)) * this.dy;
        double dy = Math.sin(Math.toRadians(baseTheta)) * this.dx
                + Math.cos(Math.toRadians(baseTheta)) * this.dy;

        if (this.flipped) {
            dx = -dx;
        }
        draw((baseX + dx), (baseY + dy), this.mutableTheta + baseTheta);

        for (NodeSprite limb : this.children) {
            limb.render(pen, (baseX + dx), (baseY + dy),
                    (int) (this.mutableTheta + baseTheta));
        }
    }

    public void setActive(boolean flag) {
        super.setActive(flag);
        for (NodeSprite n : children) {
            n.setActive(flag);
        }
    }

}
