package sprite;

import com.golden.gamedev.GameObject;


public abstract class PhysicsEngine
{

    abstract void update (FighterSprite sprite,
                          GameObject game,
                          long elapsedTime);
}
