package PhysicsEngine;

import sprite.FighterSprite;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;

public class MotionFallDown extends Motion {

	public MotionFallDown(GameObject game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean isThisMotion(FighterSprite sprite) {
		return getSpriteBottom(sprite) <= boundY;
	}

	@Override
	public void doThisMotion(FighterSprite sprite, long elapsedTime) {
		sprite.moveY(0.05 * elapsedTime);

	}
}
