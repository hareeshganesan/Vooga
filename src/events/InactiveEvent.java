package events;

import npsprite.SpriteTemplate;


public class InactiveEvent extends CollisionEvent
{
    @Override
    public void performAction (SpriteTemplate me, SpriteTemplate o)
    {
        me.setActive(false);
    }

}
