package PhysicsEngine;

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
		if(ps1.getSpriteKind().equals(BLOCK) || ps2.getSpriteKind().equals(BLOCK) ) return true;
		return false;
	}

	@Override
	public void doThisReaction() {
		if(myFighterSpriteOne.getSpriteKind().equals(BLOCK)) rebound(myFighterSpriteTwo);
		if(myFighterSpriteTwo.getSpriteKind().equals(BLOCK)) rebound(myFighterSpriteOne);

	}

	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		return new CollisionReactionNeutral(ps1, ps2);
	}
}
