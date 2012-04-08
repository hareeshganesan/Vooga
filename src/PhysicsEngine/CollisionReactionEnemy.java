package PhysicsEngine;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

/**
 * Not implemented yet.
 * Would like to deal with the collision between two sprites from different group
 * 
 * @author Donghe
 *
 */
public class CollisionReactionEnemy extends CollisionReaction{

	@Override
	public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doThisReaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		// TODO Auto-generated method stub
		return null;
	}

}
