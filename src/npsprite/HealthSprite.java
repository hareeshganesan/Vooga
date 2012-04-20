package npsprite;

import java.awt.image.BufferedImage;
import npsprite.SpriteID.GroupID;


public class HealthSprite extends SpriteTemplate implements Damage
{

    private double myDamage = 5; //default


    public HealthSprite (BufferedImage image, GroupID g)
    {
        super(image, g);
    }


    @Override
    public void setDamage (double d)
    {
        myDamage = d;
    }


    @Override
    public double getDamage ()
    {
        return myDamage;
    }


    @Override
    protected void createSpriteID (GroupID g)
    {
        myID = new SpriteID(g, false, damages, false, false);
    }

}
