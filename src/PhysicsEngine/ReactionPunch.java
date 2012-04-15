package PhysicsEngine;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

public class ReactionPunch extends Reaction {
	
	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		FighterSprite f1=(FighterSprite) ps1;
		FighterSprite f2=(FighterSprite) ps2;
			punch(f1,f2);
			punch(f2,f1);
	}
	
	private void punch(FighterSprite f1, FighterSprite f2){
		if(f1.getMoveBy().getX()==0 && f1.getMoveBy().getY()==0){
			myPhysicsEngine = new FightPhysicsEngine(f1);
			
			double dx=0;
			double dy=0;
			
			if(f2.getMoveBy().getX()>0) dx=544-f1.getWidth()-f1.getX();
			else if(f2.getMoveBy().getX()<0) dx=-f1.getX();
			
			if(f2.getMoveBy().getY()>0) dy=544-f1.getHeight()-f1.getY();
			else if(f2.getMoveBy().getY()<0) dy=-f1.getY();
			
			myPhysicsEngine.setNextLocation(dx, dy);
		}
	}

}
