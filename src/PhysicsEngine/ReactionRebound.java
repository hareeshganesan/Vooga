package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;

public class ReactionRebound extends Reaction {

	private double reboundFactor = 5.0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getClass().equals(PlatformBlock.class)) {
			rebound(ps2);
		} else if (ps2.getClass().equals(PlatformBlock.class)) {
			rebound(ps1);
		}

	}

	private void rebound(SpriteTemplate fighterSprite) {
		Point2D p = fighterSprite.getMoveBy();
		double x = -p.getX() * reboundFactor;
		double y = -p.getY() * reboundFactor;

		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocationIncrement(x, y);
	}

	public void setReboundFactor(double b) {
		reboundFactor = b;
	}

}
