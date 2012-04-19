package npsprite;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.SpriteGroup;

import npsprite.properties.HealthProperty;
import npsprite.properties.PropertyObject;
import npsprite.properties.SpawnsProperty;

public class PropertiesTest {
    
    public PropertiesTest(){
        SpriteGroup all=new SpriteGroup("everything");

        SpriteTemplate t=new SpriteTemplate(GroupID.PLAYER_1);
        t.addProperty(HealthProperty.getName(), new HealthProperty(50));
        t.addProperty(SpawnsProperty.getName(), new SpawnsProperty(all));
        PropertyObject po=t.getProperty("health");

        System.out.println("first: "+((HealthProperty)po).getHealth());
        ((HealthProperty)po).addHealth(-5);
        System.out.println("first: "+((HealthProperty)po).getHealth());
        
        PropertyObject ps=t.getProperty("spawns");
        
        
        List<Point2D> locations=new ArrayList<Point2D>();
        locations.add(new Point2D.Double(50,75));
        locations.add(new Point2D.Double(100,100));
        ((SpawnsProperty)ps).spawnSprites(2, t,locations);
        
        System.out.println(all.getSize());
        System.out.println(all.getActiveSprite().getX()+" , "+all.getActiveSprite().getY());
        
        
    }
    
    public static void main(String[]arg){
        PropertiesTest t=new PropertiesTest();
    }
}
