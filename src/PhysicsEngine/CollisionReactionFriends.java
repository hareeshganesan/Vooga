package PhysicsEngine;

import sprite.FighterSprite;


/**
 * This class deals with the collision between two sprites from the same group
 * 
 * @author Donghe
 *
 */
public class CollisionReactionFriends extends CollisionReaction {

	public CollisionReactionFriends(FighterSprite ps1, FighterSprite ps2) {
		super(ps1, ps2);
	}

	public CollisionReactionFriends() {
		
	}

	@Override
	public void doThisReaction() {
		stop(myFighterSpriteOne);
		stop(myFighterSpriteTwo);		
	}

	@Override
	public boolean isThisComposition(FighterSprite ps1, FighterSprite ps2) {
		return true;
	}

	@Override
	public CollisionReaction createCollisionReaction(FighterSprite ps1,
			FighterSprite ps2) {
		return new CollisionReactionFriends(ps1,ps2);
	}
	
	

}
