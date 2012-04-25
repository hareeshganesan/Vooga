package PhysicsEngine;

import java.util.ArrayList;

import npsprite.SpriteTemplate;

/**
 * This class deals with the collision between two sprites from the same group
 * 
 * @author Donghe
 */

public class CollisionKindFriends extends CollisionKind {

	public CollisionKindFriends(ArrayList<Reaction> reactionSteps) {
		super(reactionSteps);
	}

	public CollisionKindFriends(Reaction reaction) {
		super(reaction);
	}

	public CollisionKindFriends() {
	}

	@Override
	public boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2) {
		return belongFighterSprite(ps1) && belongFighterSprite(ps2)
				&& ps1.getGroupID() == ps2.getGroupID();
	}

}
