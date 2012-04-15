package PhysicsEngine;

import java.awt.geom.Point2D;
import sprite.FighterSprite;
import sprite.SpriteTemplate;

public class ReactionPush extends Reaction{

	@Override
	public void act(SpriteTemplate ps1, SpriteTemplate ps2) {
		push(ps1,ps2);
	}
	
	private void push(SpriteTemplate ps1, SpriteTemplate ps2){
		Point2D p1 = ((FighterSprite) ps1).getMoveBy();
		Point2D p2 = ((FighterSprite) ps2).getMoveBy();


		// if( (ps1.getX()<ps2.getX() && p1.getX()>0 && p2.getX()<=0)
		// || (ps1.getX()>ps2.getX() && p1.getX()<=0 && p2.getX()>0)
		// || (ps1.getY()>ps2.getY() && p1.getY()>0 && p2.getY()<=0)
		// || (ps1.getY()<ps2.getY() && p1.getY()<=0 && p2.getY()>0)){
		double x1 = 0, y1 = 0, x2=0 , y2=0;
		// System.out.println(p1.getX()+"   "+ p1.getY()+"  "+ p2.getX()+"  " +
		// p2.getY());
		x1 = (-p1.getX() + p2.getX()) / 2;
		y1 = (-p1.getY() + p2.getY()) / 2;
		x2 = (p1.getX() + p2.getX()) / 2;
		y2 = (p1.getY() + p2.getY()) / 2;

		myPhysicsEngine = new FightPhysicsEngine(ps1);
		myPhysicsEngine.setNextLocation(x1, y1);

		myPhysicsEngine = new FightPhysicsEngine(ps2);
		myPhysicsEngine.setNextLocation(x2, y2);
	}

}