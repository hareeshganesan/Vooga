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
        if (child.getID()!=otherGroupID){
        child.setActive(false);
        }
    }
    @Override
    public void setLocation(double xs,double ys){
        myX=xs; myY=ys;
        child.setLocation(xs,ys);
    }

    /**
     * default spawn point is the coordinates from .setLocation. 
     */
    public void setSpawnPoint(double x, double y){
        myX=x; myY=y;
    }
    
    public void respawn(){
        child.setLocation(myX,myY);
        child.setActive(true);
    }
    
    public void update(long elapsedTime){
        if (!child.isActive()){
            if ((time==null) || (time.action(elapsedTime))){
            respawn();
            }
        }
        if (child.isActive()){ child.update(elapsedTime); }
        
    }

	@Override
	public String getSpriteKind() {
		// TODO Auto-generated method stub
		return "AutomateSprite";
	}
}
