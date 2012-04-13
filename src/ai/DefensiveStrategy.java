package ai;

import game.CombatInstance;
import java.util.TreeMap;
import sprite.FighterSprite;
import action.Action;
import action.ActionSeries;
import action.AvoidAction;


/**
 * The DefensiveStrategy class creates an ActionSeries where the AI will avoid a
 * specified fighter by moving in the opposite direction from that fighter
 * within a certain range for 5 seconds.
 * 
 * @author Hareesh
 */
public class DefensiveStrategy extends Strategy
{
    CombatInstance c;


    @Override
    ActionSeries generateAction (CombatInstance c, FighterSprite f)
    {
        System.out.println("away");
        TreeMap<Long, Action> defenseLoop = new TreeMap<Long, Action>();
        defenseLoop.put((long) 5000, new AvoidAction(f, c.getFighters().get(1)));

        return new ActionSeries(defenseLoop);
    }
}
