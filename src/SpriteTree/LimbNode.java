package SpriteTree;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import com.golden.gamedev.util.ImageUtil;

import com.golden.gamedev.object.Sprite;

public class LimbNode extends Sprite{
	
	private BufferedImage myCurrImage;
	private BufferedImage myOrigImage;
	
	private int allAngles = 0;
	private int currAngle = 0;
	
	
	private double dx;
	private double dy;
	private int theta;
	private LimbNode Parent;
	
	private ArrayList<LimbNode> children = new ArrayList<LimbNode>();
	
	//to be implemented
	double damageMultiplier;
	double damageDealt;
	
	//constructor for parent
	public LimbNode(BufferedImage image, double x, double y){
		super(image, x, y);
		this.myOrigImage = image;
	}
	
	public LimbNode(LimbNode parent, BufferedImage image,double dx, double dy, int baseTheta){
		super(image,parent.getX()+dx, parent.getY()+dy);
		this.myOrigImage= image;
		this.Parent = parent;
		this.theta = baseTheta;
		this.dx = dx;
		this.dy = dy;
	
	}
	
	public void rotate(int dTheta)
	{	
		this.theta += dTheta;
		
	}
	
	
	public void addChild(LimbNode child){
		
		children.add(child);
		
	}
	public void draw(double x, double y, int theta){
		this.setX(x);
		this.setY(y);
	    this.myCurrImage =GraphicsTest.rotate(this.myOrigImage,theta);
		this.setImage(this.myCurrImage);
	}

	public void render(Graphics2D pen,double baseX, double baseY, int baseTheta){
		
		
		super.render(pen);
		
		double dx =Math.cos(Math.toRadians(baseTheta)) * this.dx - Math.sin(Math.toRadians(baseTheta)) * this.dy;
		double dy =Math.sin(Math.toRadians(baseTheta)) * this.dx + Math.cos(Math.toRadians(baseTheta)) * this.dy;

		
		draw((baseX + dx), (baseY + dy),this.theta+baseTheta);
		
		
		for(LimbNode limb: this.children){
			limb.render(pen, (baseX + dx), (baseY + dy), this.theta+baseTheta);
		}
		

	}
	
	public void update(long elapsedTime){

		super.update(elapsedTime);
	}
	
}
