package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import npsprite.SpriteValues.DIR;
import npsprite.SpriteValues.STATUS;
import npsprite.properties.DirectionProperty;
import npsprite.properties.HealthProperty;
import npsprite.properties.StatusProperty;

import sprite.HealthDisplay;

//THIS IS A POINTER TO THE TOP OF THE TREE THAT REPRESENTS A PLAYER - has no width/height
//it comes with health, direction, and status properties predefined - no need to add
//limbs come with damage - see limbsprite
//TODO: subclass of spritetemplate?
public class FighterBody extends SpriteTemplate{

    private String myName;
    private HealthProperty myHealth; //for ease in access
    private DirectionProperty myDirection;
    private StatusProperty myStatus; //for stuff like jumping, blocking
    
    private HealthDisplay myDisplay;
    LimbSprite root; //root must be a limb
    
    private Graphics2D myPen;

    private HashMap<String, NodeSprite> myMap;

    public FighterBody(LimbSprite root, String name, HealthDisplay display) {
        super(root.getGroupID());
        this.root = root;
        root.setFighter(this);
        myName = name;
        myDisplay = display;
        
        myHealth=new HealthProperty(100);
        myDirection=new DirectionProperty();
        myStatus=new StatusProperty(SpriteValues.STATUS.NORM);
        
        myStatus.setStatusValue(STATUS.BLOCK, 0.1);
        
        super.addProperty(HealthProperty.NAME,myHealth);
        super.addProperty(DirectionProperty.NAME,myDirection);
        super.addProperty(StatusProperty.NAME,myStatus);
        
        myDisplay.setStat(myName, (int) getHealth());

        myMap = new HashMap<String, NodeSprite>();
        createMap(this.root);
    }
    //TODO: link up with the horizontal flipping in limb sprites
    public void setInitDirection(DIR dir){
        myDirection.setDirection(dir);
    }
    public void setInitStatus(STATUS s){
        myStatus.setStatus(s);
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

    public void move(Graphics2D pen, double moveX, double moveY) {
        if (root != null) {
//            System.out.println(moveX+" "+moveY);
            root.render(pen, root.getX() + moveX, root.getY() + moveY, 0);
        }
        
        if (moveX < 0) {
            myDirection.setDirection(SpriteValues.DIR.LEFT);
        } else if (moveY > 0) {
            myDirection.setDirection(SpriteValues.DIR.RIGHT);
        }
    }
    
    public void setHealth(double h){
        myHealth.setMaxHealth(h);
    }
    public double getHealth() {
        return myHealth.getValue();
    }
    public void setStatusValue(SpriteValues.STATUS s, double v){
        myStatus.setStatusValue(s,v);
    }
    public double getHealthMultiplier(){
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

    public void setRoot(LimbSprite root){
        this.root=root;
        root.setFighter(this);
    }
    public LimbSprite getRoot() {
        return root;
    }
    public Collection<NodeSprite> getBodyParts(){
        return Collections.unmodifiableCollection(myMap.values());
    }

    /* For sprite tree */
    public void add(NodeSprite child) {
        root.addChild(child);
    }
    
    public void removeChild(NodeSprite child) {
        root.removeChild(child);
    }
    
    /* called by limb sprites to update the map */
    public void childAdded(NodeSprite child) {
        myMap.put(child.getName(), child);
    }
    /* called by limb sprites to update the map */
    public void childRemoved(NodeSprite child) {
        myMap.remove(child.getName());
    }

    @Override
    public void render(Graphics2D pen) {
        if(root.isActive()){
            
        myPen=pen;
        root.render(pen, root.getX(), root.getY(), 0);
        myDisplay.render(pen);
        }
    }


    public void update (long elapsedTime)
    {
        root.update(elapsedTime);
        if (getHealth() <= 0) {
            root.setActive(false); // dead, have game check for this for end of
                                   // level
        }

        myDisplay.update(elapsedTime, (int) getHealth());
        if (moveBy.getX()!=0 || moveBy.getY()!=0){
            move(myPen, moveBy.getX(), moveBy.getY());
        }
        moveBy.setLocation(0, 0); // moveBy only work for one time then set to zero
        
        super.update(elapsedTime);
    }


}
