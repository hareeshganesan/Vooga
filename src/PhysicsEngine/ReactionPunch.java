package PhysicsEngine;

import npsprite.SpriteTemplate;

public class ReactionPunch extends Reaction {

	private double BoundX = 544;
	private double BoundY = 544;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2,
			PhysicsEngine physicsEngine) {
		punch(ps1, ps2, physicsEngine);
		punch(ps2, ps1, physicsEngine);
	}

	private void punch(SpriteTemplate f1, SpriteTemplate f2,
			PhysicsEngine physicsEngine) {
		if (f1.getMoveBy().getX() == 0 && f1.getMoveBy().getY() == 0) {
			double dx = 0;
			double dy = 0;

			if (f2.getMoveBy().getX() > 0)
				dx = BoundX - f1.getWidth() - f1.getX();
			else if (f2.getMoveBy().getX() < 0)
				dx = -f1.getX();

			if (f2.getMoveBy().getY() > 0)
				dy = BoundY - f1.getHeight() - f1.getY();
			else if (f2.getMoveBy().getY() < 0)
				dy = -f1.getY();

			physicsEngine.setNextLocationIncrement(f1, dx, dy);
		}
	}

	public void setBound(double x, double y) {
		BoundX = x;
		BoundY = y;
	}

}
