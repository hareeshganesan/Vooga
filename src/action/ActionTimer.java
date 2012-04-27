package action;

import com.golden.gamedev.object.Timer;

public class ActionTimer extends Timer
{
    boolean available = true;
    long myDelay;
    long currentTick;
    public ActionTimer (int delay)
    {
        super(delay);
        myDelay = delay;
        currentTick = myDelay;
    }
    
    public boolean action(long elapsedTime) {
        if(available){
            currentTick -= elapsedTime;
            if(currentTick<0){
                available = false;
                return false;
            }
            return true;
        }
        return false;
    }
    
    public void refresh(){
        currentTick = myDelay;
    }
    public boolean isAvailable(){
        return available;
    }
    
    public void makeAvailable(){
        available = true;
        refresh();
    }
}
    

