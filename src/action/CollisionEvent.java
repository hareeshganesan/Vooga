package action;

import java.util.ArrayList;

import npsprite.SpriteTemplate;

import sprite.SpriteValues.Id;

//TODO BUILD MORE LATER
//figure out what this performAction is all about - maybe just build collision events that wrap actions
public abstract class CollisionEvent{

    SpriteTemplate myFighter;
    
    public CollisionEvent(SpriteTemplate fighter){
        myFighter=fighter;
    }
    public Id getSpriteID() {
        return myFighter.getSpriteID();
    }

    public abstract void performActionBy(SpriteTemplate o);

}
