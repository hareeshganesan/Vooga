package PhysicsEngine;

import npsprite.SpriteTemplate;

//import sprite.SpriteTemplate;

public class ReactionPunch extends Reaction {
	
	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
			punch(ps1,ps2);
			punch(ps2,ps1);
	}
	
	private void punch(SpriteTemplate f1, SpriteTemplate f2){
		if(f1.getMoveBy().getX()==0 && f1.getMoveBy().getY()==0){
			myPhysicsEngine = new FightPhysicsEngine(f1);
			
			double dx=0;
			double dy=0;
			
			if(f2.getMoveBy().getX()>0) dx=544-f1.getWidth()-f1.getX();
			else if(f2.getMoveBy().getX()<0) dx=-f1.getX();
			
			if(f2.getMoveBy().getY()>0) dy=544-f1.getHeight()-f1.getY();
			else if(f2.getMoveBy().getY()<0) dy=-f1.getY();
			
			myPhysicsEngine.setNextLocationIncrement(dx, dy);
		}
	}

}
