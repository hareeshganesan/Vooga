package sprite;

import java.awt.image.BufferedImage;


// for demo game. Sample block that doesn't move/react
/**
 * @deprecated hasn't been deprecated yet since this class doesn't do anything, but might as well get used to 
 * npsprite package
 */
@Deprecated
public class PlatformBlock extends NonPlayerSprite
{

    //default speed is 0

    public PlatformBlock (BufferedImage[] b)
    {
        super(b);
    }


    @Override
    public void collisionAction (int otherGroup)
    {
        //do nothing
    }


    public void move (double dx, double dy)
    {
        //do nothing, you can't move these blocks
    }
}
