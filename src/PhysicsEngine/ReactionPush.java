package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;

/**
 * this class works for the case that one push the other and go together
 * 
 * @author Donghe
 * 
 */
public class ReactionPush extends Reaction {

	@Override
	public void act(SpriteTemplate spriteOne, SpriteTemplate spriteTwo,
			PhysicsEngine physicsEngine) {
		Point2D p1 = spriteOne.getMoveBy();
		Point2D p2 = spriteTwo.getMoveBy();
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double horizontalIncrementOne = x1;
		double verticalIncrementOne = y1;
		double horizontalIncrementTwo = x2;
		double verticalIncrementTwo = y2;
		CollisionStatus status1 = spriteOne.getCollisionStatus();
		CollisionStatus status2 = spriteTwo.getCollisionStatus();

		if (status1.getRight() && status2.getLeft()) {
			if (x1 > x2) {
				horizontalIncrementOne = (x1 + x2) / 2;
				horizontalIncrementTwo = (x1 + x2) / 2;
			}
		} else if (status1.getLeft() && status2.getRight()) {
			if (x1 < x2) {
				horizontalIncrementOne = (x1 + x2) / 2;
				horizontalIncrementTwo = (x1 + x2) / 2;
			}
		}

		if (status1.getDown() && status2.getUp()) {
			if (y1 > y2) {
				verticalIncrementOne = (y1 + y2) / 2;
				verticalIncrementTwo = (y1 + y2) / 2;
			}
		} else if (status1.getUp() && status2.getDown()) {
			if (y1 < y2) {
				verticalIncrementOne = (y1 + y2) / 2;
				verticalIncrementTwo = (y1 + y2) / 2;
			}
		}

		physicsEngine.setNextLocationIncrement(spriteOne,
				horizontalIncrementOne, verticalIncrementOne);
		physicsEngine.setNextLocationIncrement(spriteTwo,
				horizontalIncrementTwo, verticalIncrementTwo);
	}

}
