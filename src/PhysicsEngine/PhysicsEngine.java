package PhysicsEngine;

import com.golden.gamedev.GameEngine;

import npsprite.SpriteTemplate;
import action.MotionAction;

/**
 * This is the super class of physics engine Maybe there will more children
 * engine in the futures
 * 
 * @author Donghe
 */
public abstract class PhysicsEngine {
	protected final int BASE_POINT = 0;
	protected int BOUND_X;
	protected int BOUND_Y;

	private GameEngine myGameEngine;

	public PhysicsEngine(GameEngine gameEngine) {
		myGameEngine = gameEngine;
		BOUND_X = myGameEngine.getWidth();
		BOUND_Y = myGameEngine.getHeight();
	}

	public abstract void process(MotionAction motionAction, long elapsedTime);

	public abstract void setNextLocationIncrement(SpriteTemplate sprite,
			double x, double y);

	public boolean isOutLeft(SpriteTemplate sprite, double x) {
		return sprite.getX() + x < BASE_POINT;
	}

	public boolean isOutRight(SpriteTemplate sprite, double x) {
		return sprite.getX() + x + sprite.getWidth() > BOUND_X;
	}

	public boolean isOutTop(SpriteTemplate sprite, double y) {
		return sprite.getY() + y < BASE_POINT;
	}

	public boolean isOutBottom(SpriteTemplate sprite, double y) {
		return sprite.getY() + y + sprite.getHeight() > BOUND_Y;
	}
}
