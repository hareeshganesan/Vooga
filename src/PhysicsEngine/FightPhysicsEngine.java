package PhysicsEngine;

import java.awt.geom.Point2D;

import sprite.FighterSprite;

import action.MotionAction;

public class FightPhysicsEngine extends PhysicsEngine {

	private final int BASE_POINT = 0;
	private final int BOUND_X = 544;
	private final int BOUND_Y = 544;

	public FightPhysicsEngine(MotionAction motionAaction) {
		super(motionAaction);
	}

	public FightPhysicsEngine(FighterSprite fighterSprite) {
		super(fighterSprite);
	}

	@Override
	public void process(long elapsedTime) {
		double speed = myFighterSprite.getSpeed() / 10;
		double x = speed * elapsedTime * myVectorX;
		double y = speed * elapsedTime * myVectorY;

		checkCollision();
		setNextLocation(x, y);
	}

	public void checkCollision() {

	}

	public void setNextLocation(double x, double y) {

		double finalX = x;
		double finalY = y;
		if (isOutLeft(x))
			finalX = -myFighterSprite.getX();
		if (isOutRight(x))
			finalX = BOUND_X - myFighterSprite.getX();
		if (isOutTop(y))
			finalX = -myFighterSprite.getY();
		if (isOutBottom(y))
			finalX = BOUND_Y - myFighterSprite.getY();

		myFighterSprite.setNextLocation(new Point2D.Double(finalX, finalY));
	}

	// public boolean iOutBound(double x, double y) {
	// return isOutLeft(x) || isOutRight(x) || isOutTop(y) || isOutBottom(y);
	//
	// }

	public boolean isOutLeft(double x) {
		return myFighterSprite.getX() + x <= BASE_POINT;
	}

	public boolean isOutRight(double x) {
		return myFighterSprite.getX() + x >= BOUND_X;
	}

	public boolean isOutTop(double y) {
		return myFighterSprite.getY() + y <= BASE_POINT;
	}

	public boolean isOutBottom(double y) {
		return myFighterSprite.getY() + y >= BOUND_Y;
	}

}
