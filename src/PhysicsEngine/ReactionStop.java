package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionStop extends Reaction {

	private double x1 = 0;
	private double y1 = 0;
	private double x2 = 0;
	private double y2 = 0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2,
			PhysicsEngine physicsEngine) {
		physicsEngine.setNextLocationIncrement(ps1, x1, y1);
		physicsEngine.setNextLocationIncrement(ps2, x2, y2);
	}

	public void setStopPosition(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

}
