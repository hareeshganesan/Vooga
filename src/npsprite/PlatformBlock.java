package npsprite;

import java.awt.image.BufferedImage;

// for demo game. Sample block that doesn't move/react
public class PlatformBlock extends SpriteTemplate{
    //default speed is 0

    public PlatformBlock (BufferedImage b)
    {
        super(b);
        myID=new SpriteID(SpriteID.GroupID.PLATFORM, false, false, false, false);
    }

}
