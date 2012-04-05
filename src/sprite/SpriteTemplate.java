package sprite;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.sprite.AdvanceSprite;


// all sprites in fighting game extend this template
public abstract class SpriteTemplate extends AdvanceSprite
{

    public SpriteTemplate ()
    {
        super();
    }


    public SpriteTemplate (BufferedImage[] b)
    {
        super(b);
    }


    //flip horizontally, does this need to be public?
    protected void flipImagesHoriz ()
    {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        BufferedImage[] o = new BufferedImage[this.getImages().length];
        BufferedImage[] b = this.getImages();
        for (int i = 0; i < o.length; i++)
        {
            tx.translate(-b[i].getWidth(null), 0);
            AffineTransformOp op =
                new AffineTransformOp(tx,
                                      AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            o[i] = op.filter(b[i], null);
        }
        this.setImages(o);
    }


    public abstract void collisionAction (int otherGroupID);


    public abstract void setSpeed (double speed);


    // TODO: GET BOUNDS FROM WINDOW SIZE
    protected abstract void confineBounds ();

}
