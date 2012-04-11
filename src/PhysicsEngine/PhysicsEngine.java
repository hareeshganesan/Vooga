package PhysicsEngine;


import sprite.SpriteTemplate;
import action.MotionAction;

/**
 * 
 * This is the super
 * 
 * class of physics engine Maybe there will more children engine in the futures
 * 
 * @author Donghe
 * 
 */
public abstract class PhysicsEngine {

	protected double myVectorX;
	protected double myVectorY;

	protected SpriteTemplate myFighterSprite;

	public PhysicsEngine(SpriteTemplate fighterSprite) {

		myFighterSprite = fighterSprite;
	}

	public PhysicsEngine(MotionAction motionAction) {

		myFighterSprite = motionAction.getFighterSprite();
		myVectorX = motionAction.getVectorX();

		myVectorY = motionAction.getVectorY();
	}

	public abstract void process(long elapsedTime);

}
