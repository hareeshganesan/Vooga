package npsprite.properties;

import java.util.HashMap;

import npsprite.SpriteValues;
import npsprite.SpriteValues.STATUS;

//dual properties - tells you if the sprite is blocking, punching, etc.
//also gives back a double - if sprite is blocking, double should be closer to 0 than 
//if sprite standing, so that health lost from collision*block is < health lost from collision*standing
public class StatusProperty extends PropertyObject{
    public static final String NAME="status";
    private SpriteValues.STATUS status;
    private HashMap<STATUS,Double> multipliers=new HashMap<STATUS,Double>(); //each sprite can set its own multipliers
    
    public StatusProperty(SpriteValues.STATUS stat) {
        status=stat;
        for (SpriteValues.STATUS s: SpriteValues.STATUS.values()){
            multipliers.put(s, 1.0); //default all statuses to 1
        }
    }

    @Override
    public PropertyObject clone() {
        return new StatusProperty(status);
    }

    public void setStatus(STATUS s){
        status=s;
    }
    @Override
    public double getValue() {
        return multipliers.get(status);
    }

    public void setStatusValue(STATUS s, double v) {
        multipliers.put(s, v);
    }

}
