package PhysicsEngine;

import java.util.ArrayList;

import sprite.SpriteTemplate;
/**
 * This class deal with the collision between one sprite and a block or
 * something that is neutral
 * 
 * @author Donghe
 * 
 */
public class CollisionKindNeutral extends CollisionKind {
	
public CollisionKindNeutral(ArrayList<Reaction> reactionSteps) {
		super(reactionSteps);
	}

	@Override
	public boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2) {
		if(ps1.getSpriteKind().equals(BLOCK) || ps2.getSpriteKind().equals(BLOCK) ){
			return true;
		}
		return false;
	}
	

}
