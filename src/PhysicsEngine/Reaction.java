package PhysicsEngine;

import npsprite.SpriteTemplate;

public abstract class Reaction {

	public abstract void act(SpriteTemplate ps1, SpriteTemplate ps2,
			PhysicsEngine physicsEngine);
}
