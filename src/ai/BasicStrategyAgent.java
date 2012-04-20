package ai;

import game.CombatInstance;
import java.util.TreeMap;
import sprite.HealthDisplay;
import action.Action;


/**
 * This class allows the user to define an AI that acts based on strategies with
 * weights. The weights are defined in the instantiation of the method using the
 * addKey method. Weights must be mapped to an ActionMap. The Strategy agent
 * simply selects its next ActionSeries of moves by using weighting with a
 * random number to select a new strategy and obtain the ActionSeries from it.
 * 
 * @author Hareesh
 */
@SuppressWarnings("serial")
public class BasicStrategyAgent extends AIAgent
{

    Action currentAction;
    Strategy strat;
    TreeMap<Double, Strategy> strategies = new TreeMap<Double, Strategy>();


    public BasicStrategyAgent (String name,
                               HealthDisplay display,
                               int groupID,
                               CombatInstance c)
    {
        super(name, display, groupID, c);

    }


    public void calculateLocation (long elapsedTime)
    {
        if (currentAction == null || currentAction.isDone(elapsedTime))
        {
            currentAction = getAction();
        }
        currentAction.performAction(elapsedTime);
    }


    protected Strategy selectRandomStrategy ()
    {
        double random = Math.random();
        return strategies.ceilingEntry(random).getValue();
    }


    private Action getAction ()
    {
        if (strat == null || strat.isComplete())
        {
            strat = selectRandomStrategy();
            strat.initializeGoals();
            System.out.println(strat.getClass().getName());
        }
        return strat.generateAction(myLevel, this);

    }


    /**
     * Adds strategy with weight in. Weight should be less than 1 and the
     * weights must encompass the range from 0 to 1.
     * 
     * @param weight
     * @param s
     */
    public void addStrategy (double weight, Strategy s)
    {
        strategies.put(weight, s);
    }

}
