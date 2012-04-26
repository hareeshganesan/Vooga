package npsprite.properties;

import events.CollisionEvent;

public abstract class PropertyObject implements Cloneable{

    public static final String NAME=" ";
    public abstract PropertyObject clone();

    public abstract double getValue();
    
    /**
     * Property-caused events (versus collision-caused events)
     * Default is null
     */
    public CollisionEvent update(long elapsedTime){
        return null;
    }
    
}
