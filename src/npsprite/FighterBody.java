package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import events.CollisionEvent;
import action.Action;
import action.ActionTimer;

import SpriteTree.Animation;
import SpriteTree.LimbNode;

import npsprite.SpriteValues.DIR;
import npsprite.SpriteValues.STATUS;
import npsprite.properties.DirectionProperty;
import npsprite.properties.HealthProperty;
import npsprite.properties.SpawnsProperty;
import npsprite.properties.StatusProperty;

import sprite.HealthDisplay;

//THIS IS A POINTER TO THE TOP OF THE TREE THAT REPRESENTS A PLAYER - has no width/height
//it comes with health, direction, and status properties predefined - no need to add
//limbs come with damage - see limbsprite
//TODO: subclass of spritetemplate?
public class FighterBody extends SpriteTemplate {
    private String myName;
    private HealthProperty myHealth; // for ease in access
    private DirectionProperty myDirection;
    private StatusProperty myStatus; // for stuff like jumping, blocking

    private ArrayList<ActionTimer> myTimers;
    private HealthDisplay myDisplay;
    NodeSprite root; // root must be a limb
    
    private HashMap<String, NodeSprite> myMap;
    private HashMap<String,Animation> myMovements;
    
    public FighterBody(NodeSprite root, String name, HealthDisplay display) {
        super(root.getGroupID());
        this.root = root;
        root.setFighter(this);
        this.setDefaultSpeed(root.getSpeed());
        myName = name;
        myDisplay = display;

        myHealth = new HealthProperty(100);
        myDirection = new DirectionProperty(DIR.RIGHT);
        myStatus = new StatusProperty(SpriteValues.STATUS.NORM);

        myTimers = new ArrayList<ActionTimer>();
        myTimers.add(new ActionTimer(500));

        this.addProperty(HealthProperty.NAME, myHealth);
        this.addProperty(DirectionProperty.NAME, myDirection);
        this.addProperty(StatusProperty.NAME, myStatus);

        myDisplay.setStat(myName, (int) getHealth());

        myMap = new HashMap<String, NodeSprite>();
        createMap(this.root);
    }

    public void setAnimations(HashMap<String,Animation>moves){
        myMovements=moves;
    }
    public Animation getAnimation(String name){
        return myMovements.get(name);
    }
    public void setInitDirection(DIR dir) {
        myDirection.setDirection(dir);
    }

    public void setInitStatus(STATUS s) {
        myStatus.setStatus(s);
    }
    public DIR getDirection(){
        return myDirection.getDirection();
    }
    public STATUS getStatus(){
        return myStatus.getStatus();
    }
    
    public void createMap(NodeSprite currNode) {
        if (!myMap.containsKey(currNode.getName())) {
            myMap.put(currNode.getName(), currNode);
        }
        for (NodeSprite limb : currNode.getChildren()) {
            createMap(limb);
        }
    }

    public NodeSprite getNode(String name) {
        return myMap.get(name);
    }

    public void move(double moveX, double moveY) {
        if (root != null) {
            root.setX(root.getX() + moveX);     
            root.setY(root.getY() + moveY);
        }

        if (moveX < 0) {
            myDirection.setDirection(SpriteValues.DIR.LEFT);
        } else if (moveY > 0) {
            myDirection.setDirection(SpriteValues.DIR.RIGHT);
        }
    }

    public void setHealth(double h) {
        myHealth.setMaxHealth(h);
    }

    public double getHealth() {
        return myHealth.getValue();
    }

    public double getHealthMultiplier() {
        return myStatus.getValue();
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

    public void setRoot(NodeSprite root) {
        this.root = root;
        root.setFighter(this);
    }

    public NodeSprite getRoot() {
        return root;
    }

    public NodeSprite getLimb(String name) {
        return myMap.get(name);
    }

    public Collection<NodeSprite> getBodyParts() {
        return Collections.unmodifiableCollection(myMap.values());
    }

    /* For sprite tree */
    public void add(String parentName, NodeSprite child) {
        NodeSprite parent = myMap.get(parentName);
        parent.addChild(child);
        
    }

    public void removeChild(NodeSprite child) {
        NodeSprite node = myMap.get(child);
        if (node == null) {
            return; // break gracefully
        }
        if (node.getChildren().size() != 0) {
            for (NodeSprite cchild : node.getChildren()) {
                myMap.remove(cchild.getName());
            }
        }
        NodeSprite parent = child.getParent();
        parent.removeChild(child);
        myMap.remove(child.getName());
    }

    /* called by limb sprites to update the map */
    public void childAdded(NodeSprite child) {
        myMap.put(child.getName(), child);
    }

    public void flip(SpriteValues.DIR direction) {
        if (direction!=myDirection.getDirection()){
        root.flip(true);
        }
        myDirection.setDirection(direction);
    }
    
    public void collisionAction(SpriteTemplate otherSprite) {
        super.collisionAction(otherSprite);
    }

    @Override
    public void render(Graphics2D pen) {
        if (root.isActive()) {
            root.render(pen, root.getX(), root.getY(), 0);
            myDisplay.render(pen);
        }
    }

    public void update(long elapsedTime) {
        if(this.getCollisionStatus().getStandOnSth()){
            myTimers.get(0).makeAvailable();
        }
        root.update(elapsedTime);
        if (getHealth() <= 0) {
            root.setActive(false); // dead, have game check for this for end of
                                   // level
            this.setActive(false);
        }
        myDisplay.update(elapsedTime, (int) getHealth());
        super.update(elapsedTime);
    }
    public String print(LimbNode currentNode){
        String tree = currentNode.getName();
        if(currentNode.getChildren().size() == 0){
            return currentNode.getName();
        }
        for(LimbNode child: currentNode.getChildren()){
            tree +=print(child);
            tree += "--";
        }
        return tree;
        
    }


    public int getHeight() {
        double height = 0;
        for (NodeSprite n : getBodyParts()) {
            height = Math.max(height, n.getY() + n.getHeight() - getY());
        }
        return (int) height;
    }

    public int getWidth() {
        double width = 0;
        for (NodeSprite n : getBodyParts()) {
            width = Math.max(width, n.getX() + n.getWidth() - getX());
        }
        return (int) width;
    }
    public ActionTimer getMyTimer (int index)
    {
        return myTimers.get(index);
    }
    
}
