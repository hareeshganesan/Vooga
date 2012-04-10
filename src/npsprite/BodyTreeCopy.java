package npsprite;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import sprite.HealthDisplay;

//TODO: RENAME
//TODO: FIGURE OUT WHERE THE HEALTH IS STORED - IN THIS? HOW? THE SUM OF ALL PARTS?
//THIS IS A POINTER TO THE TOP OF THE TREE THAT REPRESENTS A PLAYER
public class BodyTreeCopy{

    private String myName;
    private int myHealth; //TODO: HOW TO CALCULATE HEALTH?
    private Point2D moveBy;

    private HealthDisplay myDisplay;
    LimbNodeCopy root = null;

    public BodyTreeCopy(String name,HealthDisplay display){
        myName=name;
        myDisplay=display;
        myDisplay.setStat(myName, myHealth);
        moveBy = new Point2D.Double();
    }
    
    public void render(Graphics2D pen) {
        renderHelper(pen, root);
    }

    public void renderHelper(Graphics2D pen, LimbNodeCopy currNode) {
        if (currNode.getChildren().size() == 0) {
            currNode.render(pen);
        } else {
            currNode.render(pen);
            for (LimbNodeCopy ln : currNode.getChildren()) {
                renderHelper(pen, ln);
            }
        }
    }

    public void update(long elapsedTime) {
        // TODO
        myDisplay.update(elapsedTime, myHealth);
        if (myHealth==0){
            root.setActive(false); //dead
        }
    }

    public void move(int moveX, int moveY, LimbNodeCopy currNode) {
        if (currNode.getChildren().size() == 0) {
            currNode.setPosition(moveX, moveY);
        } else {
            currNode.setPosition(moveX, moveY);
            for (LimbNodeCopy ln : currNode.getChildren()) {
                move(moveX, moveY, ln);
            }
        }
    }

    public void addChild(LimbNodeCopy child) {
        //apparently only the first body part is root of the tree
        if (root == null) {
            root = child;
        } else {
            root.addChild(child); 
        }
    }

    public void removeChild(LimbNodeCopy child) {
        if (child==root){
            root.setParent(null);
            root=null;
        }
        else {
            root.removeChild(child);
        }

    }

    protected Point2D confineBounds(double dx, double dy) {
        // TODO Auto-generated method stub
        return null;
    }

}
