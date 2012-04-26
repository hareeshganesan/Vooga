package npsprite.properties;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import npsprite.SpriteTemplate;
import game.CombatInstance;

/**
 * Sprites with this property can spawn other specific sprites into the game, under the same groupID 
 * as the originator sprite.
 * 
 * @author Wendy
 *
 */
public class SpawnsProperty extends PropertyObject{

    public static final String NAME="spawns";
    private CombatInstance myGame;
    private SpriteTemplate toSpawn; //TODO
    private int number; //TODO
    
    public SpawnsProperty(CombatInstance c, SpriteTemplate template){
        myGame=c;
        toSpawn=template;
        number=1; //default;
    }
    
    private ArrayList<SpriteTemplate> myOGame;
    //for testing, delete
    public SpawnsProperty(ArrayList<SpriteTemplate>all) {
        myOGame=all;
    }

    //spawns in random locations
    public void spawnSprites(){
        Random ran=new Random();
        int i=0;
        while (i<number){
            SpriteTemplate newsprite=toSpawn.clone();
            newsprite.setLocation(ran.nextDouble()*myGame.getWidth(),ran.nextDouble()*myGame.getHeight());
            myGame.addSprite(newsprite);
            i++;
        }
    }
    //spawns in locations given
    public void spawnSprites(List<Point2D>locations){
        int i=0;
        while (i<number){
            if (i==locations.size()){
                i=0; //loop back 
            }
            SpriteTemplate newsprite=toSpawn.clone();
            Point2D loc=locations.get(i);
            newsprite.setLocation(loc.getX(),loc.getY());
            myGame.addSprite(newsprite);

            i++;
        }
            
    }
    
    public void spawnSprites(int number, SpriteTemplate s, List<Point2D>locations){
        int i=0;
        while (i<number){
            SpriteTemplate newsprite=s.clone();
            Point2D loc=locations.get(i);
            newsprite.setLocation(loc.getX(),loc.getY());
//            myGame.addSprite(newsprite);

            myOGame.add(newsprite); //for testing, delete
            i++;
            
        }
    }

    public void setNumber(int n){
        number=n;
    }
    
    //yo dawg, i heard you like spawning so i let you spawn sprites that can spawn some more. rabbits
    @Override
    public PropertyObject clone() {
        return new SpawnsProperty(myGame,toSpawn);
    }

    /**
     * returns number of sprites to spawn
     */
    public double getValue() {
        return number;
    }
}
