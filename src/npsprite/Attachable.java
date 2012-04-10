package npsprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


//IMPLEMENT THIS TO ATTACH/DETACH OTHER SPRITES TO YOUR MOVEMENT
//TODO: WRITE MOVE FXN?
//TODO: MERGE IN WITH LIMB NODES
public interface Attachable{

//    public void attachToParent(SpriteTemplate f);
//    public void detachFromParent();

    public void addChild(SpriteTemplate child);
    public void removeChild(SpriteTemplate child);
}
