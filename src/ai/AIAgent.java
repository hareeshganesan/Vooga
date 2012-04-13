package ai;

import game.CombatInstance;
import sprite.FighterSprite;
import sprite.HealthDisplay;


/**
 * The AIAgent works identically to the FighterSprite. Extensions to the class
 * should implement the update method to select the actions the AI Agent will
 * perform.
 * 
 * @author Hareesh
 */
public abstract class AIAgent extends FighterSprite
{

    CombatInstance myLevel;


    public AIAgent (String name,
                    HealthDisplay display,
                    int groupID,
                    CombatInstance c)
    {
        super(name, display, groupID);
        myLevel = c;
    }


    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
    }

}
