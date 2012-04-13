package events;

import npsprite.SpriteTemplate;

/**
 * @author Wendy - superclass for collision events, will need to refactor later 
 * to reduce memory consumption
 */
public abstract class CollisionEvent{

    public CollisionEvent(){
    }

    public abstract void performAction(SpriteTemplate me, SpriteTemplate o);

}
