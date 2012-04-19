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
		if (belongFighterSprite(ps1) && isWeaponSprite(ps2))
			return true;
		if (belongFighterSprite(ps2) && isWeaponSprite(ps1))
			return true;
		return false;
	}
}
