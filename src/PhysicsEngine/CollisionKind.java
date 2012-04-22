package PhysicsEngine;

import java.util.ArrayList;
import npsprite.FighterBody;
import npsprite.LimbSprite;
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

	public abstract boolean isThisKind(SpriteTemplate ps1, SpriteTemplate ps2);

	public void doThisReaction(SpriteTemplate ps1, SpriteTemplate ps2) {
		// Wendy can untoggle these two lines if your code is ready
		ps1.collisionAction(ps2);
		ps2.collisionAction(ps1);
		for (Reaction reaction : myReactions) {
			if (belongFighterSprite(ps1)) {
				ps1 = ((LimbSprite) ps1).getMyPointer();
			}
			if (belongFighterSprite(ps2)) {
				ps2 = ((LimbSprite) ps2).getMyPointer();
			}
			reaction.act(ps1, ps2);
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

	protected boolean isPlatformBlock(SpriteTemplate s) {
		return s.getClass().equals(PlatformBlock.class);
	}

	protected boolean belongFighterSprite(SpriteTemplate s) {
		if (LimbSprite.class.isAssignableFrom(s.getClass()))
			if (FighterBody.class.isAssignableFrom(((LimbSprite) s)
					.getMyPointer().getClass()))
				return true;
		return false;
	}

	// /**
	// * @deprecated no longer using weaponsprites distinction
	// */
	// @Deprecated
	// protected boolean isWeaponSprite(SpriteTemplate s){
	// return s.getClass().equals(WeaponSprite.class);
	// }

}
