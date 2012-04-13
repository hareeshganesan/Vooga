package PhysicsEngine;

import java.util.ArrayList;
import sprite.SpriteTemplate;

/**
 * This class deals with the collision between two sprites from the same group
 * 
 * @author Donghe
 * 
 */
public class CollisionReactionFriends extends CollisionReaction {
	private ArrayList<ReactionStep> myReactionStepList=new ArrayList<ReactionStep>();

	public CollisionReactionFriends(SpriteTemplate ps1, SpriteTemplate ps2){
		super(ps1,ps2);
		myReactionStepList.add(new ReactionStepPunch());
		myReactionStepList.add(new ReactionStepPush());
	}

	public CollisionReactionFriends() {
		
	}

	@Override
	public void doThisReaction() {
		for(ReactionStep step:myReactionStepList){
			step.act(myFighterSpriteOne,myFighterSpriteTwo);
		}
	}

	@Override
	public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
		if (ps1.getSpriteKind().equals(FIGHTER)
				&& ps2.getSpriteKind().equals(FIGHTER))
			return true;
		return false;
	}

	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		return new CollisionReactionFriends(ps1, ps2);
	}

	
	public void addReactionStep(ReactionStep step){
		myReactionStepList.add(step);
	}
}
