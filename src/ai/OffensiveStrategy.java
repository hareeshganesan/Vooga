package ai;

import game.CombatInstance;
import java.util.TreeMap;
import sprite.FighterSprite;
import action.Action;
import action.ActionSeries;
import action.FollowAction;

/**
 * The OffensiveStrategy class creates an ActionSeries where the AI will move toward a
 * specified fighter by following it as the fighter moves for 5 seconds.
 * 
 * @author Hareesh
 */
public class OffensiveStrategy extends Strategy
{
    CombatInstance c;

    @Override
    ActionSeries generateAction (CombatInstance c, FighterSprite f)
    {
        System.out.println("toward");
        TreeMap<Long, Action> offenseLoop = new TreeMap<Long, Action>();
        offenseLoop.put((long) 5000, new FollowAction(f, c.getFighters().get(1)));
        return new ActionSeries(offenseLoop);
    }
}
