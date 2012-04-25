package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;

/**
 * Not implemented yet. Would like to deal with the collision between two
 * sprites from different group
 * 
 * @author Donghe
 */
public class CollisionKindEnemy extends CollisionKind {

	public CollisionKindEnemy(ArrayList<Reaction> reactionSteps) {
		super(reactionSteps);
	}

	public CollisionKindEnemy(Reaction reaction) {
		super(reaction);
	}

	public CollisionKindEnemy() {
	}

	@Override
	public boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2) {
		return belongFighterSprite(ps1) && belongFighterSprite(ps2)
				&& ps1.getGroupID() != ps2.getGroupID();
	}
}
