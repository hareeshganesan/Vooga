package npsprite;

import npsprite.SpriteID.GroupID;

//IMPLEMENT THIS TO ATTACH/DETACH OTHER SPRITES TO YOUR MOVEMENT
/**
 * @deprecated no longer using interfaces
 */
@Deprecated 
public interface Attachable{
    boolean attaches=true;

    public void addChild(NodeSprite child);
    public void removeChild(NodeSprite child);
    /**
     * a sprite that becomes child of another sprite adopts the parent sprite's groupID
     */
    public void changeGroupID(SpriteID.GroupID g);

    /*
     *Override the inherited method from spritetemplate to return the proper temporary groupID
     *Failing to do so won't result in error, but will break the attaching behavior
     *TODO fix it so they're forced to implement?
     */
    public GroupID getGroupID();
}
