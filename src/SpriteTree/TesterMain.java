package SpriteTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class TesterMain extends Game {
	private BodyTree myTree;
	private Graphics2D myPen;
	private LimbNode leg;
	private LimbNode lowerLeg;
	private int sw = 0;
	
	private int currTime =0;
	private Animation animation = new DemoAniOne(currTime);

	@Override
	public void initResources() {
	
		BufferedImage imgH = GraphicsTest.loadImage("src/resources/bodyParts/circle.png");
		BufferedImage imgT = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgLA = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgRA = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgLL = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgRL = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		

		BufferedImage imgLRL = GraphicsTest.loadImage("src/resources/bodyParts/nLimb.png");

		
		LimbNode torso = new LimbNode(imgT, this.getWidth()/2, this.getHeight()/2);
		LimbNode head = new LimbNode(torso,imgH, torso.getWidth()/3,-5,0);
		LimbNode LeftArm = new LimbNode(torso,imgLA, -15,0,45);		
		LimbNode RightArm = new LimbNode(torso,imgRA, 15,0,-45);
		LimbNode LeftLeg = new LimbNode(torso,imgLL, -15,torso.getHeight()/2,45);
		LimbNode RightLeg= new LimbNode(torso,imgRL, 15,torso.getHeight()/2,-45);
		
		LimbNode LRightLeg = new LimbNode(RightLeg, imgLRL, 0, RightLeg.getHeight()/2, 45);
		RightLeg.addChild(LRightLeg);
		
		
		leg = RightLeg;
		lowerLeg = LRightLeg;
		
		
		torso.addChild(LeftLeg);
		torso.addChild(RightLeg);
		torso.addChild(RightArm);
		torso.addChild(LeftArm);
		torso.addChild(head);
		
		myTree = new BodyTree(torso);
		
		

	}

	
	@Override
	public void render(Graphics2D pen) {
		pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        myPen = pen;
        myTree.render(pen);
	}

	public void animationOne(){
			
			if(currTime<=40){
				leg.rotate(-1);
				lowerLeg.rotate(2);
			}
			if(currTime>40 && currTime <=80){
				lowerLeg.rotate(-4);
			}
			
			
			if(currTime>80 && currTime<=120){
				lowerLeg.rotate(4);

			}
			if(currTime >120 && currTime<=160){
				leg.rotate(1);
				lowerLeg.rotate(-2);
			}
	}
	
	
	@Override
	public void update(long elapsedTime) {
		currTime +=1;
		//animation begins, extract into methods?
//		if(currTime<=40){
//			leg.rotate(-1);
//			lowerLeg.rotate(2);
//		}
//		if(currTime>40 && currTime <=80){
//			lowerLeg.rotate(-4);
//		}
//		if(currTime>80 && currTime<=120){
//			lowerLeg.rotate(4);
//
//		}
//		if(currTime >120 && currTime<=160){
//			leg.rotate(1);
//			lowerLeg.rotate(-2);

//		}	
		//animation ends
		
		this.animation.animate(leg, lowerLeg);
		
		
		if(keyDown(KeyEvent.VK_LEFT)){
			myTree.move(myPen,-1, 0);
		}
		if(keyDown(KeyEvent.VK_RIGHT)){
			myTree.move(myPen,1, 0);
		}
		if(keyDown(KeyEvent.VK_UP)){
			myTree.move(myPen,0, -1);
		}
		if(keyDown(KeyEvent.VK_DOWN)){
			myTree.move(myPen,0, 1);
		}
		
		if(keyDown(KeyEvent.VK_SPACE)){
			this.animation = new DemoAniOne(currTime);

		}
		

		
	
	}
	
	 public static void main (String[] args)
	    {
	        GameLoader loader = new GameLoader();
	        loader.setup(new TesterMain(), new Dimension(800, 600), false);
	        loader.start();
	    }
}
