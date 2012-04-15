package PhysicsEngine;

import java.util.ArrayList;

import sprite.SpriteTemplate;

/**
 * Not implemented yet. Would like to deal with the collision between two
 * sprites from different group
 * 
 * @author Donghe
 * 
 */
public class CollisionKindEnemy extends CollisionKind {

	public CollisionKindEnemy(ArrayList<Reaction> reactionSteps) {
		super(reactionSteps);
	}

	@Override
	public boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getSpriteKind().equals(FIGHTER)
				&& ps2.getSpriteKind().equals(WEAPON))
			return true;
		if (ps1.getSpriteKind().equals(WEAPON)
				&& ps2.getSpriteKind().equals(FIGHTER))
			return true;
		return false;
	}
}
