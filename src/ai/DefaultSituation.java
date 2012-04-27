package ai;

import java.util.TreeMap;
import npsprite.FighterBody;
import game.CombatInstance;

public class DefaultSituation extends Situation
{

    public DefaultSituation(FighterBody myFighter, CombatInstance c){
        strategies.put(1.0, new OffensiveStrategy(myFighter, c));
        //strategies.put(.5, new DefensiveStrategy(myFighter, c));

    }
    public boolean isOccurring ()
    {
        return true;
    }

}
