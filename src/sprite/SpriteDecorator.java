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
}
