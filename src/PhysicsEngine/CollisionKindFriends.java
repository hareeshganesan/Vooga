package PhysicsEngine;

import java.util.ArrayList;

import sprite.SpriteTemplate;

/**
 * This class deals with the collision between two sprites from the same group
 * 
 * @author Donghe
 * 
 */
public class CollisionKindFriends extends CollisionKind {

public CollisionKindFriends(ArrayList<Reaction> reactionSteps) {
		super(reactionSteps);
	}

	@Override
	public boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2) {
		if ((ps1.getSpriteKind().equals(FIGHTER) || ps1.getSpriteKind().contains("AI"))
				&& (ps2.getSpriteKind().equals(FIGHTER) || ps2.getSpriteKind().contains("AI")))
			return true;
		return false;
	}
	

}
