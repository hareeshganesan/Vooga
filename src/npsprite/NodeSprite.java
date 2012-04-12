package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import sprite.SpriteValues;

import SpriteTree.GraphicsTest;

import action.Action;

import com.golden.gamedev.util.ImageUtil;

import com.golden.gamedev.object.Sprite;

//The sprite for things that can overlap and own other sprites - the tree for animation
public class NodeSprite extends SpriteTemplate implements Attachable {

    private BufferedImage myImage;
    private double parentDx;
    private double parentDy;
    private NodeSprite Parent;
    private ArrayList<NodeSprite> children = new ArrayList<NodeSprite>();
    private ArrayList<NodeSprite> childrenCopy = new ArrayList<NodeSprite>();// for
                                                                             // getter

    // to be implemented
    double damageMultiplier; // if this node is arm, damageMultiplier less than
                             // if node were torso
    double damageDealt = -5;

    private int myDirection;
    private Point2D moveBy;

    public NodeSprite(NodeSprite parent, BufferedImage image, double parentX,
            double parentY, double dx, double dy) {
        super(image, parentX + dx, parentY + dy);
        this.myImage = image;
        this.Parent = parent;
        this.parentDx = dx;
        this.parentDy = dy;

        super.setDamage(damageDealt);
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

    public void addHealth(int h) {
        if (Parent != null) {
            Parent.addHealth(h);
        } else {
            super.addHealth(h);
        }
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
    }

    public void addChild(NodeSprite child) {
        child.setParent(this);
        child.setID(this.getID());
        children.add(child);
        // System.out.println(children.size());
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

    public ArrayList<NodeSprite> getChildren() {
        childrenCopy.clear();
        childrenCopy.addAll(children);
        // Collections.copy(childrenCopy, children);
        return childrenCopy;
    }

    // TODO: ABSTRACT THIS ROTATION INTO MORE THINGS?
    public void rotateLimb(int angle) {
        // x and y and the coordinates of hinge
        if (this.Parent == null) {
            this.myImage = GraphicsTest.rotate(this.myImage, angle,
                    this.getX(), this.getY());
            this.setImage(this.myImage);
        } else {
            this.myImage = ImageUtil.rotate(this.myImage, angle);
            this.setImage(this.myImage);
        }
    }

    public void render(Graphics2D pen) {
        super.render(pen);
    }

    public void update(long elapsedTime) {
        super.update(elapsedTime);
    }

    /*           */
    /* Collisions */
    /*           */

    // TODO: THIS MAY CHANGE WITH CHANGING COLLISIONCHECKER
    protected Point2D confineBounds(double dx, double dy) {
        if (!this.isOnScreen()) {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY());
        }
        if ((getX() - dx) < 0)
            dx = -getX();
        if ((getX() + getWidth() + dx) > getBackground().getWidth())
            dx -= getY() + getHeight();
        if ((getY() + getHeight() + dy) > getBackground().getHeight())
            dy -= getY() + getHeight();
        if (getY() + dy < 0)
            dy = 0;
        return new Point2D.Double(dx, dy);
    }

}
