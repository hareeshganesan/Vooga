package PhysicsEngine;

import sprite.SpriteTemplate;

/**
 * This class deals with the collision between two sprites from the same group
 * 
 * @author Donghe
 * 
 */
public class CollisionReactionFriends extends CollisionReaction {

	public CollisionReactionFriends(SpriteTemplate ps1, SpriteTemplate ps2) {
		super(ps1, ps2);
	}

	public CollisionReactionFriends() {

	}

	@Override
	public void doThisReaction() {
		rebound(myFighterSpriteTwo);
		rebound(myFighterSpriteOne);
	}

	@Override
	public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getSpriteKind().equals(FIGHTER)
				&& ps2.getSpriteKind().equals(FIGHTER))
			return true;
		return false;
	}

	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		return new CollisionReactionFriends(ps1, ps2);
	}
}
