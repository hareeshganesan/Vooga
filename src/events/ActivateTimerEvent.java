package events;

import npsprite.SpriteTemplate;
import npsprite.properties.TimerProperty;

/**
 * Activates the TimerProperty object
 * Yeah I know it's not the best design ever, but it's getting close to the deadline
 * @author Wendy
 *
 */
public class ActivateTimerEvent extends CollisionEvent{


    private static ActivateTimerEvent he;
    protected ActivateTimerEvent(){}
    
    public static ActivateTimerEvent getInstanceOf(){
        if (he==null){
            he=new ActivateTimerEvent();
        }
        return he;
    }
    
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        if (me.hasProperty(TimerProperty.NAME)){
            ((TimerProperty) me.getProperty(TimerProperty.NAME)).isActive(true);
        }
    }

    public static String getName(){
        return "activetimer";
    }

}
