package ai;

import game.CombatInstance;
import java.util.TreeMap;
import sprite.FighterSprite;
import action.Action;
import action.ActionSeries;
import action.AvoidAction;


public class DefensiveStrategy extends Strategy
{
    CombatInstance c;


    @Override
    ActionSeries generateAction (CombatInstance c, FighterSprite f)
    {
        System.out.println("away");
        TreeMap<Long, Action> defenseLoop = new TreeMap<Long, Action>();
        defenseLoop.put((long) 5000,
                        new AvoidAction(f, c.getFighters().get(1)));

        return new ActionSeries(defenseLoop);
    }
}
