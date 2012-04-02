package sprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionShape;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

public class GeneralSpriteCollision extends PreciseCollisionGroup{
    
    public GeneralSpriteCollision(){
       pixelPerfectCollision=true; 
    }
    public void collided(Sprite s1, Sprite s2) {
        SpriteTemplate p1=(SpriteTemplate)s1;
        SpriteTemplate p2=(SpriteTemplate)s2;
        p1.collisionAction(p2.getID());
        p2.collisionAction(p1.getID());
    }
}
