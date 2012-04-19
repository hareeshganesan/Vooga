package PhysicsEngine;

import java.awt.geom.Point2D;
import sprite.FighterSprite;
import sprite.PlatformBlock;
import sprite.SpriteTemplate;

public class ReactionRebound extends Reaction {
	private double reboundDistance;
	
	public ReactionRebound(Double distance){
		reboundDistance=distance;
	}
	
	
	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		if(ps1.getClass().equals(PlatformBlock.class)) rebound(ps2);
		if(ps2.getClass().equals(PlatformBlock.class)) rebound(ps1);
		
	}
	
	private void rebound(SpriteTemplate fighterSprite) {
		double x = 0;
		double y = 0;
		System.out.println("rebound");
		Point2D p = ((FighterSprite) fighterSprite).getMoveBy();
		if (p.getX() > 0)
			x = -reboundDistance;
		if (p.getX() < 0)
			x = reboundDistance;
		if (p.getY() > 0)
			y = -reboundDistance;
		if (p.getY() < 0)
			y = reboundDistance;

		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocationIncrement(x, y);
	}

}
