package events;

import npsprite.GroupID;
import npsprite.LimbSprite;
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
            if (me.hasProperty(HealthProperty.NAME)){
                double damage=o.getProperty(DamageProperty.NAME)
                    .getValue();
                if (GroupID.isFighter(me.getGroupID())){
                    if (GroupID.isFighter(o.getGroupID())){
                        //TODO - check if facing each other, punching/blocking, etc
                    }
                    damage=damage*((LimbSprite) me).getMyPointer().getHealthMultiplier();
                }
                ((HealthProperty) me.getProperty(HealthProperty.NAME))
                    .addHealth(damage);
            }
        }
    }
}
