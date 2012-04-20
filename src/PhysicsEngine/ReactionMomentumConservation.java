package PhysicsEngine;

import java.awt.geom.Point2D;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

public class ReactionMomentumConservation extends Reaction{

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		Point2D p1 = ps1.getMoveBy();
		Point2D p2 = ps2.getMoveBy();
		
		double x1=p1.getX();
		double y1=p1.getY();
		double x2=p2.getX();
		double y2=p2.getY();
		double m1=getMass(ps1);
		double m2=getMass(ps2);
		
		double xx1=(m1-m2)*x1/(m1+m2)+2*m2*x2/(m1+m2);
		double xx2=(m2-m1)*x2/(m1+m2)+2*m1*x1/(m1+m2);
		double yy1=(m1-m2)*y1/(m1+m2)+2*m2*y2/(m1+m2);
		double yy2=(m2-m1)*y2/(m1+m2)+2*m1*y1/(m1+m2);
		
		myPhysicsEngine = new FightPhysicsEngine(ps1);
		myPhysicsEngine.setNextLocationIncrement(xx1, yy1);
		
		myPhysicsEngine = new FightPhysicsEngine(ps2);
		myPhysicsEngine.setNextLocationIncrement(xx2, yy2);
		
	}
	
	private double getMass(SpriteTemplate ps1){
		double m=0;
		if(FighterSprite.class.isAssignableFrom(ps1.getClass())){
			m=((FighterSprite) ps1).getMass();
		}
		return m;
	}

}
