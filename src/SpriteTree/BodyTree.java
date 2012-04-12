package SpriteTree;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;

public class BodyTree {
	private LimbNode root;
	private HashMap<String,LimbNode> map;
	
	
	public BodyTree (LimbNode root){
		this.root=root;
	}
	
	public void render(Graphics2D pen){
		root.render(pen, root.getX(),root.getY(),0);
	}
	
	public void move(Graphics2D pen, double moveX, double moveY){
		root.render(pen, root.getX()+moveX,root.getY()+moveY,0);
	}
}