package events;


import npsprite.SpriteID;
import npsprite.SpriteTemplate;

public abstract class CollisionEvent{

    public CollisionEvent(){
    }

    public abstract void performAction(SpriteTemplate me, SpriteTemplate o);

    public SpriteID getSpriteID() {
        // TODO Auto-generated method stub
        return null;
    }

}
