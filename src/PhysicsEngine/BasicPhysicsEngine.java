package PhysicsEngine;

import java.awt.geom.Point2D;
import sprite.FighterSprite;
import action.MotionAction;


public class BasicPhysicsEngine extends PhysicsEngine
{
//    @Override
//    public void update (FighterSprite sprite, GameObject game, long elapsedTime)
//    {
//        double speed = sprite.getSpeed();
//        if (sprite.getY() + sprite.getHeight() <= game.getHeight()) sprite.move(0,
//                                                                                0.05 * elapsedTime);
//        if (game.keyDown((KeyEvent.VK_DOWN))) sprite.move(0, speed *
//                                                             elapsedTime);
//        if (game.keyPressed((KeyEvent.VK_UP))) sprite.move(0, -speed *
//                                                              elapsedTime * 20);
//        if (game.keyDown((KeyEvent.VK_RIGHT))) sprite.move(speed * elapsedTime,
//                                                           0);
//        if (game.keyDown((KeyEvent.VK_LEFT))) sprite.move(-speed * elapsedTime,
//                                                          0);
//
//    }
    
    public BasicPhysicsEngine(MotionAction motionAaction) {
		super(motionAaction);
		// TODO Auto-generated constructor stub
	}

	public static void process(FighterSprite fs, double x_vector, double y_vector, long elapsed_time){
        double speed = fs.getSpeed()/10;
        double x = speed*elapsed_time*x_vector;
        double y = speed*elapsed_time*y_vector;
        fs.setNextLocation(new Point2D.Double(x, y));
    }

	@Override
	public void process(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
