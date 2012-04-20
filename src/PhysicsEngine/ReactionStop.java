package PhysicsEngine;

import sprite.SpriteTemplate;


public class ReactionStop extends Reaction
{

    @Override
    public void act (SpriteTemplate ps1, SpriteTemplate ps2)
    {
        // TODO Auto-generated method stub
        myPhysicsEngine = new FightPhysicsEngine(ps1);
        myPhysicsEngine.setNextLocationIncrement(0, 0);

        myPhysicsEngine = new FightPhysicsEngine(ps2);
        myPhysicsEngine.setNextLocationIncrement(0, 0);

    }

}
