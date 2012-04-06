package PhysicsEngine;

import sprite.FighterSprite;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author Donghe
 * 
 */
public abstract class PhysicsEngine {

	protected int boundX;
	protected int boundY;
	protected GameObject myGame;
//	protected FighterSprite myFighterSprite;

	public PhysicsEngine(GameObject game) {
		myGame = game;
		boundX = game.getWidth();
		boundY = game.getHeight();
//		myFighterSprite=fighterSprite;
	}

	public abstract void update(FighterSprite fighterSprite, long elapsedTime);
}
