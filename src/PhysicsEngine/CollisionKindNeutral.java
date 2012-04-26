package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;

/**
 * The Collision between some sprite and a block
 * 
 * @author Donghe
 */
public class CollisionKindNeutral extends CollisionKind {

	public CollisionKindNeutral(ArrayList<Reaction> reactionSteps) {
		super(reactionSteps);
	}

	public CollisionKindNeutral(Reaction reaction) {
		super(reaction);
	}

	public CollisionKindNeutral() {
	}

	@Override
	public boolean isThisKind(SpriteTemplate spriteOne, SpriteTemplate spriteTwo) {
		return (isPlatformBlock(spriteOne) || isPlatformBlock(spriteTwo));
	}
}
