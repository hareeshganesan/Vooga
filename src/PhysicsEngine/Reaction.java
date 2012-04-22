package PhysicsEngine;

import npsprite.SpriteTemplate;

public abstract class Reaction
{
    protected FightPhysicsEngine myPhysicsEngine;


    public abstract void act (SpriteTemplate ps1, SpriteTemplate ps2);
}
