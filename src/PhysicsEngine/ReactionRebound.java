package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;

public class ReactionRebound extends Reaction {

	private final double STOP = 0;
	private double reboundFactor = 20.0;
	private boolean reboundUp = false;
	private boolean reboundDown = false;
	private boolean reboundLeft = false;
	private boolean reboundRight = false;

	public ReactionRebound() {

	}

	public ReactionRebound(double factor) {
		reboundFactor = factor;
	}

	@Override
	public void act(SpriteTemplate spriteOne, SpriteTemplate spriteTwo,
			PhysicsEngine physicsEngine) {
		if (spriteOne.getClass().equals(PlatformBlock.class)) {
			rebound(spriteTwo, spriteOne, physicsEngine);
		} else if (spriteTwo.getClass().equals(PlatformBlock.class)) {
			rebound(spriteOne, spriteTwo, physicsEngine);
		}

	}

	private void rebound(SpriteTemplate fighterSprite, SpriteTemplate block,
			PhysicsEngine physicsEngine) {
		Point2D moveBy = fighterSprite.getMoveBy();
		double horizontalIncrement = moveBy.getX();
		double verticalIncrement = moveBy.getY();

		if (!reboundUp && fighterSprite.getCollisionStatus().getDown()) {
			verticalIncrement = Math.min(verticalIncrement, STOP);
		} else if (!reboundDown && fighterSprite.getCollisionStatus().getUp()) {
			verticalIncrement = Math.max(verticalIncrement, STOP);
		} else if (!reboundRight
				&& fighterSprite.getCollisionStatus().getLeft()) {
			horizontalIncrement = Math.max(horizontalIncrement, STOP);
		} else if (!reboundLeft
				&& fighterSprite.getCollisionStatus().getRight()) {
			horizontalIncrement = Math.min(horizontalIncrement, STOP);
		} else {
			horizontalIncrement = -moveBy.getX() * reboundFactor;
			verticalIncrement = -moveBy.getY() * reboundFactor;
		}

		physicsEngine.setNextLocationIncrement(fighterSprite,
				horizontalIncrement, verticalIncrement);
	}

	public void setReboundFactor(double factor) {
		reboundFactor = factor;
	}

	public void setUp(boolean up) {
		reboundUp = up;
	}

	public void setDown(boolean down) {
		reboundDown = down;
	}

	public void setLeft(boolean left) {
		reboundLeft = left;
	}

	public void setRight(boolean rightS) {
		reboundRight = rightS;
	}

}
