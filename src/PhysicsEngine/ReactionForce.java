package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionForce extends Reaction {

	private double myHorizontalForceOne = 3000.0;
	private double myVerticalForceOne = 3000.0;
	private double myHorizontalForceTwo = -3000.0;
	private double myVerticalForceTwo = -3000.0;

	public ReactionForce() {

	}

	public ReactionForce(double horizontalForceOne, double verticalForceOne,
			double horizontalForceTwo, double verticalForceTwo) {
		myHorizontalForceOne = horizontalForceOne;
		myVerticalForceOne = verticalForceOne;
		myHorizontalForceTwo = horizontalForceTwo;
		myVerticalForceTwo = verticalForceTwo;
	}

	@Override
	public void act(SpriteTemplate spriteOne, SpriteTemplate spriteTwo,
			PhysicsEngine physicsEngine) {
		double massOne = spriteOne.getMass();
		double massTwo = spriteTwo.getMass();

		double horizontalIncrementOne = myHorizontalForceTwo / massOne;
		double verticalIncrementOne = myVerticalForceTwo / massOne;
		double horizontalIncrementTwo = myHorizontalForceOne / massTwo;
		double verticalIncrementTwo = myVerticalForceOne / massTwo;

		physicsEngine.setNextLocationIncrement(spriteOne,
				horizontalIncrementOne, verticalIncrementOne);
		physicsEngine.setNextLocationIncrement(spriteTwo,
				horizontalIncrementTwo, verticalIncrementTwo);

	}

	public void setPower(double horizontalForceOne, double verticalForceOne,
			double horizontalForceTwo, double verticalForceTwo) {
		myHorizontalForceOne = horizontalForceOne;
		myVerticalForceOne = verticalForceOne;
		myHorizontalForceTwo = horizontalForceTwo;
		myVerticalForceTwo = verticalForceTwo;
	}

}
