package npsprite.properties;

import npsprite.SpriteValues;
import npsprite.SpriteValues.DIR;


/**
 * Mainly for fighter sprites, since we need to know which direction the fighter
 * is facing for attacks
 * 
 * @author Wendy
 */
public class DirectionProperty extends PropertyObject
{
    public static final String NAME = "direction";
    private SpriteValues.DIR direction;


    public DirectionProperty (DIR right)
    {
        direction = right;
    }


    public void setDirection (DIR dir)
    {
        direction = dir;
    }


    public SpriteValues.DIR getDirection ()
    {
        return direction;
    }


    @Override
    public PropertyObject clone ()
    {
        return this;
    }


    @Override
    public double getValue ()
    {
        return 0;
    }
}
