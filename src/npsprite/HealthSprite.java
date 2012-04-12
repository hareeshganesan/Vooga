package npsprite;

import java.awt.image.BufferedImage;

import action.MotionAction;

public class HealthSprite extends PlatformBlock implements PowerUp{

    public HealthSprite(BufferedImage b) {
        super(b);
        super.setMaxHealth(1);
        super.setDamage(5);
    }

}
