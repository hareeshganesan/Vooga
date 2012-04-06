package PhysicsEngine;

import sprite.FighterSprite;

/**
 * Not implemented yet.
 * Would like to deal with the collision between two sprites from different group
 * 
 * @author Donghe
 *
 */
public class CollisionReactionEnemy extends CollisionReaction{

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
