package PhysicsEngine;

import sprite.SpriteTemplate;

/**
 * Not implemented yet. Would like to deal with the collision between two
 * sprites from different group
 * 
 * @author Donghe
 * 
 */
public class CollisionReactionEnemy extends CollisionReaction {

	public CollisionReactionEnemy(SpriteTemplate ps1, SpriteTemplate ps2) {
		super(ps1, ps2);
	}

	public CollisionReactionEnemy() {

	}

	@Override
	public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getSpriteKind().equals(FIGHTER)
				&& ps2.getSpriteKind().equals(WEAPON))
			return true;
		if (ps1.getSpriteKind().equals(WEAPON)
				&& ps2.getSpriteKind().equals(FIGHTER))
			return true;
		return false;
	}

	@Override
	public void doThisReaction() {
		//do something for damage
	}

	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		return new CollisionReactionEnemy(ps1, ps2);
	}
}
