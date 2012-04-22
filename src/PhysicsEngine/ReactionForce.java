package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionForce extends Reaction {

	private double ForceX1 = 30.0;
	private double ForceY1 = 30.0;
	private double ForceX2 = 30.0;
	private double ForceY2 = 30.0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		double m1 = ps1.getMass();
		double m2 = ps2.getMass();

		double x1 = ForceX2 / m1;
		double y1 = ForceY2 / m1;
		double x2 = ForceX1 / m2;
		double y2 = ForceY1 / m2;

		myPhysicsEngine = new FightPhysicsEngine(ps1);
		myPhysicsEngine.setNextLocationIncrement(x1, y1);

		myPhysicsEngine = new FightPhysicsEngine(ps2);
		myPhysicsEngine.setNextLocationIncrement(x2, y2);

	}

	public void setPower(double px1, double py1, double px2, double py2) {
		ForceX1 = px1;
		ForceY1 = py1;
		ForceX2 = px2;
		ForceY2 = py2;
	}

}
