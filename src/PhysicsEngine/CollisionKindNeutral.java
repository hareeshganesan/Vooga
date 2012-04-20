package PhysicsEngine;

import java.util.ArrayList;
//import sprite.SpriteTemplate;

import npsprite.SpriteTemplate;


/**
 * This class deal with the collision between one sprite and a block or
 * something that is neutral
 * 
 * @author Donghe
 */
public class CollisionKindNeutral extends CollisionKind
{

    public CollisionKindNeutral (ArrayList<Reaction> reactionSteps)
    {
        super(reactionSteps);
    }


    public CollisionKindNeutral (Reaction reaction)
    {
        super(reaction);
    }


    public CollisionKindNeutral ()
    {}


    @Override
    public boolean isThisKind (SpriteTemplate ps1, SpriteTemplate ps2)
    {
        if (isPlatformBlock(ps1) || isPlatformBlock(ps2))
        {
            return true;
        }
        return false;
    }
}
