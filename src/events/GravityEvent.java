package events;

import java.awt.Point;

import npsprite.SpriteTemplate;

public class GravityEvent extends CollisionEvent{
    private double gravity=-0.3;
    
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        if (o.getCenterY()>me.getY()){
        me.setNextLocationIncrement(new Point.Double(0,me.getY()+gravity));
        }
    }

}
