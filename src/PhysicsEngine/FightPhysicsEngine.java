package PhysicsEngine;

import java.awt.geom.Point2D;
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

	private final int BASE_POINT = 0;
	private final int BOUND_X = 544;
	private final int BOUND_Y = 544;
	private final int SPEED_DEFALT = 10;
	private double jump = 100;
	private double backgroundFactor = 1;
	private double outBoundDistance = 10;

	public FightPhysicsEngine(MotionAction motionAaction) {
		super(motionAaction);
	}

	public FightPhysicsEngine(SpriteTemplate fighterSprite) {
		super(fighterSprite);
	}

	@Override
	public void process(long elapsedTime) {
		double speed = myFighterSprite.getSpeed() / SPEED_DEFALT;
		double x = speed * elapsedTime * myVectorX;
		double y = speed * elapsedTime * myVectorY;

		setNextLocationIncrement(x, y);
	}

	public void setNextLocationIncrement(double x, double y) {

		double finalX = x * backgroundFactor;
		double finalY = y * backgroundFactor;
//		if (myVectorY < 0) {
//			if (myFighterSprite.getY() + myFighterSprite.getHeight() < BOUND_Y ) {
//				finalY = 0;
//			} else {
//				finalY = jump * finalY;
//			}
//		}

		if (isOutLeft(x))
			finalX = outBoundDistance;
		if (isOutRight(x))
			finalX = -outBoundDistance;
		if (isOutTop(y))
			finalY = outBoundDistance;
		if (isOutBottom(y))
			finalY = BOUND_Y - myFighterSprite.getY()
					- myFighterSprite.getHeight();

		myFighterSprite.setNextLocationIncrement(new Point2D.Double(finalX,
				finalY));

		// for debug
		 System.out.println("Left:" + myFighterSprite.getX() + "    Right:"
		 + (myFighterSprite.getWidth() + myFighterSprite.getX())
		 + "    Top:" + myFighterSprite.getY() + "    Bottom:"
		 + (myFighterSprite.getHeight() + myFighterSprite.getY()));
	}

	public boolean isOutLeft(double x) {
		return myFighterSprite.getX() + x < BASE_POINT;
	}

	public boolean isOutRight(double x) {
		return myFighterSprite.getX() + x + myFighterSprite.getWidth() > BOUND_X;
	}

	public boolean isOutTop(double y) {
		return myFighterSprite.getY() + y < BASE_POINT;
	}

	public boolean isOutBottom(double y) {
		return myFighterSprite.getY() + y + myFighterSprite.getHeight() > BOUND_Y;
	}
	
	public void setJump(double j) {
		jump = j;
	}

	public void setBackgroundFactor(double b) {
		backgroundFactor = b;
	}
	
	public void setOutBoundDistance(double b){
		outBoundDistance=b;
	}

}
