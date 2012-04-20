package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionStop extends Reaction {

	private double x1 = 0;
	private double y1 = 0;
	private double x2 = 0;
	private double y2 = 0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		myPhysicsEngine = new FightPhysicsEngine(ps1);
		myPhysicsEngine.setNextLocationIncrement(x1, y1);

		myPhysicsEngine = new FightPhysicsEngine(ps2);
		myPhysicsEngine.setNextLocationIncrement(x2, y2);

	}

	public void setStopPosition(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

}
