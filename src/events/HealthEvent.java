package events;

import npsprite.Damage;
import npsprite.Health;
import npsprite.SpriteTemplate;

/**
 * This event involves removing/adding health according to other sprite's "damage" field, if it has one
 * @author Wendy
 *
 */
public class HealthEvent extends CollisionEvent{

    //we know that I have a health because this is called inside me
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o){
        if (o.doesDamage()){
            ((Health) me).addHealth(((Damage) o).getDamage());
        }
    }
}
