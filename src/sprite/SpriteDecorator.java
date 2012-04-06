package sprite;

/**
 * Created 4 mar
 * @author Wendy
 */
public abstract class SpriteDecorator extends SpriteTemplate{
    protected NonPlayerSprite child;
    public SpriteDecorator(NonPlayerSprite component){
        child=component;
    }
    
    //TODO: confineBounds may be different after physics engine...we'll see
    @Override
    protected void confineBounds() {
        child.confineBounds();
    }

    @Override
    public void setSpeed(double speed) {
        child.setSpeed(speed);
    }

}
