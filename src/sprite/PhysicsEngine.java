package sprite;


import com.golden.gamedev.GameObject;
import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;

public abstract class PhysicsEngine
{

    abstract void update(FighterSprite sprite, GameObject game, long elapsedTime);
}
