package events;

import java.awt.geom.Point2D;

import npsprite.GroupID;
import npsprite.SpriteTemplate;
import npsprite.properties.SpawnsProperty;

public class SpawnEvent extends CollisionEvent {

    private static SpawnEvent he;
    protected SpawnEvent(){}
    
    public static SpawnEvent getInstanceOf(){
        if (he==null){
            he=new SpawnEvent();
        }
        return he;
    }
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        if (me.hasProperty(SpawnsProperty.NAME)) {
            if (GroupID.isFighter(me.getGroupID())){
                Point2D loc=new Point2D.Double(me.getCenterX(),me.getCenterY());
                ((SpawnsProperty) me.getProperty(SpawnsProperty.NAME)).translateSpawnSpots(loc);
            }
            ((SpawnsProperty) me.getProperty(SpawnsProperty.NAME))
                    .spawnSprites();
        }
    }
    public static String getName(){
        return "spawn";
    }

}
