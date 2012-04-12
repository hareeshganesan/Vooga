package npsprite;

import java.awt.image.BufferedImage;

public class HealthSprite extends SpriteTemplate implements Damage{

    private double myDamage=5; //default
    public HealthSprite(BufferedImage image) {
        super(image);
    }

    @Override
    public void setDamage(double d) {
        myDamage=d;
    }

    @Override
    public double getDamage() {
        return myDamage;
    }

}
