package npsprite;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import action.Action;
import action.CollisionEvent;
import action.MotionAction;

// for demo game. Sample block that doesn't move/react
public class PlatformBlock extends SpriteTemplate{
    //default speed is 0

    public PlatformBlock (BufferedImage b)
    {
        super(b);
    }
//
//    @Override
//    public MotionAction collisionAction (SpriteTemplate otherGroup)
//    {
//        return null;
//        //do nothing
//    }
//    public void addCollisionEvent(CollisionEvent c){
//        //these blocks don't react
//    }

    //I don't know what this is about
    @Override
    protected Point2D confineBounds(double dx, double dy) {
        // TODO Auto-generated method stub
        return null;
    }


}
