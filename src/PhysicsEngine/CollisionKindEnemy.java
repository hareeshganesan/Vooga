package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;


/**
 * The Collision between two bodyFighter with different GroupID
 * 
 * @author Donghe
 */
public class CollisionKindEnemy extends CollisionKind
{

    public CollisionKindEnemy (ArrayList<Reaction> reactionSteps)
    {
        super(reactionSteps);
    }


    public CollisionKindEnemy (Reaction reaction)
    {
        super(reaction);
    }


    public CollisionKindEnemy ()
    {}


    @Override
    public boolean isThisKind (SpriteTemplate spriteOne,
                               SpriteTemplate spriteTwo)
    {
        return belongFighterBody(spriteOne) && belongFighterBody(spriteTwo) &&
               spriteOne.getGroupID() != spriteTwo.getGroupID();
    }
}
