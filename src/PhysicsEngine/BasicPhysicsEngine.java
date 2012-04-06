package PhysicsEngine;

import java.awt.event.KeyEvent;

import sprite.FighterSprite;

import com.golden.gamedev.GameObject;


public class BasicPhysicsEngine extends PhysicsEngine
{
    @Override
    public void update (FighterSprite sprite, GameObject game, long elapsedTime)
    {
        double speed = sprite.getSpeed();
        if (sprite.getY() + sprite.getHeight() <= game.getHeight()) sprite.move(0,
                                                                                0.05 * elapsedTime);
        if (game.keyDown((KeyEvent.VK_DOWN))) sprite.move(0, speed *
                                                             elapsedTime);
        if (game.keyPressed((KeyEvent.VK_UP))) sprite.move(0, -speed *
                                                              elapsedTime * 20);
        if (game.keyDown((KeyEvent.VK_RIGHT))) sprite.move(speed * elapsedTime,
                                                           0);
        if (game.keyDown((KeyEvent.VK_LEFT))) sprite.move(-speed * elapsedTime,
                                                          0);

    }

}
