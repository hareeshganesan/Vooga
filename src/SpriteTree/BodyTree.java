package SpriteTree;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class BodyTree {
	LimbNode root = null;
	
	public void add(LimbNode limb){
		if(root == null){
			root = limb;
		}
		else{
			root.addChild(limb);
		}
	}
	
	
	public void render(Graphics2D pen){
		renderHelper(pen,root);
	}
	
	public void renderHelper(Graphics2D pen, LimbNode currNode){
		if(currNode.getChildren().size() ==0){
			currNode.render(pen);
		}
		else{
			currNode.render(pen);
		for(LimbNode ln: currNode.getChildren()){
			renderHelper(pen, ln);
		}	
		}
	}
	
	
	
	public void update(long elapsedTime){
	
	
	}


	public void move(int moveX, int moveY, LimbNode currNode) {
		if(currNode.getChildren().size() ==0){
			currNode.setPosition(moveX, moveY);
		}
		else{
			currNode.setPosition(moveX, moveY);
			for(LimbNode ln: currNode.getChildren()){
				move(moveX,moveY,ln);
			}	
		}
	}

}
