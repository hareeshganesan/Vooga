package ai;

import game.CombatInstance;
import sprite.HealthDisplay;


public class BasicAIAgent extends AIAgent
{

    public BasicAIAgent (String name,
                         HealthDisplay display,
                         int groupID,
                         CombatInstance c)
    {
        super(name, display, groupID, c);
    }

    @Override
    public void update (long elapsedTime)
    {
        
        
    }

}
