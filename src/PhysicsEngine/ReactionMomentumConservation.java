package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;

/**
 * this reaction is for the case that the collision under the law of momentum
 * conservation and the law of energy conservation
 * 
 * @author Donghe
 * 
 */
public class ReactionMomentumConservation extends Reaction {

	@Override
	public void act(SpriteTemplate spriteOne, SpriteTemplate spriteTwo,
			PhysicsEngine physicsEngine) {
		Point2D moveByOne = spriteOne.getMoveBy();
		Point2D moveByTwo = spriteTwo.getMoveBy();

		double x1 = moveByOne.getX();
		double y1 = moveByOne.getY();
		double x2 = moveByTwo.getX();
		double y2 = moveByTwo.getY();
		double m1 = spriteOne.getMass();
		double m2 = spriteTwo.getMass();
		CollisionStatus status1 = spriteOne.getCollisionStatus();
		CollisionStatus status2 = spriteTwo.getCollisionStatus();

		double horizontalIncrementOne = x1;
		double horizontalIncrementTwo = x2;
		double verticalIncrementOne = y1;
		double verticalIncrementTwo = y2;

		if ((status1.getLeft() && status2.getRight())
				|| (status1.getRight() && status2.getLeft())) {
			horizontalIncrementOne = getIncrement(m1, m2, x1, x2);
			horizontalIncrementTwo = getIncrement(m2, m1, x2, x1);
		}
		if ((status1.getUp() && status2.getDown())
				|| (status1.getDown() && status2.getUp())) {
			verticalIncrementOne = getIncrement(m1, m2, y1, y2);
			verticalIncrementTwo = getIncrement(m2, m1, y2, y1);
		}

		physicsEngine.setNextLocationIncrement(spriteOne,
				horizontalIncrementOne, verticalIncrementOne);
		physicsEngine.setNextLocationIncrement(spriteTwo,
				horizontalIncrementTwo, verticalIncrementTwo);

	}

	/**
	 * get the increment by the law of momentum conservation and the law of
	 * energy conservation
	 */
	private double getIncrement(double m1, double m2, double speed1,
			double speed2) {
		return (m1 - m2) * speed1 / (m1 + m2) + 2 * m2 * speed2 / (m1 + m2);
	}

}
