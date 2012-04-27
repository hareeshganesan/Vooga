package npsprite.properties;

import java.util.HashMap;

import events.CollisionEvent;
import events.SpawnEvent;

import npsprite.SpriteValues;
import npsprite.SpriteValues.STATUS;

public class StatusProperty extends PropertyObject{
    public static final String NAME="status";
    private SpriteValues.STATUS status;
     
    public StatusProperty(SpriteValues.STATUS stat) {
        status=stat;
    }

    @Override
    public PropertyObject clone() {
        return new StatusProperty(status);
    }

    public void setStatus(STATUS s){
        status=s;
    }
    public STATUS getStatus(){
        return status;
    }
    @Override
    public double getValue() {
        return 0;
    }
    
    public CollisionEvent update(long elapsedTime){
        if (status==STATUS.BLOCK){
            return new SpawnEvent();
        }
        return null;
    }

}
