package PhysicsEngine;

import java.awt.event.KeyEvent;

import sprite.FighterSprite;

import com.golden.gamedev.GameObject;

public class MotionJumpUp extends Motion {

	public MotionJumpUp(GameObject game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean isThisMotion(FighterSprite sprite) {
		return myGame.keyPressed((KeyEvent.VK_UP));
	}

	@Override
	public void doThisMotion(FighterSprite sprite, long elapsedTime) {
		double speed = sprite.getSpeed();
		if (getSpriteBottom(sprite) >= boundY)
			sprite.moveY(-getMovementLength(speed, elapsedTime) * 20);

	}

}
