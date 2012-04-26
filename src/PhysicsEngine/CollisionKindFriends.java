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
	public boolean isThisKind(SpriteTemplate spriteOne, SpriteTemplate spriteTwo) {
		return belongFighterBody(spriteOne) && belongFighterBody(spriteTwo)
				&& spriteOne.getGroupID() == spriteTwo.getGroupID();
	}

}
