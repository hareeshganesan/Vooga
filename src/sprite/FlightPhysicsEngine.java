package sprite;

import java.awt.event.KeyEvent;



import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;

/**
 * NOT YET IMPLEMENTED, IGNORE ME
 *
 */
public class FlightPhysicsEngine extends PhysicsEngine
{

    @Override
    void update (Sprite sprite, GameObject game, long elapsedTime)
    {
        if (sprite.getY() + sprite.getHeight() >= 0) sprite.move(0,-0.05 * elapsedTime);
        
        

    }

	@Override
	void update(AdvanceSprite sprite, GameObject game, long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

    @Override
    void update(FighterSprite sprite, GameObject game, long elapsedTime) {
        // TODO Auto-generated method stub
        
    }
}
