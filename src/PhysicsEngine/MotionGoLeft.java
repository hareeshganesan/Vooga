package PhysicsEngine;

import java.awt.event.KeyEvent;
import sprite.FighterSprite;
import com.golden.gamedev.GameObject;

public class MotionGoLeft extends Motion {

	public MotionGoLeft(GameObject game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean isThisMotion(FighterSprite sprite) {
		return myGame.keyDown((KeyEvent.VK_LEFT));
	}

	@Override
	public void doThisMotion(FighterSprite sprite, long elapsedTime) {
		double speed = sprite.getSpeed();
		if (getSpriteLeft(sprite) - getMovementLength(speed, elapsedTime) >= 0)
			sprite.moveX(-getMovementLength(speed, elapsedTime));
		else
			sprite.moveX(-getSpriteLeft(sprite));

	}

}
