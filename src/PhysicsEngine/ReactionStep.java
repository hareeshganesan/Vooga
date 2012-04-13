package PhysicsEngine;
import sprite.SpriteTemplate;

public  abstract class  ReactionStep {
	protected FightPhysicsEngine myPhysicsEngine;
	
	public abstract void act(SpriteTemplate ps1, SpriteTemplate ps2);
}
