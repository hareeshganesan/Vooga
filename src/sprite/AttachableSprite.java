package sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//wraps a sprite that can be used as a weapon, or as a freestanding npc
//may also be useful for later extensions to power-ups
public class AttachableSprite extends SpriteDecorator {

    FighterSprite fighter;

    public AttachableSprite(NonPlayerSprite component) {
        super(component);
    }

    @Override
    public void collisionAction(int otherGroup) {
        child.collisionAction(otherGroup);
    }

    public void attachToFighter(FighterSprite f) {
        fighter = f;
        fighter.addWeapon((WeaponSprite) child); //TODO: IS THIS A SAFE CAST?
    }
    public void detachFromFighter(FighterSprite f){
        fighter.removeWeapon((WeaponSprite) child);//TODO: IS THIS A SAFE CAST?
        f=null;
    }
}
