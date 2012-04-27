package npsprite.properties;

import com.golden.gamedev.object.Timer;
import events.CollisionEvent;


/*
 * Use for sprites that will do something (saved as a collisionEvent) after a
 * set period of time
 */
public class TimerProperty extends PropertyObject
{
    public static final String NAME = "timer";
    private Timer time;
    private int millisec;
    private boolean isActive;
    private CollisionEvent event; //when timer is up, return this event to the sprite to do


    public TimerProperty (CollisionEvent e, int ms)
    {
        event = e;
        millisec = ms;
        isActive = false;
    }


    @Override
    public PropertyObject clone ()
    {
        return this;
    }


    public void isActive (boolean flag)
    {
        if (isActive != flag)
        {
            isActive = flag;
            if (isActive)
            {
                time = new Timer(millisec);
            }
            else
            {
                time = null;
            }
        }
    }


    @Override
    public double getValue ()
    {
        return millisec;
    }


    public CollisionEvent update (long elapsedTime)
    {
        if (isActive)
        {
            if (time.action(elapsedTime))
            {
                time = null;
                isActive = false;
                return event;
            }
        }
        return null;
    }
}
