package events;

import npsprite.SpriteTemplate;
import npsprite.properties.SpawnsProperty;

public class SpawnEvent extends CollisionEvent{

    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        if (me.hasProperty(SpawnsProperty.NAME)){
        ((SpawnsProperty) me.getProperty(SpawnsProperty.NAME)).spawnSprites();
        }
        //TODO: LET SPRITETEMPLATE DECIDE SPAWNING LOCATIONS SOMEWHERE
    }

}
