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
	
	@Override
	public void initResources() {
	//	bg = new Background();
		BufferedImage imgHead = GraphicsTest.loadImage("src/resources/bodyParts/head.png");
		BufferedImage imgLArm = GraphicsTest.loadImage("src/resources/bodyParts/leftArm.png");
		BufferedImage imgRArm = GraphicsTest.loadImage("src/resources/bodyParts/rightArm.png");
		BufferedImage imgTorso = GraphicsTest.loadImage("src/resources/bodyParts/torso.png");
		BufferedImage imgLLeg = GraphicsTest.loadImage("src/resources/bodyParts/leftLeg.png");
		BufferedImage imgRLeg= GraphicsTest.loadImage("src/resources/bodyParts/rightLeg.png");

		LimbNode torso = new LimbNode(null, imgTorso, 100,100,0,0);	
		LimbNode head = new LimbNode(torso,imgHead,torso.getX(),torso.getY(),0,-(imgTorso.getHeight()/2));
		LimbNode LeftArm = new LimbNode(torso,imgLArm,torso.getX(),torso.getY(),-10,+10);
		LimbNode RightArm = new LimbNode(torso,imgRArm,torso.getX(),torso.getY(),torso.getWidth()-5,-10);
		LimbNode LeftLeg = new LimbNode(torso,imgLLeg, torso.getX(),torso.getOldY(),0,+60);
		LimbNode RightLeg = new LimbNode(torso,imgRLeg, torso.getX(),torso.getOldY(),+10,+60);
		
		
		torso.addChild(RightLeg);
		torso.addChild(LeftLeg);
		torso.addChild(LeftArm);
		torso.addChild(RightArm);
		torso.addChild(head);
		
		
		BodyTree tree = new BodyTree();
		tree.add(torso);
		myTree = tree;
		
	}

	@Override
	public void render(Graphics2D pen) {
		pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
		myTree.render(pen);
		
	}

	@Override
	public void update(long elapsedTime) {
		myTree.update(elapsedTime);

		if(keyDown(KeyEvent.VK_RIGHT)){
			myTree.move(1,0,myTree.root);
		}
		if(keyDown(KeyEvent.VK_LEFT)){
			myTree.move(-1,0,myTree.root);
		}
		if(keyDown(KeyEvent.VK_UP)){
			myTree.move(0,-1,myTree.root);
		}
		if(keyDown(KeyEvent.VK_DOWN)){
			myTree.move(0,1,myTree.root);
		}
	
	}
	
	 public static void main (String[] args)
	    {
	        GameLoader loader = new GameLoader();
	        loader.setup(new TesterMain(), new Dimension(800, 600), false);
	        loader.start();
	    }
}
