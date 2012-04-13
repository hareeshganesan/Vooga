package SpriteTree;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import com.golden.gamedev.util.ImageUtil;

import com.golden.gamedev.object.Sprite;

public class LimbNode extends Sprite{
	
	private String myName;
	
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
	
	//constructor for root node who doesn't have a parent 
	public LimbNode(String name, BufferedImage image, double x, double y){
		super(image, x, y);
		this.myName = name;
		this.myOrigImage = image;
	}
	
	public LimbNode copy(LimbNode other){
		return new LimbNode(other.myName,other.Parent,other.getImage(),other.dx,other.dy,other.theta);
		
	}
	
	public String getName(){
		return this.myName;
	}
	
	
	public ArrayList<LimbNode> getChildren(){
		return this.children;
	}
	public LimbNode(String name, LimbNode parent, BufferedImage image,double dx, double dy, int baseTheta){
		super(image,parent.getX()+dx, parent.getY()+dy);
		this.myName = name;
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
	
	public void print(){
		System.out.println(this.Parent);
		System.out.println(this.dx);
		System.out.println(this.dy);
		System.out.println(this.theta);
	}
	
}
