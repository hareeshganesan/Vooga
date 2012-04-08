package PhysicsEngine;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

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
	protected final String FIGHTER="FighterSprite"; 
	protected final String BLOCK="PlatformBlock"; 
	protected final String WEAPON="WeaponSprite"; 

	protected SpriteTemplate myFighterSpriteOne;
	protected SpriteTemplate myFighterSpriteTwo;
	protected FightPhysicsEngine myPhysicsEngine;



	public CollisionReaction() {
	}

	public CollisionReaction(SpriteTemplate ps1, SpriteTemplate ps2) {
		myFighterSpriteOne = ps1;
		myFighterSpriteTwo = ps2;
	}
	
	public abstract boolean isThisComposition(SpriteTemplate ps1,
			SpriteTemplate ps2);

	public abstract void doThisReaction();
	
	public abstract CollisionReaction createCollisionReaction(SpriteTemplate ps1, SpriteTemplate ps2);

	public void stop(SpriteTemplate fighterSprite) {
		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocation(ZERO, ZERO);
	}

	public void go(SpriteTemplate fighterSprite, double speedX, double speedY) {
		myPhysicsEngine = new FightPhysicsEngine(fighterSprite);
		myPhysicsEngine.setNextLocation(speedX, speedY);
	}

}
