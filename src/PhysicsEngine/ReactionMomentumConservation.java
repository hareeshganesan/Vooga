package PhysicsEngine;

import java.awt.geom.Point2D;
import npsprite.SpriteTemplate;

public class ReactionMomentumConservation extends Reaction{

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
//	    System.out.println("do this rxnmconserv");
		Point2D p1 = ps1.getMoveBy();
		Point2D p2 = ps2.getMoveBy();
		
		double x1=p1.getX();
		double y1=p1.getY();
		double x2=p2.getX();
		double y2=p2.getY();
		double m1=ps1.getMass();
		double m2=ps2.getMass();
		
		double xx1=(m1-m2)*x1/(m1+m2)+2*m2*x2/(m1+m2);
		double xx2=(m2-m1)*x2/(m1+m2)+2*m1*x1/(m1+m2);
		double yy1=(m1-m2)*y1/(m1+m2)+2*m2*y2/(m1+m2);
		double yy2=(m2-m1)*y2/(m1+m2)+2*m1*y1/(m1+m2);
		
		myPhysicsEngine = new FightPhysicsEngine(ps1);
		myPhysicsEngine.setNextLocationIncrement(xx1, yy1);
		
		myPhysicsEngine = new FightPhysicsEngine(ps2);
		myPhysicsEngine.setNextLocationIncrement(xx2, yy2);
		
	}

}
