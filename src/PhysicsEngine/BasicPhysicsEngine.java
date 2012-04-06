package PhysicsEngine;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import sprite.FighterSprite;


import java.awt.event.KeyEvent;

import com.golden.gamedev.GameObject;

public class BasicPhysicsEngine extends PhysicsEngine
{
    public BasicPhysicsEngine(GameObject myGame) {
        super(myGame);
    }

    @Override
    public void update (FighterSprite sprite, long elapsedTime)
    {
        double speed = sprite.getSpeed();
        if (sprite.getY() + sprite.getHeight() <= myGame.getHeight()) sprite.move(0,
                                                                                0.05 * elapsedTime);
        if (myGame.keyDown((KeyEvent.VK_DOWN))) sprite.move(0, speed *
                                                             elapsedTime);
        if (myGame.keyPressed((KeyEvent.VK_UP))) sprite.move(0, -speed *
                                                              elapsedTime * 20);
        if (myGame.keyDown((KeyEvent.VK_RIGHT))) sprite.move(speed * elapsedTime,
                                                           0);
        if (myGame.keyDown((KeyEvent.VK_LEFT))) sprite.move(-speed * elapsedTime,
                                                          0);

    }
    
    public static void process(FighterSprite fs, double x_vector, double y_vector, long elapsed_time){
        double speed = fs.getSpeed()/10;
        double x = speed*elapsed_time*x_vector;
        double y = speed*elapsed_time*y_vector;
        fs.setNextLocation(new Point2D.Double(x, y));
    }

}
