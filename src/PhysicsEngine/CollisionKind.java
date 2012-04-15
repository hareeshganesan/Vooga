package PhysicsEngine;
import java.util.ArrayList;

import sprite.SpriteTemplate;

/**
 * this is the super class of collision reaction it includes the methods of
 * finding the specific reaction, creating reaction and doing the reaction also
 * it has some basic step of reaction, such as stop and go
 * 
 * @author Donghe
 * 
 */
public abstract class CollisionKind {

	protected final String FIGHTER = "FighterSprite";
	protected final String BLOCK = "PlatformBlock";
	protected final String WEAPON = "WeaponSprite";
	
	protected ArrayList<Reaction> myReactions=new ArrayList<Reaction>();
	
	public CollisionKind(ArrayList<Reaction> reactions){
		myReactions=reactions;
	}
	
	public CollisionKind(Reaction reaction){
		myReactions.add(reaction);
	}

	public abstract boolean isThisKind(SpriteTemplate ps1,
			SpriteTemplate ps2);
	
	public void doThisReaction(SpriteTemplate ps1, SpriteTemplate ps2) {
		for(Reaction reaction:myReactions){
			reaction.act(ps1,ps2);
		}
	}
	
	public void addReaction(Reaction reaction){
		myReactions.add(reaction);
	}
	
	public void removeReaction(Reaction reaction){
		myReactions.remove(reaction);
	}

}
