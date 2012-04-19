package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.HashMap;

import sprite.HealthDisplay;
import events.CollisionEvent;

//THIS IS A POINTER TO THE TOP OF THE TREE THAT REPRESENTS A PLAYER
//TODO: wait for helena to fix the mapping
public class FighterBody {

    private String myName;
    private double myHealth = 50; // default placeholder

    private HealthDisplay myDisplay;
    NodeSprite root = null;

    private HashMap<String,NodeSprite> map ;
    
    public FighterBody(String name, HealthDisplay display) {
        myName = name;
        myDisplay = display;
        myDisplay.setStat(myName, (int) myHealth);

        map= new HashMap<String,NodeSprite>();
    }
    public FighterBody(LimbSprite root,String name, HealthDisplay display) {
        this.root=root;
        myName = name;
        myDisplay = display;
        myDisplay.setStat(myName, (int) myHealth);

        map= new HashMap<String,NodeSprite>();
        createMap(this.root);
    }

    public void createMap(NodeSprite currNode){
        if(!map.containsKey(currNode.getName())){
            map.put(currNode.getName(), currNode);
        }
        for(LimbSprite limb:currNode.getChildren()){
            createMap(limb); //TODO: SHOULD THEY HAVE NAMES? OR GO OFF OF IDS?
        }
    }

    
    public LimbSprite getNode(String name){
        return map.get(name);
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
            root.setParent(this);
            root = child;
            createMap(this.root);
        } else {
            root.addChild(child);
        }
    }

    public void addCollisionEvent(CollisionEvent c) {
        //this should not happen
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
