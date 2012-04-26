package PhysicsEngine;

import npsprite.SpriteTemplate;

/**
 * the super class of reaction
 * 
 * @author Donghe
 * 
 */
public abstract class Reaction {

	/**
	 * do the reaction between these two sprites
	 * 
	 * @param spriteOne
	 *            sprite one
	 * @param spriteTwo
	 *            sprite two
	 * @param physicsEngine
	 *            the physics Engine we use to set new location increment
	 */
	public abstract void act(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo, PhysicsEngine physicsEngine);
}
