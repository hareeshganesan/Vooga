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
        ArrayList<SpriteTemplate>all=new ArrayList<SpriteTemplate>();

        SpriteTemplate t=new SpriteTemplate(GroupID.PLAYER_1);
        t.addProperty(HealthProperty.NAME, new HealthProperty(50));
        t.addProperty(SpawnsProperty.NAME, new SpawnsProperty(all));
        PropertyObject po=t.getProperty("health");

        System.out.println("first: "+po.getValue());
        ((HealthProperty)po).addHealth(-5);
        System.out.println("first: "+po.getValue());
        
        PropertyObject ps=t.getProperty("spawns");
        List<Point2D> locations=new ArrayList<Point2D>();
        locations.add(new Point2D.Double(50,75));
        locations.add(new Point2D.Double(100,100));
        ((SpawnsProperty)ps).spawnSprites(2, t,locations);
        
        System.out.println(all.size());
        System.out.println(all.get(0).getX()+" , "+all.get(0).getY());
        System.out.println(all.get(1).getX()+" , "+all.get(1).getY());
        
        
    }
    
    public static void main(String[]arg){
        PropertiesTest t=new PropertiesTest();
    }
}
