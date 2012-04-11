package action;

import java.util.Map.Entry;
import java.util.TreeMap;

public class ActionSeries 
{

    TreeMap<Long, Action> actionMap; 
    long time;
    
    public ActionSeries(TreeMap<Long, Action> map){
        actionMap = map;
    }
    
    public ActionSeries(){
        actionMap = new TreeMap<Long, Action>();
    }
    
    public void performAction(long elapsedTime){
        time+=elapsedTime;
        Entry<Long, Action> action = actionMap.floorEntry(time);
        action.getValue().performAction(elapsedTime);
    }
}
