package PhysicsEngine;

import java.awt.event.KeyEvent;
import sprite.FighterSprite;
import com.golden.gamedev.GameObject;

public class MotionGoRight extends Motion {

	public MotionGoRight(GameObject game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean isThisMotion(FighterSprite sprite) {
		return myGame.keyDown((KeyEvent.VK_RIGHT));
	}

	@Override
	public void doThisMotion(FighterSprite sprite, long elapsedTime) {
		double speed = sprite.getSpeed();
		if (getSpriteRight(sprite) + getMovementLength(speed, elapsedTime) <= boundX)
			sprite.moveX(getMovementLength(speed, elapsedTime));
		else
			sprite.moveX(myGame.getWidth() - getSpriteRight(sprite));

	}

}
