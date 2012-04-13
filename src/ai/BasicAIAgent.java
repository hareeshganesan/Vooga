package ai;

import game.CombatInstance;
import java.util.List;
import sprite.FighterSprite;
import sprite.HealthDisplay;
import action.FollowAction;
import action.MotionAction;


/**
 * The BasicAIAgent simply creates an AI Agent that follows around a user given
 * that users location/
 * 
 * @author Hareesh
 */
public class BasicAIAgent extends AIAgent
{

    public BasicAIAgent (String name,
                         HealthDisplay display,
                         int groupID,
                         CombatInstance c)
    {
        super(name, display, groupID, c);
    }


    public void update (long elapsedTime)
    {
        List<FighterSprite> fs = myLevel.getFighters();
        if (getCurrentLocation().distance(fs.get(0).getCurrentLocation()) > 40)
        {
            FollowAction follow = new FollowAction(this, fs.get(0));
            follow.performAction(elapsedTime);
        }
        super.update(elapsedTime);
    }

}
