package ai;

import npsprite.FighterBody;
import npsprite.LimbSprite;
import game.CombatInstance;
//import sprite.FighterSprite;
import sprite.HealthDisplay;



/**
 * The AIAgent works identically to the FighterSprite. Extensions to the class
 * should implement the update method to select the actions the AI Agent will
 * perform.
 * 
 * @author Hareesh
 */
public abstract class AIAgent extends FighterBody
{

    CombatInstance myLevel;


    public AIAgent (String name,LimbSprite root,
                    HealthDisplay display,
                    int groupID,
                    CombatInstance c)
    {
        super(root, name, display);
        myLevel = c;
    }

    public AIAgent (String name,
                    HealthDisplay display,
                    int groupID,
                    CombatInstance c)
    {
        super(null, name, display);
        myLevel = c;
    }

    abstract public void calculateLocation(long elapsedTime);
    
    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
    }
}
