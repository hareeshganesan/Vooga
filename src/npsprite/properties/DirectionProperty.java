package npsprite.properties;

/**
 * Mainly for fighter sprites, since we need to know which direction the fighter is facing for attacks
 * @author Wendy
 *
 */
public class DirectionProperty extends PropertyObject{
    final static String name="direction";
    private int direction;
    
    public void setDirection(int dir){
        direction=dir;
    }
    
    public int getDirection(){
        return direction;
    }
    public static String getName(){
        return name;
    }

    @Override
    public PropertyObject clone() {
        return this;
    }
    
}
