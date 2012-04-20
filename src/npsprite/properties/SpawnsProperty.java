package npsprite.properties;

import java.awt.geom.Point2D;
import java.util.List;

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

    static final String NAME="spawns";
    private CombatInstance myGame;
    
    public SpawnsProperty(CombatInstance c){
        myGame=c;
    }
    
    //TODO for testing, delete later
    private SpriteGroup myOgame;
    
    public SpawnsProperty(SpriteGroup g){
        myOgame=g;
    }
    //
    
    public void spawnSprites(int number, SpriteTemplate s, List<Point2D>locations){
        int i=0;
        while (i<number){
            SpriteTemplate newsprite=s.clone();
            Point2D loc=locations.get(i);
            newsprite.setLocation(loc.getX(),loc.getY());
            myGame.addSprite(newsprite);

//            myOgame.add(newsprite);
            i++;
            
        }
    }

    public static String getName(){
        return NAME;
    }
    
    //yo dawg, i heard you like spawning so i let you spawn sprites that can spawn some more. rabbits
    @Override
    public PropertyObject clone() {
        return new SpawnsProperty(myGame);
    }
}
