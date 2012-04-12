package sprite;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.sprite.AdvanceSprite;


// all sprites in fighting game extend this template
public abstract class SpriteTemplate extends AdvanceSprite
{
    // defaults
    double DEFAULT_SPEED = 0;
    double DEFAULT_DAMAGE = 0;
    
    private double myDamage;

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

    public void setDefaultSpeed (double speed){
        DEFAULT_SPEED=speed;
    }
    /**
     * speed reset to default value, same for x and y directions for now
     */
    public void resetSpeed(){
        this.setSpeed(DEFAULT_SPEED, DEFAULT_SPEED);
    }

    
    public void setDamage (double d)
    {
        myDamage = d;
    }
    public double getDamage ()
    {
        return myDamage;
    }

    // TODO: GET BOUNDS FROM WINDOW SIZE
    protected abstract Point2D confineBounds (double dx, double dy);

	public void setNextLocationIncrement(Point2D nextLocation) {
		// TODO Auto-generated method stub
		
	}
	
	public double getSpeed(){
		return 0;
	}

	public abstract String getSpriteKind();   
}
