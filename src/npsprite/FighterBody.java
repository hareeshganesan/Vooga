package npsprite;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import sprite.HealthDisplay;
import sprite.SpriteValues;

//THIS IS A POINTER TO THE TOP OF THE TREE THAT REPRESENTS A PLAYER
public class FighterBody{

    private String myName;
    private double myHealth=50;
    private Point2D moveBy;

    private HealthDisplay myDisplay;
    NodeSprite root = null;

    public FighterBody(String name,HealthDisplay display){
        myName=name;
        myDisplay=display;
        myDisplay.setStat(myName, (int) myHealth);
        moveBy = new Point2D.Double();
    }

    //TODO currently used for testing, will need to implement with Point2D stuff later
    public void move(int moveX, int moveY, NodeSprite currNode) {
        if (currNode.getChildren().size() == 0) {
            currNode.setPosition(moveX, moveY);
        } else {
            currNode.setPosition(moveX, moveY);
            for (NodeSprite ln : currNode.getChildren()) { //recursion
                move(moveX, moveY, ln);
            }
        }
    }

    private void getHealth(){
        if (root==null){
            myHealth=0;
        }
        else {
            myHealth=root.getHealth();
        }
    }
    
    public void add(NodeSprite child) {
        //apparently only the first body part is root of the tree
        if (root == null) {
            root = child;
//            System.out.println("root added!");
        } else {
            root.addChild(child); 
        }
    }

    public void removeChild(NodeSprite child) {
        if (child==root){
            root.setParent(null);
            root=null;
        }
        else {
            root.removeChild(child);
        }
    }

    public void render(Graphics2D pen) {
        renderHelper(pen, root);
        myDisplay.render(pen);
    }

    public void renderHelper(Graphics2D pen, NodeSprite currNode) {
        if (currNode.getChildren()==null) {
            currNode.render(pen);
        } else {
            currNode.render(pen);
            for (NodeSprite ln : currNode.getChildren()) {
                renderHelper(pen, ln);
            }
        }
    }
    public void update(long elapsedTime) {
        getHealth();
        if (myHealth<=0){
            root.setActive(false); //dead, have game check for this for end of level
        }
        myDisplay.update(elapsedTime, (int) myHealth);
    }

}
