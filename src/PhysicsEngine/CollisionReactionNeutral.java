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
public class CollisionReactionNeutral extends CollisionReaction {
	private ArrayList<ReactionStep> myReactionStepList=new ArrayList<ReactionStep>();

	public CollisionReactionNeutral(SpriteTemplate ps1, SpriteTemplate ps2) {
		super(ps1, ps2);
		myReactionStepList.add(new ReactionStepRebound(20.0));
	}
	
	public CollisionReactionNeutral() {

	}

	@Override
	public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
		if(ps1.getSpriteKind().equals(BLOCK) || ps2.getSpriteKind().equals(BLOCK) ) return true;
		return false;
	}
	
	public void doThisReaction() {
		for(ReactionStep step:myReactionStepList){
			step.act(myFighterSpriteOne,myFighterSpriteTwo);
		}
	}
	
	@Override
	public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
			SpriteTemplate ps2) {
		return new CollisionReactionNeutral(ps1, ps2);
	}
	
	public void addReactionStep(ReactionStep step){
		myReactionStepList.add(step);
	}
}
