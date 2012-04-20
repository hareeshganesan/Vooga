package PhysicsEngine;

import java.util.ArrayList;
import sprite.SpriteTemplate;


public class CollisionKindCustom extends CollisionKind
{

    private Class<?> c1;
    private Class<?> c2;
    private boolean and = true;


    public CollisionKindCustom (ArrayList<Reaction> reactions)
    {
        super(reactions);
    }


    public CollisionKindCustom (Reaction reaction)
    {
        super(reaction);
    }


    public CollisionKindCustom ()
    {}


    @Override
    public boolean isThisKind (SpriteTemplate ps1, SpriteTemplate ps2)
    {
        if (and)
        {
            if (ps1.getClass().equals(c1) && ps2.getClass().equals(c2)) return true;
            return false;
        }
        else
        {
            if (ps1.getClass().equals(c1) || ps2.getClass().equals(c2)) return true;
            return false;
        }

    }


    public void setType (Class<?> c1, Class<?> c2)
    {
        this.c1 = c1;
        this.c2 = c2;
    }


    public void setOr ()
    {
        and = false;
    }

}
