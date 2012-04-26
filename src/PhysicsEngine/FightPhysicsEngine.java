package PhysicsEngine;

import java.awt.geom.Point2D;

import com.golden.gamedev.GameEngine;

import npsprite.FighterBody;
import npsprite.NodeSprite;
import npsprite.SpriteTemplate;
import action.MotionAction;

/**
 * the subclass of PhysicsEngine hope this can deal with most kinds of physics
 * problems
 * 
 * @author Donghe
 */
public class FightPhysicsEngine extends PhysicsEngine {

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

	@Override
	public void setNextLocationIncrement(SpriteTemplate sprite, double x,
			double y) {

		double finalX = x * myBackgroundFactor;
		double finalY = y * myBackgroundFactor;

		if (isOutLeft(sprite, x))
			finalX = myOutBoundDistance;
		if (isOutRight(sprite, x))
			finalX = -myOutBoundDistance;
		if (isOutUp(sprite, y))
			finalY = myOutBoundDistance;
		if (isOutDown(sprite, y)) {
			finalY = myBoundDown - sprite.getY() - sprite.getHeight();
		}

		sprite.setNextLocationIncrement(new Point2D.Double(finalX, finalY));

		// for debug
		// System.out.println(sprite.getGroupID() + "  Left:" + sprite.getX()
		// + "    Right:" + (sprite.getWidth() + sprite.getX())
		// + "    Top:" + sprite.getY() + "    Bottom:"
		// + (sprite.getHeight() + sprite.getY()));
	}

	/**
	 * set a new speed factor for this physics engine its default value is 1.0
	 * For example, if we develop this game in the water, maybe we need to set
	 * it to 0.8
	 * 
	 * @param backgroundFactor
	 *            the new factor
	 */
	public void setBackgroundFactor(double backgroundFactor) {
		myBackgroundFactor = backgroundFactor;
	}

	/**
	 * when sprites hit the up bound, left bound and right bound, we make them a
	 * rebound distantce
	 * 
	 * @param outBoundDistance
	 *            the rebound distance
	 */
	public void setOutBoundDistance(double outBoundDistance) {
		myOutBoundDistance = outBoundDistance;
	}

	/**
	 * a speed factor works for motion's speed, its default value is 0.5
	 * 
	 * @param speedFactor
	 *            speed factor
	 */
	public void setSpeedFactor(double speedFactor) {
		mySpeedFactor = speedFactor;
	}
}
