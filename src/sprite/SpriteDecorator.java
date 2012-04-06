package sprite;

/**
 * Created 4 mar
 * @author Wendy
 * Quite a bit of overhead, may have to abandon the strict pattern decorator
 * maybe visitor?
 */
public abstract class SpriteDecorator extends SpriteTemplate{
    protected NonPlayerSprite child;
    public SpriteDecorator(NonPlayerSprite component){
        super();
        child=component;
    }
    
    //TODO: confineBounds may be different after physics engine...we'll see
    @Override
    protected void confineBounds() {
        child.confineBounds();
    }

    @Override
    public void setDefaultSpeed(double speed) {
        child.setDefaultSpeed(speed);
    }
    //the id of the sprite decorator shouldn't matter
    public void setID(int groupID){
        child.setID(groupID);
    }
    public int getID(){
        return child.getID();
    }

}
