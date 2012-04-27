package npsprite.properties;

import events.CollisionEvent;
import events.SpawnEvent;

import npsprite.SpriteValues;
import npsprite.SpriteValues.STATUS;

/**
 * May not be needed - for blocks, punches, damage multipliers (if someone maybe wants combo moves)
 * @author Wendy
 *
 */
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
//            return new SpawnEvent();
            return SpawnEvent.getInstanceOf();
        }
        return null;
    }

}
