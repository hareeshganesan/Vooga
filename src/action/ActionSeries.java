package action;

import java.util.Map.Entry;
import java.util.TreeMap;


public class ActionSeries implements Action
{

    TreeMap<Long, Action> actionMap;
    long time;


    public ActionSeries (TreeMap<Long, Action> map)
    {
        actionMap = map;
    }


    public ActionSeries ()
    {
        actionMap = new TreeMap<Long, Action>();
    }


    public void performAction (long elapsedTime)
    {
        time += elapsedTime;
        Entry<Long, Action> action = actionMap.ceilingEntry(time);
        action.getValue().performAction(elapsedTime);
    }


    public boolean isDone (long elapsedTime)
    {
        if ((time + elapsedTime) > actionMap.lastKey())
        {
            time = 0;
            return true;
        }
        return false;
    }


    public void reset ()
    {
        time = 0;
    }
}
