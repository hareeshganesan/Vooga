package ai;

import game.CombatInstance;
import npsprite.FighterBody;


public class DefaultSituation extends Situation
{

    public DefaultSituation (FighterBody myFighter, CombatInstance c)
    {
        strategies.put(1.0, new OffensiveStrategy(myFighter, c));
        strategies.put(.5, new DefensiveStrategy(myFighter, c));

    }


    public boolean isOccurring ()
    {
        return true;
    }

}
