package ai;

import game.CombatInstance;
import java.util.TreeMap;
import sprite.HealthDisplay;
import action.ActionSeries;


public class BasicStrategyAgent extends AIAgent
{

    ActionSeries currentAction;
    TreeMap<Double, Strategy> strategies = new TreeMap<Double,Strategy>();
    public BasicStrategyAgent (String name,
                               HealthDisplay display,
                               int groupID,
                               CombatInstance c)
    {
        super(name, display, groupID, c);
        
    }
    
    public void update(long elapsedTime){
        if(currentAction == null || currentAction.isDone(elapsedTime)){
            currentAction = getAction();
        }
        currentAction.performAction(elapsedTime);
        super.update(elapsedTime);
    }
    
    private ActionSeries getAction(){
        double random = Math.random();
        return strategies.ceilingEntry(random).getValue().generateAction(this.myLevel, this);
    }
    
    public void addStrategy(double weight, Strategy s){
        strategies.put(weight, s);
    }

}
