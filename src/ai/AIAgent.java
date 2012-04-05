package ai;

import sprite.FighterSprite;
import sprite.HealthDisplay;


public abstract class AIAgent extends FighterSprite
{

    public AIAgent (String name, HealthDisplay display, int groupID)
    {
        super(name, display, groupID);
    }
    
    abstract public void update(long elapsedTime);
    
    

    

}
