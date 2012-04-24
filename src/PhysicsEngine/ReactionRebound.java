package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;

public class ReactionRebound extends Reaction {

	private double reboundFactor = 20.0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2,
			PhysicsEngine physicsEngine) {
		if (ps1.getClass().equals(PlatformBlock.class)) {
			rebound(ps2, physicsEngine);
		} else if (ps2.getClass().equals(PlatformBlock.class)) {
			rebound(ps1, physicsEngine);
		}

	}

	private void rebound(SpriteTemplate fighterSprite,
			PhysicsEngine physicsEngine) {
		Point2D p = fighterSprite.getMoveBy();
		double x = -p.getX() * reboundFactor;
		double y = -p.getY() * reboundFactor;

		physicsEngine.setNextLocationIncrement(fighterSprite, x, y);
	}

	public void setReboundFactor(double b) {
		reboundFactor = b;
	}

}
