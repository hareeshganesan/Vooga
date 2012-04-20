package PhysicsEngine;

import java.util.ArrayList;
import sprite.FighterSprite;
import sprite.PlatformBlock;
import sprite.SpriteTemplate;
import sprite.WeaponSprite;

/**
 * this is the super class of collision reaction it includes the methods of
 * finding the specific reaction, creating reaction and doing the reaction also
 * it has some basic step of reaction, such as stop and go
 * 
 * @author Donghe
 * 
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

	public abstract boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2);

	public void doThisReaction(SpriteTemplate ps1, SpriteTemplate ps2) {
		// Wendy can untoggle these two lines if your code is ready
		// ps1.collisionAction(ps2);
		// ps2.collisionAction(ps1);
		for (Reaction reaction : myReactions) {
			reaction.act(ps1, ps2);
		}
	}

	public void addReaction(Reaction reaction) {
		myReactions.add(reaction);
	}

	public void removeReaction(Reaction reaction) {
		myReactions.remove(reaction);
	}
	
	public void setReaction(Reaction reaction){
		myReactions = new ArrayList<Reaction>();
		myReactions.add(reaction);
	}
	
	public void setReaction(ArrayList<Reaction> reactions){
		myReactions = reactions;
	}
	
	protected boolean isPlatformBlock(SpriteTemplate s){
		return s.getClass().equals(PlatformBlock.class);
	}
	
	protected boolean belongFighterSprite(SpriteTemplate s){
		return FighterSprite.class.isAssignableFrom(s.getClass());
	}
	
	protected boolean isWeaponSprite(SpriteTemplate s){
		return s.getClass().equals(WeaponSprite.class);
	}
	
}
