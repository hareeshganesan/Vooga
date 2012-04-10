package PhysicsEngine;

import npsprite.FighterSprite;
import npsprite.SpriteTemplate;

/**
 * Not implemented yet. Would like to deal with the collision between two
 * sprites from different group
 * 
 * @author Donghe
 * 
 */
public class CollisionReactionEnemy extends CollisionReaction {

    public CollisionReactionEnemy(SpriteTemplate ps1, SpriteTemplate ps2) {
        super(ps1, ps2);
    }

    public CollisionReactionEnemy() {

    }

    @Override
    public boolean isThisComposition(SpriteTemplate ps1, SpriteTemplate ps2) {
        if (ps1.getSpriteKind().equals(FIGHTER)
                && ps2.getSpriteKind().equals(WEAPON))
            return true;
        if (ps1.getSpriteKind().equals(WEAPON)
                && ps2.getSpriteKind().equals(FIGHTER))
            return true;
        return false;
    }

//    @Override
//    public void doThisReaction() {
//        if (myFighterSpriteOne.getSpriteKind().equals(FIGHTER))
//            ((FighterSprite) myFighterSpriteOne).addHealth(-10);
//        if (myFighterSpriteTwo.getSpriteKind().equals(FIGHTER))
//            ((FighterSprite) myFighterSpriteTwo).addHealth(-100);
//
//    }

    @Override
    public CollisionReaction createCollisionReaction(SpriteTemplate ps1,
            SpriteTemplate ps2) {
        return new CollisionReactionEnemy(ps1, ps2);
    }

}
