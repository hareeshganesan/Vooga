package sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//wraps a sprite (w/ no children) that can be used as a weapon, or as a freestanding npc
//may also be useful for later extensions to power-ups
public class AttachableSprite extends SpriteDecorator {

    FighterSprite fighter;
    private int oldID;

    public AttachableSprite(NonPlayerSprite component) {
        super(component);
    }

    @Override
    public void collisionAction(int otherGroup) {
        child.collisionAction(otherGroup);
    }

    public void attachToFighter(FighterSprite f) {
        fighter = f;
        oldID=child.getID();
        fighter.addWeapon(child);
    }
    public void detachFromFighter(FighterSprite f){
        fighter.removeWeapon(child);//TODO
        f=null;
        child.setID(oldID);
    }
}
