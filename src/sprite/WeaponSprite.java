package sprite;

import java.awt.image.BufferedImage;


@Deprecated
public class WeaponSprite extends NonPlayerSprite
{

    FighterSprite fighter;
    double fighterX=0;
    double fighterY=0;
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
//        this.fighterX += dx;
//        this.fighterY += dy;
    }


    @Override
    public void collisionAction (int otherGroup)
    {
        //TODO
    }

}
