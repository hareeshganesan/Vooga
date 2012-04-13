package npsprite;

import java.awt.image.BufferedImage;

import npsprite.SpriteID.GroupID;

public class PlatformBlock extends SpriteTemplate{
    //default speed is 0

    public PlatformBlock (BufferedImage b)
    {
        super(b,null);
    }

    @Override
    protected void createSpriteID(GroupID g) { //UM...
        myID=new SpriteID(SpriteID.GroupID.PLATFORM, false, false, false, false);
    }

}
