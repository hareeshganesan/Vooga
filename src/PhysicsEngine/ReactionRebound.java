package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;

public class ReactionRebound extends Reaction {

	private double reboundFactor = 20.0;
	private double limit = 2.0;

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2,
			PhysicsEngine physicsEngine) {
		if (ps1.getClass().equals(PlatformBlock.class)) {
			rebound(ps2, ps1, physicsEngine);
		} else if (ps2.getClass().equals(PlatformBlock.class)) {
			rebound(ps1, ps2, physicsEngine);
		}

	}

	private void rebound(SpriteTemplate fighterSprite, SpriteTemplate block,
			PhysicsEngine physicsEngine) {
		Point2D p = fighterSprite.getMoveBy();
		double x = 0;
		double y = 0;
//		if (p.getY() < limit && p.getY() >=0 ) {
//			x = p.getX();
//		}else if(p.getY() <0 ) {
//			x = p.getX();
//			y = p.getY();
//		}
//		
//		else {
			x = -p.getX() * reboundFactor;
			y = -p.getY() * reboundFactor;
		

		physicsEngine.setNextLocationIncrement(fighterSprite, x, y);
	}

	public void setReboundFactor(double b) {
		reboundFactor = b;
	}

}
