package npsprite;

import java.awt.image.BufferedImage;
import npsprite.properties.DamageProperty;

//demo health power-up
public class HealthSprite extends SpriteTemplate{

    public HealthSprite(BufferedImage image,GroupID g) {
        super(image,g);
        this.addProperty(DamageProperty.getName(), new DamageProperty(5));
    }
}
