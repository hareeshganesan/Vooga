package sprite;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Timer;

/**
 * created 4 mar
 * @author Wendy
 */
//wraps a npc to allow for disappearing/reappearing after set period of time
public class AutomateSprite extends SpriteDecorator{
    private double myX;
    private double myY;
    private Timer time;
    public AutomateSprite(NonPlayerSprite component) {
        super(component);
    }
    
    public void setRegenTime(int millisec){
        time=new Timer(millisec);
    }

    @Override
    public void collisionAction(int otherGroupID) {
        child.setActive(false);
    }
    @Override
    public void setLocation(double xs,double ys){
        myX=xs; myY=ys;
        child.setLocation(xs,ys);
    }

    /**
     * default spawn point is the original location from .setLocation
     */
    public void setSpawnPoint(double x, double y){
        myX=x; myY=y;
    }
    
    private void respawn(){
        child.setLocation(myX,myY);
        child.setActive(true);
    }
    
    //TODO: will need to rewrite sprite group to allow for auto checking during updates (but no rendering)
    public void update(long elapsedTime){
        if (!child.isActive() && time.action(elapsedTime)){
            respawn();
        }
        if (child.isActive()){ child.update(elapsedTime); }
        
    }
    @Override
    protected void confineBounds() {
        child.confineBounds();
    }

    @Override
    public void setSpeed(double speed) {
        child.setSpeed(speed);
    }

}
