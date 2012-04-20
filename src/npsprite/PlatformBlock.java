package npsprite;

import java.awt.image.BufferedImage;
import npsprite.SpriteID.GroupID;

public class PlatformBlock extends SpriteTemplate{

    public PlatformBlock (BufferedImage b)
    {
        super(b,null);
    }
    
    public void update(long elapsedTime){
        //don't bother, it doesn't move
    }

}
