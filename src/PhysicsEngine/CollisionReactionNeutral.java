package PhysicsEngine;

import sprite.FighterSprite;

/**
 * This class deal with the collision between one sprite and a block or
 * something that is neutral
 * 
 * @author Donghe
 * 
 */
public class CollisionReactionNeutral extends CollisionReaction {

	@Override
	public boolean isThisComposition(FighterSprite ps1, FighterSprite ps2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doThisReaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public CollisionReaction createCollisionReaction(FighterSprite ps1,
			FighterSprite ps2) {
		// TODO Auto-generated method stub
		return null;
	}

}
