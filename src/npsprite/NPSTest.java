package npsprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import sprite.HealthDisplay;
import sprite.SpriteValues;


import action.CollisionEvent;
import action.PowerUpEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class NPSTest extends Game {

	private FighterBody myTree;
	private HealthSprite power;
	
	@Override
	public void initResources() {
	//	bg = new Background();
		BufferedImage imgHead = GraphicsTest.loadImage("src/resources/bodyParts/head.png");
		BufferedImage imgLArm = GraphicsTest.loadImage("src/resources/bodyParts/leftArm.png");
		BufferedImage imgRArm = GraphicsTest.loadImage("src/resources/bodyParts/rightArm.png");
		BufferedImage imgTorso = GraphicsTest.loadImage("src/resources/bodyParts/torso.png");
		BufferedImage imgLLeg = GraphicsTest.loadImage("src/resources/bodyParts/leftLeg.png");
		BufferedImage imgRLeg= GraphicsTest.loadImage("src/resources/bodyParts/rightLeg.png");

		NodeSprite torso = new NodeSprite(null, imgTorso, 100,100,0,0);	
		NodeSprite head = new NodeSprite(torso,imgHead,torso.getX(),torso.getY(),0,-(imgTorso.getHeight()/2));
		NodeSprite LeftArm = new NodeSprite(torso,imgLArm,torso.getX(),torso.getY(),-10,+10);
		NodeSprite RightArm = new NodeSprite(torso,imgRArm,torso.getX(),torso.getY(),torso.getWidth()-5,-10);
		NodeSprite LeftLeg = new NodeSprite(torso,imgLLeg, torso.getX(),torso.getOldY(),0,+60);
		NodeSprite RightLeg = new NodeSprite(torso,imgRLeg, torso.getX(),torso.getOldY(),+10,+60);
		

        torso.setSpriteID(SpriteValues.Id.PLAYER_1);
        torso.addHealth(-40);
		torso.addChild(RightLeg);
		torso.addChild(LeftLeg);
		torso.addChild(LeftArm);
		torso.addChild(RightArm);
		torso.addChild(head);
		
		FighterBody tree = new FighterBody("test", new HealthDisplay(10, 10, 100));
		tree.add(torso);
		myTree = tree;
		
		
		power=new HealthSprite(getImage("resources/block.png"));
		power.setLocation(400, 300);
		power.setSpriteID(SpriteValues.Id.POWER_UP);
		power.addCollisionEvent(new PowerUpEvent(torso));
		
	}

	@Override
	public void render(Graphics2D pen) {
		pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
		myTree.render(pen);
		power.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		myTree.update(elapsedTime);
		power.update(elapsedTime);
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
        if(keyDown(KeyEvent.VK_C)){
            fakeCollision(power,myTree.root);
        }
	}
	
	public void fakeCollision(SpriteTemplate p1, SpriteTemplate p2){
	    p1.collisionAction(p2);
//	    p2.collisionAction(p1);
	}
	 public static void main (String[] args)
	    {
	        GameLoader loader = new GameLoader();
	        loader.setup(new NPSTest(), new Dimension(800, 600), false);
	        loader.start();
	    }
}
