package events;

import npsprite.GroupID;
import npsprite.NodeSprite;
import npsprite.SpriteTemplate;
import npsprite.properties.DamageProperty;
import npsprite.properties.HealthProperty;

/**
 * This event involves removing/adding health according to other sprite's
 * "damage" field, if it has one
 * 
 * @author Wendy
 * 
 */
public class HealthEvent extends CollisionEvent {

    // we know that I have a health because this is called inside me
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        if (o.hasProperty(DamageProperty.NAME)) {
            if (me.hasProperty(HealthProperty.NAME)) {
                double damage = o.getProperty(DamageProperty.NAME).getValue();
                ((HealthProperty) me.getProperty(HealthProperty.NAME))
                        .addHealth(damage);
            }
        }
    }
    public static String getName(){
        return "health";
    }
}
