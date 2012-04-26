package PhysicsEngine;

import java.util.ArrayList;
import npsprite.FighterBody;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;

/**
 * this is the super class of collision reaction it includes the methods of
 * finding the specific reaction, creating reaction and doing the reaction also
 * it has some basic step of reaction, such as stop and go
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

	public abstract boolean isThisKind(SpriteTemplate spriteOne, SpriteTemplate spriteTwo);

	public void doThisReaction(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo, PhysicsEngine physicsEngine) {
		spriteOne.collisionAction(spriteTwo);
		spriteTwo.collisionAction(spriteOne);
		for (Reaction reaction : myReactions) {
			reaction.act(spriteOne, spriteTwo, physicsEngine);
		}
	}

	public void addReaction(Reaction reaction) {
		myReactions.add(reaction);
	}

	public void removeReaction(Reaction reaction) {
		myReactions.remove(reaction);
	}

	public void setReaction(Reaction reaction) {
		myReactions = new ArrayList<Reaction>();
		myReactions.add(reaction);
	}

	public void setReaction(ArrayList<Reaction> reactions) {
		myReactions = reactions;
	}

	protected boolean isPlatformBlock(SpriteTemplate sprite) {
		return sprite.getClass().equals(PlatformBlock.class);
	}

	protected boolean belongFighterBody(SpriteTemplate sprite) {
		return FighterBody.class.isAssignableFrom(sprite.getClass());
	}
}
