package ai;

import game.CombatInstance;
import java.util.List;
import npsprite.FighterBody;
import npsprite.LimbSprite;
import sprite.HealthDisplay;
import action.FollowAction;

/**
 * The BasicAIAgent simply creates an AI Agent that follows around a user given
 * that users location/
 * 
 * @author Hareesh
 */
@SuppressWarnings("serial")
public class BasicAIAgent extends AIAgent {

	public BasicAIAgent(String name, LimbSprite root, HealthDisplay display,
			int groupID, CombatInstance c) {
		super(name, root, display, groupID, c);
	}

	public void calculateLocation(long elapsedTime) {
		List<FighterBody> fs = myLevel.getFighters();
		if (getCurrentLocation().distance(fs.get(0).getCurrentLocation()) > 40) {
			FollowAction follow = new FollowAction(this, fs.get(0),
					myPhysicsEngine);
			follow.performAction(elapsedTime);
		}
	}

}
