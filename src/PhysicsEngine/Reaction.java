package PhysicsEngine;

import npsprite.SpriteTemplate;

public abstract class Reaction {

	public abstract void act(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo, PhysicsEngine physicsEngine);
}
