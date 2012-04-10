package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;


import SpriteTree.GraphicsTest;

import com.golden.gamedev.util.ImageUtil;

import com.golden.gamedev.object.Sprite;

//TODO: RENAME
public class LimbNodeCopy extends SpriteTemplate{
	
	private BufferedImage myImage;
	private double parentDx;
	private double parentDy;
	private LimbNodeCopy Parent;
	private ArrayList<LimbNodeCopy> children = new ArrayList<LimbNodeCopy>();
	private ArrayList<LimbNodeCopy> childrenCopy;//for getter
	
	//to be implemented
	double damageMultiplier;
	double damageDealt;
	
	
	public LimbNodeCopy(LimbNodeCopy parent, BufferedImage image,double parentX, double parentY, double dx, double dy){
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
	
	public ArrayList<LimbNodeCopy> getChildren(){
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
	
    protected void setParent(LimbNodeCopy parent){
        Parent=parent;
    }

    public void addChild(LimbNodeCopy child) {
        child.setParent(this);
        children.add(child);
    }

    public void removeChild(LimbNodeCopy child) {
        if (children==null){ 
            //do nothing 
        }
        else if (!children.contains(child)){
            for (LimbNodeCopy c:children){
                c.removeChild(child);
            }
        }
        children.remove(child);
        child.setParent(null);
    }

    @Override
    public void collisionAction(SpriteTemplate otherSprite) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Point2D confineBounds(double dx, double dy) {
        // TODO Auto-generated method stub
        return null;
    }
	
}
