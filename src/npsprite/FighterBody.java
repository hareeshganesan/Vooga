package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import events.CollisionEvent;


import sprite.HealthDisplay;

//THIS IS A POINTER TO THE TOP OF THE TREE THAT REPRESENTS A PLAYER
public class FighterBody {

    private String myName;
    private double myHealth = 50; // default placeholder

    private HealthDisplay myDisplay;
    NodeSprite root = null;

    private static ArrayList<CollisionEvent> myCollisions = new ArrayList<CollisionEvent>();

    public FighterBody(String name, HealthDisplay display) {
        myName = name;
        myDisplay = display;
        myDisplay.setStat(myName, (int) myHealth);
    }

    // TODO currently used for testing, will need to implement with Point2D
    // stuff later

    public void move(Graphics2D pen, double moveX, double moveY) {
        if (root != null) {
            root.render(pen, root.getX() + moveX, root.getY() + moveY, 0);
        }
    }

    private void getHealth() {
        if (root == null) {
            myHealth = 0;
        } else {
            myHealth = root.getHealth();
        }
    }

    public void add(NodeSprite child) {
        // apparently only the first body part is root of the tree
        if (root == null) {
            root = child;
            // System.out.println("root added!");
        } else {
            root.addChild(child);
        }
    }

    public void addCollisionEvent(CollisionEvent c) {
        myCollisions.add(c);
    }

    public void removeChild(NodeSprite child) {
        if (child == root) {
            root.setParent(null);
            root = null;
        } else {
            root.removeChild(child);
        }
    }

    public void render(Graphics2D pen) {
        root.render(pen, root.getX(), root.getY(), 0);
    }

    public void update(long elapsedTime) {
        root.update(elapsedTime);
        getHealth();
        if (myHealth <= 0) {
            root.setActive(false); // dead, have game check for this for end of
                                   // level
        }
        myDisplay.update(elapsedTime, (int) myHealth);
    }

    
    /* Wrapped for input handler */
    public Point2D getCurrentLocation() {
        return root.getCurrentLocation();
    }
    public double getX() {
        return root.getX();
    }
    public double getY() {
        return root.getY();
    }

}
