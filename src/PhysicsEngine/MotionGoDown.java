package PhysicsEngine;

import java.awt.event.KeyEvent;
import sprite.FighterSprite;
import com.golden.gamedev.GameObject;

public class MotionGoDown extends Motion {

	public MotionGoDown(GameObject game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean isThisMotion(FighterSprite sprite) {
		return myGame.keyDown((KeyEvent.VK_DOWN));
	}

	@Override
	public void doThisMotion(FighterSprite sprite, long elapsedTime) {
		double speed = sprite.getSpeed();
		if (getSpriteBottom(sprite) + getMovementLength(speed, elapsedTime) <= boundY)
			sprite.moveY(getMovementLength(speed, elapsedTime));
		else
			sprite.moveY(boundY - getSpriteBottom(sprite));
	}

}
