package SpriteTree;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public class LimbNode extends Sprite{
	
	private BufferedImage myImage;
	private double parentDx;
	private double parentDy;
	private LimbNode Parent;
	private ArrayList<LimbNode> children = new ArrayList<LimbNode>();
	private ArrayList<LimbNode> childrenCopy;//for getter
	
	//to be implemented
	double damageMultiplier;
	double damageDealt;
	
	
	public LimbNode(LimbNode parent, BufferedImage image,double parentX, double parentY, double dx, double dy){
		super(image,parentX+dx, parentY+dy);
		this.myImage= image;
		this.Parent = parent;
		this.parentDx = dx;
		this.parentDy = dy;
	}
	
	public void setPosition(int moveX, int moveY){
		this.setX(this.getX()+moveX);
		this.setY(this.getY()+moveY);
	}
	
	public void addChild(LimbNode child){
		children.add(child);
	}
	public ArrayList<LimbNode> getChildren(){
		Collections.copy(childrenCopy, children);
		return childrenCopy;
	}
	
	

	public void rotateLimb(int angle){
		//x and y and the coordinates of hinge
		if(this.Parent==null){
			this.myImage = GraphicsTest.rotate(this.myImage,angle, this.getX(),this.getY());
			this.setImage(this.myImage);
		}
		else{
		this.myImage = ImageUtil.rotate(this.myImage,angle);
		this.setImage(this.myImage);
		}
		}
	

	public void render(Graphics2D pen){
		super.render(pen);
	}
	
	public void update(long elapsedTime){

		super.update(elapsedTime);
	}
	
}
