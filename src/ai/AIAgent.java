package ai;

import game.CombatInstance;
import npsprite.FighterBody;
import npsprite.HealthDisplay;
import npsprite.NodeSprite;
import PhysicsEngine.PhysicsEngine;


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
    protected PhysicsEngine myPhysicsEngine;


    public AIAgent (String name,
                    NodeSprite root,
                    HealthDisplay display,
                    int groupID,
                    CombatInstance c)
    {
        super(root, name, display);
        myLevel = c;
        myPhysicsEngine = myLevel.getPhysicsEngine();
    }


    abstract public void calculateLocation (long elapsedTime);


    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
    }
}
