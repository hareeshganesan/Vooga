package events;

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
        if (o.hasProperty(DamageProperty.getName())) {
            ((HealthProperty) me.getProperty(HealthProperty.getName())) // TODO:
                                                                        // THIS
                                                                        // IS
                                                                        // RIDICULOUS,
                                                                        // REFACTOR,
                                                                        // maybe
                                                                        // double
                                                                        // dispatch?
                    .addHealth(((DamageProperty) o.getProperty(DamageProperty
                            .getName())).getDamage());
        }
    }
}
