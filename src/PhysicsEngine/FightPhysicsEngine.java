package PhysicsEngine;

import java.util.ArrayList;

import sprite.FighterSprite;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author Donghe
 */
public class FightPhysicsEngine extends PhysicsEngine {

	public ArrayList<Motion> myMotionList = new ArrayList<Motion>();

	public FightPhysicsEngine(GameObject game) {
		super(game);
		myMotionList.add(new MotionGoDown(myGame, boundX, boundY));
		myMotionList.add(new MotionGoLeft(myGame, boundX, boundY));
		myMotionList.add(new MotionGoRight(myGame, boundX, boundY));
		myMotionList.add(new MotionJumpUp(myGame, boundX, boundY));
		myMotionList.add(new MotionFallDown(myGame, boundX, boundY));
		myMotionList.add(new MotionRotate(myGame, boundX, boundY));
	}

	@Override
	public void update(FighterSprite fighterSprite, long elapsedTime) {
		for (Motion motion : myMotionList) {
			if (motion.isThisMotion(fighterSprite))
				motion.doThisMotion(fighterSprite, elapsedTime);

		}
		// printLocation(sprite, myGame);
		/**
		 * 
		 * FighterSprite fs, x_speed, y_speed,
		 * 
		 * calculate the new location based on current position
		 * fs.setNextLocation(new Point2D.Double(dx,dy))
		 * checkCollision
		 */
	}

	public void printLocation(FighterSprite sprite, GameObject game) {
		System.out.println("X:" + sprite.getX() + "  Y:" + sprite.getY()
				+ "  Height:" + sprite.getHeight() + "  GameHeight:"
				+ game.getHeight());

	}

}
