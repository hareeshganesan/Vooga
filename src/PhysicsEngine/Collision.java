package PhysicsEngine;

import sprite.FighterSprite;

public class Collision {
	private final int BOUND_X = 544;
	private final int BOUND_Y = 544;
	private final int BASE_POINT = 0;

	private FighterSprite myFighterSpriteOne;
	private FighterSprite myFighterSpriteTwo;

	public Collision(FighterSprite ps1, FighterSprite ps2) {
		myFighterSpriteOne = ps1;
		myFighterSpriteTwo = ps2;
	}

	public boolean isCollided() {
		// for (int i = BASE_POINT; i < BOUND_X; i++) {
		// for (int j = BASE_POINT; j < BOUND_Y; j++) {
		// if (isCoverThisPoint(myFighterSpriteOne, i, j)
		// && isCoverThisPoint(myFighterSpriteTwo, i, j))
		// return true;
		// }
		// }

		if (Math.abs(myFighterSpriteOne.getX() - myFighterSpriteTwo.getX())
				+ Math.abs(myFighterSpriteOne.getY()
						- myFighterSpriteTwo.getY()) < 30)
			return true;

		return false;
	}

	public boolean isCoverThisPoint(FighterSprite fs, int x, int y) {
		if (fs.getX() < x)
			return false;
		if (fs.getX() + fs.getWidth() > x)
			return false;
		if (fs.getY() < y)
			return false;
		if (fs.getY() + fs.getHealth() > y)
			return false;
		return true;
	}

	public void checkCollison() {
		if (isCollided()) {
			stop(myFighterSpriteOne);
			stop(myFighterSpriteTwo);
			System.out.println("collision");
		}

	}

	public void stop(FighterSprite fighterSprite) {
		FightPhysicsEngine fightPhysicsEngine = new FightPhysicsEngine(
				fighterSprite);
		fightPhysicsEngine.setNextLocation(0, 0);
	}
}
