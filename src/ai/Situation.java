package ai;

import java.util.TreeMap;

public abstract class Situation
{
    protected TreeMap<Double, Strategy> strategies = new TreeMap<Double, Strategy>();

    public Situation(){
        strategies = new TreeMap<Double,Strategy>();
    }
    
    public abstract boolean isOccurring();

    public Strategy selectRandomStrategy ()
    {
        double random = Math.random();
        return strategies.ceilingEntry(random).getValue();
    }
}
