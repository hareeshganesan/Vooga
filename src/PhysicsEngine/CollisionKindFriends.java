package PhysicsEngine;

import java.util.ArrayList;
import npsprite.SpriteTemplate;


/**
 * The Collision between two bodyFighter with the same GroupID
 * 
 * @author Donghe
 */

public class CollisionKindFriends extends CollisionKind
{

    public CollisionKindFriends (ArrayList<Reaction> reactionSteps)
    {
        super(reactionSteps);
    }


    public CollisionKindFriends (Reaction reaction)
    {
        super(reaction);
    }


    public CollisionKindFriends ()
    {}


    @Override
    public boolean isThisKind (SpriteTemplate spriteOne,
                               SpriteTemplate spriteTwo)
    {
        return belongFighterBody(spriteOne) && belongFighterBody(spriteTwo) &&
               spriteOne.getGroupID() == spriteTwo.getGroupID();
    }

}
