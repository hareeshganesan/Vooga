package PhysicsEngine;

import game.CombatInstance;
import java.awt.event.KeyEvent;
import sprite.FighterSprite;
import sprite.SpriteTemplate;

public class ReactionStepPunch extends ReactionStep {

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		FighterSprite f1=(FighterSprite) ps1;
		FighterSprite f2=(FighterSprite) ps2;
		if(CombatInstance.myEngine.keyDown(KeyEvent.VK_E)){
			punch(f1);
			punch(f2);
		}
		
	}
	
	private void punch(FighterSprite f){
		if(f.getMoveBy().getX()==0 && f.getMoveBy().getY()==0){
			myPhysicsEngine = new FightPhysicsEngine(f);
			myPhysicsEngine.setNextLocation(544-f.getWidth()-f.getX(), 0);
		}
	}

}
