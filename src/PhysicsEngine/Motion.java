package PhysicsEngine;

import sprite.FighterSprite;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;

/**
 * 
 * @author Donghe
 * 
 */

public abstract class Motion {

	protected int boundX;
	protected int boundY;
	protected GameObject myGame;

	public Motion(GameObject game, int x, int y) {
		myGame = game;
		boundX = x;
		boundY = y;
	}

	public abstract boolean isThisMotion(FighterSprite sprite);

	public abstract void doThisMotion(FighterSprite sprite, long elapsedTime);

	public double getSpriteBottom(Sprite sprite) {
		return sprite.getY() + sprite.getHeight();
	}

	public double getMovementLength(double speed, long elapsedTime) {
		return speed * elapsedTime;
	}

	public double getSpriteRight(FighterSprite sprite) {
		return sprite.getX() + sprite.getWidth();
	}

	public double getSpriteLeft(FighterSprite sprite) {
		return sprite.getX();
	}

}
