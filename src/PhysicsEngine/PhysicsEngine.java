package PhysicsEngine;

import sprite.FighterSprite;
import action.MotionAction;

import com.golden.gamedev.GameObject;

public abstract class PhysicsEngine {

	// abstract void update (FighterSprite sprite,
	// GameObject game,
	// long elapsedTime);
	protected double myVectorX;
	protected double myVectorY;
	protected FighterSprite myFighterSprite;
	
	public PhysicsEngine(FighterSprite fighterSprite){
		myFighterSprite=fighterSprite;
	}

	public PhysicsEngine(MotionAction motionAction){
		myFighterSprite=motionAction.getFighterSprite();
		myVectorX=motionAction.getVectorX();
		myVectorY=motionAction.getVectorY();
	}

	public abstract void process(long elapsedTime);

}
