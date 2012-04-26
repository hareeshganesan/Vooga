package action;

import PhysicsEngine.PhysicsEngine;
import npsprite.FighterBody;

public class FollowAction implements Action {
	FighterBody myEnemy;
	FighterBody myFighter;
	boolean done;
	PhysicsEngine myPhysicsEngine;

	public FollowAction(FighterBody fighter, FighterBody enemy,
			PhysicsEngine physicsEngine) {
		myFighter = fighter;
		myEnemy = enemy;
		myPhysicsEngine = physicsEngine;
	}

	@Override
	public void performAction(long elapsedTime) {
		MotionAction f = new MotionAction(myFighter,
				myEnemy.getCurrentLocation(), myPhysicsEngine);
		f.performAction(elapsedTime);
		done = true;
	}

	public boolean isDone(long elapsedTime) {
		return done;
	}

}
