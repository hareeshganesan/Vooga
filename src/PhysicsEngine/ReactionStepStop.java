package PhysicsEngine;

import sprite.SpriteTemplate;

public class ReactionStepStop extends ReactionStep{

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		// TODO Auto-generated method stub
		myPhysicsEngine = new FightPhysicsEngine(ps1);
		myPhysicsEngine.setNextLocation(0, 0);
		
		myPhysicsEngine = new FightPhysicsEngine(ps2);
		myPhysicsEngine.setNextLocation(0, 0);
		
	}

}