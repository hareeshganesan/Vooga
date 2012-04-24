package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionForce extends Reaction {

	private double ForceX1 = 3000.0;
	private double ForceY1 = 3000.0;
	private double ForceX2 = -3000.0;
	private double ForceY2 = -3000.0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2, PhysicsEngine physicsEngine) {
		double m1 = ps1.getMass();
		double m2 = ps2.getMass();

		double x1 = ForceX2 / m1;
		double y1 = ForceY2 / m1;
		double x2 = ForceX1 / m2;
		double y2 = ForceY1 / m2;

		physicsEngine.setNextLocationIncrement(ps1, x1, y1);
		physicsEngine.setNextLocationIncrement(ps2, x2, y2);

	}

	public void setPower(double px1, double py1, double px2, double py2) {
		ForceX1 = px1;
		ForceY1 = py1;
		ForceX2 = px2;
		ForceY2 = py2;
	}

}
