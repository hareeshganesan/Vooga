package PhysicsEngine;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

/**
 * This class deal with the collision between one sprite and a block or
 * something that is neutral
 * 
 * @author Donghe
 * 
 */
public class CollisionReactionNeutral extends CollisionReaction {

	public CollisionReactionNeutral(SpriteTemplate ps1, SpriteTemplate ps2) {
		super(ps1, ps2);
	}
	
	public CollisionReactionNeutral() {

	}

	@Override
	public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getSpriteKind().equals(FIGHTER)
				&& ps2.getSpriteKind().equals(BLOCK))
			return true;
		if (ps1.getSpriteKind().equals(BLOCK)
				&& ps2.getSpriteKind().equals(FIGHTER))
			return true;
		return false;
	}

	@Override
	public void doThisReaction() {
		if(myFighterSpriteOne.getSpriteKind().equals(BLOCK)) stop(myFighterSpriteOne);
		if(myFighterSpriteTwo.getSpriteKind().equals(BLOCK)) stop(myFighterSpriteTwo);

	}

	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		return new CollisionReactionNeutral(ps1, ps2);
	}

}
