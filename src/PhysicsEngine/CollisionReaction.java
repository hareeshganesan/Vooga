package PhysicsEngine;

import sprite.FighterSprite;

/**
 * this is the super class of collision reaction
 * it includes the methods of finding the specific reaction, creating reaction and doing the reaction
 * also it has some basic step of reaction, such as stop and go
 * 
 * @author Donghe
 * 
 */
public abstract class CollisionReaction {
	
	private final int ZERO=0;

	protected FighterSprite myFighterSpriteOne;
	protected FighterSprite myFighterSpriteTwo;
	protected FightPhysicsEngine myPhysicsEngine;



	public CollisionReaction() {
	}

	public CollisionReaction(FighterSprite ps1, FighterSprite ps2) {
		myFighterSpriteOne = ps1;
		myFighterSpriteTwo = ps2;
	}
	
	public abstract boolean isThisComposition(FighterSprite ps1,
			FighterSprite ps2);

	public abstract void doThisReaction();
	
	public abstract CollisionReaction createCollisionReaction(FighterSprite ps1, FighterSprite ps2);

	public void stop(FighterSprite fighterSprite) {
		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocation(ZERO, ZERO);
	}

	public void go(FighterSprite fighterSprite, double speedX, double speedY) {
		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocation(speedX, speedY);
	}

}
