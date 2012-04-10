package npsprite;

import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;




// for demo game. Sample block that doesn't move/react
public class PlatformBlock extends NonPlayerSprite implements Immobile
{
    //default speed is 0

    public PlatformBlock (BufferedImage b)
    {
        super(b);
        this.mo
    }

    @Override
    public void collisionAction (SpriteTemplate otherGroup)
    {
        //do nothing
    }

    @Override
    public void setNextLocation(Double double1) {
        // TODO Auto-generated method stub
        
    }

}
