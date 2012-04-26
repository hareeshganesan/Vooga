package ai;

import game.CombatInstance;
import java.util.ArrayList;
import npsprite.FighterBody;
import PhysicsEngine.PhysicsEngine;
import action.Action;


abstract class Strategy
{

    private int index;
    protected ArrayList<Goal> goals;
    private boolean done;
    protected CombatInstance c;
    protected FighterBody myFighter;
    protected PhysicsEngine myPhysicsEngine; 


    public Strategy (FighterBody ai, CombatInstance ci)
    {
        c = ci;
        myFighter = ai;
        goals = new ArrayList<Goal>();
        myPhysicsEngine = c.getPhysicsEngine();
    }


    public Action generateAction (CombatInstance c, FighterBody f)
    {

        Goal nextGoal = goals.get(index);
        index++;
        if (index == goals.size()) done = true;
        return nextGoal;
    }


    abstract public void initializeGoals ();


    /**
     * Specify success condition for the strategy and move on. Failure in any
     * goal can result in this being set to done.
     * 
     * @return
     */
    public boolean isComplete ()
    {
        return done;
    }
}
