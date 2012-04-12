package npsprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//IMPLEMENT THIS TO ATTACH/DETACH OTHER SPRITES TO YOUR MOVEMENT
//MERGED IN WITH LIMB NODES
public interface Attachable{
    boolean attaches=true;

    public void addChild(NodeSprite child);
    public void removeChild(NodeSprite child);
    public void changeGroupID(SpriteID.GroupID g);
}
