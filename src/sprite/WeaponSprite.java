package sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class WeaponSprite extends NonPlayerSprite
{

    FighterSprite fighter;
    double fighterX;
    double fighterY;
    BufferedImage[] image;


    public WeaponSprite (BufferedImage[] image, FighterSprite fighter)
    {
        super(image);
        fighter.addWeapon(this);

        //are these necessary?
        this.image = image;
        this.fighterX = fighter.getX();
        this.fighterY = fighter.getY();
        //

        setLocation(fighterX + this.getWidth(), fighterY + this.getHeight());
    }


    @Override
    protected void animationChanged (int oldStat,
                                     int oldDir,
                                     int status,
                                     int direction)
    {
        if ((direction == SpriteValues.LEFT) ||
            (direction == SpriteValues.RIGHT))
        {
            flipImagesHoriz();
        }
    }


    public void updateLocation (double dx, double dy)
    {
        this.move(dx, dy);
        this.fighterX += dx;
        this.fighterY += dy;
    }


    @Override
    public void collisionAction (int otherGroup)
    {

    }


    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
    }


    public void render (Graphics2D pen)
    {
        super.render(pen);
    }


    @Override
    public void setSpeed (double speed)
    {
        //TODO speed is whatever the parent makes it
    }


	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getSpriteKind() {
		// TODO Auto-generated method stub
		return "WeaponSprite";
	}
}
