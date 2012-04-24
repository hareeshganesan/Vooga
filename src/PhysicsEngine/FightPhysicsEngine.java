package PhysicsEngine;

import java.awt.geom.Point2D;

import com.golden.gamedev.GameEngine;

import npsprite.SpriteTemplate;
import action.MotionAction;

/**
 * The fighting physics engine Calculation the new location of the sprite check
 * whether there is a collision if the sprite moves in this way if there is a
 * collision then have a collision reaction check whether there is out of bound
 * if yes we need to make the sprite move to the bound instead of out of bound
 * 
 * @author Donghe
 */
public class FightPhysicsEngine extends PhysicsEngine {

	private double speedFactor = 10;
	private double jump = 100;
	private double backgroundFactor = 1;
	private double outBoundDistance = 10;

	public FightPhysicsEngine(GameEngine gameEngine) {
		super(gameEngine);
	}

	@Override
	public void process(MotionAction motionAction, long elapsedTime) {
		SpriteTemplate sprite = motionAction.getFighterBody();
		double myVectorX = motionAction.getVectorX();
		double myVectorY = motionAction.getVectorY();
		double speed = sprite.getSpeed() / speedFactor;
		double x = speed * elapsedTime * myVectorX;
		double y = speed * elapsedTime * myVectorY;
		setNextLocationIncrement(sprite, x, y);
	}

	public void setNextLocationIncrement(SpriteTemplate sprite, double x,
			double y) {

		double finalX = x * backgroundFactor;
		double finalY = y * backgroundFactor;
		// if (myVectorY < 0) {
		// if (myFighterSprite.getY() + myFighterSprite.getHeight() < BOUND_Y )
		// {
		// finalY = 0;
		// } else {
		// finalY = jump * finalY;
		// }
		// }

		if (isOutLeft(sprite, x))
			finalX = outBoundDistance;
		if (isOutRight(sprite, x))
			finalX = -outBoundDistance;
		if (isOutTop(sprite, y))
			finalY = outBoundDistance;
		if (isOutBottom(sprite, y))
			finalY = BOUND_Y - sprite.getY() - sprite.getHeight();

		sprite.setNextLocationIncrement(new Point2D.Double(finalX, finalY));

		// for debug
		// System.out.println("Left:" + myFighterSprite.getX() + "    Right:"
		// + (myFighterSprite.getWidth() + myFighterSprite.getX())
		// + "    Top:" + myFighterSprite.getY() + "    Bottom:"
		// + (myFighterSprite.getHeight() + myFighterSprite.getY()));
	}

	public void setJump(double j) {
		jump = j;
	}

	public void setBackgroundFactor(double b) {
		backgroundFactor = b;
	}

	public void setOutBoundDistance(double b) {
		outBoundDistance = b;
	}

	public void setSpeedFactor(double b) {
		speedFactor = b;
	}

}
