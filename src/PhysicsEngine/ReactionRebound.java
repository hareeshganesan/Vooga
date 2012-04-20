package PhysicsEngine;

import java.awt.geom.Point2D;

import npsprite.FighterBody;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;
//import sprite.FighterSprite;
//import sprite.PlatformBlock;
//import sprite.SpriteTemplate;

public class ReactionRebound extends Reaction {

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getClass().equals(PlatformBlock.class)) {
			rebound(ps2);
		} else if (ps2.getClass().equals(PlatformBlock.class)) {
			rebound(ps1);
		}

	}

	private void rebound(SpriteTemplate fighterSprite) {
		Point2D p =  fighterSprite.getMoveBy();
		double x = -p.getX()*5;
		double y = -p.getY()*5;

		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocationIncrement(x, y);
	}

}
