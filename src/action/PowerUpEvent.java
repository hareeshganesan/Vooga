package action;

import npsprite.NodeSprite;
import npsprite.SpriteTemplate;
import sprite.SpriteValues.Id;

public class PowerUpEvent extends CollisionEvent{

    public PowerUpEvent(SpriteTemplate fighter) {
        super(fighter);
    }
    
    public void performActionBy(SpriteTemplate o){
        myFighter.addHealth(o.getDamage());
//        o.addHealth(-1);
    }

}
