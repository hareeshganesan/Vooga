package npsprite;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class PlatformBlock extends SpriteTemplate
{

    public PlatformBlock (BufferedImage b,GroupID g)
    {
        super(b, g);
    }
    
    public void update(long elapsedTime){
        //don't bother, it doesn't move
    	myCollisionStatus.setDefault();
    }

}
