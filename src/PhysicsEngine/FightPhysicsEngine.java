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

	private double myJump = 100;
	private double myBackgroundFactor = 1.0;
	private double myOutBoundDistance = 10;
	private double mySpeedFactor = 0.5;

	public FightPhysicsEngine(GameEngine gameEngine) {
		super(gameEngine);
	}

	@Override
	public void process(MotionAction motionAction, long elapsedTime) {
		SpriteTemplate sprite = motionAction.getFighterBody();
		double myVectorX = motionAction.getVectorX() * mySpeedFactor;
		double myVectorY = motionAction.getVectorY() * mySpeedFactor;
		double speed = sprite.getSpeed();
		double x = speed * elapsedTime * myVectorX;
		double y = speed * elapsedTime * myVectorY;
		setNextLocationIncrement(sprite, x, y);
	}

	public void setNextLocationIncrement(SpriteTemplate sprite, double x,
			double y) {

		double finalX = x * myBackgroundFactor;
		double finalY = y * myBackgroundFactor;
		// if (myVectorY < 0) {
		// if (myFighterSprite.getY() + myFighterSprite.getHeight() < BOUND_Y )
		// {
		// finalY = 0;
		// } else {
		// finalY = jump * finalY;
		// }
		// }

		if (isOutLeft(sprite, x))
			finalX = myOutBoundDistance;
		if (isOutRight(sprite, x))
			finalX = -myOutBoundDistance;
		if (isOutTop(sprite, y))
			finalY = myOutBoundDistance;
		if (isOutBottom(sprite, y))
			finalY = BOUND_Y - sprite.getY() - sprite.getHeight();

		sprite.setNextLocationIncrement(new Point2D.Double(finalX, finalY));

		// for debug
		// System.out.println(sprite.getGroupID() + "  Left:" + sprite.getX()
		// + "    Right:" + (sprite.getWidth() + sprite.getX())
		// + "    Top:" + sprite.getY() + "    Bottom:"
		// + (sprite.getHeight() + sprite.getY()));
	}

	public void setJump(double jump) {
		myJump = jump;
	}

	public void setBackgroundFactor(double backgroundFactor) {
		myBackgroundFactor = backgroundFactor;
	}

	public void setOutBoundDistance(double outBoundDistance) {
		myOutBoundDistance = outBoundDistance;
	}

	public void setSpeedFactor(double speedFactor) {
		mySpeedFactor = speedFactor;
	}
}
