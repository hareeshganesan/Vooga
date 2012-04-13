package PhysicsEngine;

import java.awt.geom.Point2D;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

public class ReactionStepRebound extends ReactionStep {
	private final String BLOCK = "PlatformBlock";
	private double reboundDistance;
	
	public ReactionStepRebound(Double distance){
		reboundDistance=distance;
	}
	
	
	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		if(ps1.getSpriteKind().equals(BLOCK)) rebound(ps2);
		if(ps2.getSpriteKind().equals(BLOCK)) rebound(ps1);
		
	}
	
	private void rebound(SpriteTemplate fighterSprite) {
		double x = 0;
		double y = 0;
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
		myPhysicsEngine.setNextLocation(x, y);
	}

}
