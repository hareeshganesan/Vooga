package ai;

import npsprite.FighterSprite;
import game.CombatInstance;
import sprite.HealthDisplay;


public abstract class AIAgent extends FighterSprite
{

    CombatInstance myLevel;
    
    public AIAgent (String name, HealthDisplay display, int groupID, CombatInstance c)
    {
        super(name, display, groupID);
        myLevel = c;
    }
    
    public void update(long elapsedTime){
        super.update(elapsedTime);
    }
    
    

    

}
