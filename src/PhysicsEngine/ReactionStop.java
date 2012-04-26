package PhysicsEngine;

import npsprite.SpriteTemplate;

/**
 * this class works for the case that two sprites collide and then we want them
 * both stop with a certain distance between them
 * 
 * @author Donghe
 * 
 */
public class ReactionStop extends Reaction {

	private final double FACTOR = 0.5;
	private double myDistance = 0.2;
	private double myStopPositionXOne = 0;
	private double myStopPositionYOne = 0;
	private double myStopPositionXTwo = 0;
	private double myStopPositionYTwo = 0;

	public ReactionStop() {

	}

	public ReactionStop(double dis) {
		myDistance = dis;
	}

	@Override
	public void act(SpriteTemplate spriteOne, SpriteTemplate spriteTwo,
			PhysicsEngine physicsEngine) {
		CollisionStatus status1 = spriteOne.getCollisionStatus();
		CollisionStatus status2 = spriteTwo.getCollisionStatus();
		double horizontalIncrementOne = myStopPositionXOne;
		double verticalIncrementOne = myStopPositionYOne;
		double horizontalIncrementTwo = myStopPositionXTwo;
		double verticalIncrementTwo = myStopPositionYTwo;
		if (status1.getLeft() && status2.getRight()) {
			horizontalIncrementOne = myStopPositionXOne + myDistance * FACTOR;
			horizontalIncrementTwo = myStopPositionXTwo - myDistance * FACTOR;
		} else if (status1.getRight() && status2.getLeft()) {
			horizontalIncrementOne = myStopPositionXOne - myDistance * FACTOR;
			horizontalIncrementTwo = myStopPositionXTwo + myDistance * FACTOR;
		}
		if (status1.getUp() && status2.getDown()) {
			verticalIncrementOne = myStopPositionYOne + myDistance * FACTOR;
			verticalIncrementTwo = myStopPositionYTwo - myDistance * FACTOR;
		} else if (status1.getDown() && status2.getUp()) {
			verticalIncrementOne = myStopPositionYOne - myDistance * FACTOR;
			verticalIncrementTwo = myStopPositionYTwo + myDistance * FACTOR;
		}

		physicsEngine.setNextLocationIncrement(spriteOne,
				horizontalIncrementOne, verticalIncrementOne);
		physicsEngine.setNextLocationIncrement(spriteTwo,
				horizontalIncrementTwo, verticalIncrementTwo);
	}

	/**
	 * the stop position for these two sprites
	 */
	public void setStopPosition(double x1, double y1, double x2, double y2) {
		this.myStopPositionXOne = x1;
		this.myStopPositionXTwo = x2;
		this.myStopPositionYOne = y1;
		this.myStopPositionYTwo = y2;
	}

	/**
	 * the distance we want them to keep
	 */
	public void setDistance(double dis) {
		myDistance = dis;
	}

}
