package PhysicsEngine;

import java.util.ArrayList;
import npsprite.GroupID;
import npsprite.SpriteTemplate;

/**
 * this is a super class for all kinds of collision every collision kind has one
 * or more reactions associated with it
 * 
 * @author Donghe
 */
public abstract class CollisionKind {

	protected ArrayList<Reaction> myReactions = new ArrayList<Reaction>();

	public CollisionKind(ArrayList<Reaction> reactions) {
		myReactions = reactions;
	}

	public CollisionKind(Reaction reaction) {
		myReactions.add(reaction);
	}

	public CollisionKind() {
	}

	/**
	 * check whether these collided two sprites belong to this kind
	 * 
	 * @param spriteOne
	 *            sprite one
	 * @param spriteTwo
	 *            sprite two
	 * @return whether these collided two sprites belong to this kind
	 */
	public abstract boolean isThisKind(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo);

	/**
	 * do the reaction one by one from its list on these two sprites
	 * 
	 * @param spriteOne
	 *            sprite one
	 * @param spriteTwo
	 *            sprite two
	 * @param physicsEngine
	 *            the physics engine we need to set new location increment
	 */
	public void doThisReaction(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo, PhysicsEngine physicsEngine) {
	    spriteOne.collisionAction(spriteTwo);
		spriteTwo.collisionAction(spriteOne);
		for (Reaction reaction : myReactions) {
			reaction.act(spriteOne, spriteTwo, physicsEngine);
		}
	}

	/**
	 * add a new reaction for this collision kind
	 * 
	 * @param reaction
	 *            the new reaction
	 */
	public void addReaction(Reaction reaction) {
		myReactions.add(reaction);
	}

	/**
	 * remove the reaction from this collision kind
	 * 
	 * @param reaction
	 *            the reaction that needs to be removed
	 */
	public void removeReaction(Reaction reaction) {
		myReactions.remove(reaction);
	}

	/**
	 * set the reaction for this kind of collision
	 * 
	 * @param reaction
	 *            the reaction needs to be set
	 */
	public void setReaction(Reaction reaction) {
		myReactions = new ArrayList<Reaction>();
		myReactions.add(reaction);
	}

	/**
	 * set the reaction list for this kind of collision
	 * 
	 * @param reactions
	 *            the reaction list that need to be set
	 */
	public void setReaction(ArrayList<Reaction> reactions) {
		myReactions = reactions;
	}

	/**
	 * check whether this sprite is a platform block
	 */
	protected boolean isPlatformBlock(SpriteTemplate sprite) {
	    return GroupID.isPlatform(sprite.getGroupID());
	}
	protected boolean isOtherSprite(SpriteTemplate sprite){
	    return GroupID.isOther(sprite.getGroupID());
	}

	/**
	 * check whether this sprite is a kind of FighterBody
	 */
	protected boolean belongFighterBody(SpriteTemplate sprite) {
	    return GroupID.isFighter(sprite.getGroupID());
	}
}
