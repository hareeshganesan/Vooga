package events;

import npsprite.SpriteTemplate;


public class InactiveEvent extends CollisionEvent
{
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        System.out.println("inactivating: "+me);
        me.setActive(false);
    }

}
