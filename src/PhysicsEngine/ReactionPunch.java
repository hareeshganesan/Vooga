package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionPunch extends Reaction {

	private final double STOP = 0;
	private double BoundLeft = 0;
	private double BoundUp = 0;
	private double BoundRight = 544;
	private double BoundDown = 544;

	@Override
	public void act(SpriteTemplate spriteOne, SpriteTemplate spriteTwo,
			PhysicsEngine physicsEngine) {
		double horizontalIncrementOne = spriteOne.getMoveBy().getX();
		double verticalIncrementOne = spriteOne.getMoveBy().getY();
		double horizontalIncrementTwo = spriteTwo.getMoveBy().getX();
		double verticalIncrementTwo = spriteTwo.getMoveBy().getY();
		CollisionStatus status1 = spriteOne.getCollisionStatus();
		CollisionStatus status2 = spriteTwo.getCollisionStatus();

		if (status1.getRight() && status2.getLeft()) {
			if (spriteOne.getMoveBy().getX() == STOP)
				horizontalIncrementOne = getLeft(spriteOne);
			if (spriteTwo.getMoveBy().getX() == STOP)
				horizontalIncrementTwo = getRight(spriteTwo);
		} else if (status1.getLeft() && status2.getRight()) {
			if (spriteOne.getMoveBy().getX() == STOP)
				horizontalIncrementOne = getRight(spriteOne);
			if (spriteTwo.getMoveBy().getX() == STOP)
				horizontalIncrementTwo = getLeft(spriteTwo);
		}
		if (status1.getDown() && status2.getUp()) {
			if (spriteOne.getMoveBy().getY() == STOP)
				verticalIncrementOne = getDown(spriteOne);
			if (spriteTwo.getMoveBy().getY() == STOP)
				verticalIncrementTwo = getUp(spriteTwo);
		} else if (status1.getUp() && status2.getDown()) {
			if (spriteOne.getMoveBy().getY() == STOP)
				verticalIncrementOne = getUp(spriteOne);
			if (spriteTwo.getMoveBy().getY() == STOP)
				verticalIncrementTwo = getDown(spriteTwo);
		}
		physicsEngine.setNextLocationIncrement(spriteOne,
				horizontalIncrementOne, verticalIncrementOne);
		physicsEngine.setNextLocationIncrement(spriteTwo,
				horizontalIncrementTwo, verticalIncrementTwo);
	}

	public void setBound(double up, double down, double left, double right) {
		BoundUp = up;
		BoundDown = down;
		BoundRight = right;
		BoundDown = down;
	}

	private double getLeft(SpriteTemplate sprite) {
		return BoundLeft - sprite.getX();
	}

	private double getRight(SpriteTemplate sprite) {
		return BoundRight - sprite.getWidth() - sprite.getX();
	}

	private double getUp(SpriteTemplate sprite) {
		return BoundUp - sprite.getY();
	}

	private double getDown(SpriteTemplate sprite) {
		return BoundDown - sprite.getHeight() - sprite.getY();
	}

}
