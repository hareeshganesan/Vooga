package npsprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import npsprite.properties.HealthProperty;

import sprite.HealthDisplay;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import events.CompositeEvent;
import events.HealthEvent;
import events.InactiveEvent;

//this test shows how two sprites can "collide" (press c on keyboard), leading 
//the torso sprite to gain health and the health sprite (prototyped power-up) to disappear.

//known issues: if you set the location of the torso sprite anywhere but 0,0 
//it flies off the screen
public class NPSTest extends Game {

    private ArrayList<SpriteTemplate> myGroups;
    private FighterBody myTree;
    private HealthSprite power;
    private Graphics2D myPen;


    @Override
    public void initResources ()
    {
        // bg = new Background();

        power =
            new HealthSprite(getImage("resources/block.png"),
                             GroupID.UNCATEGORIZED);
        power.setLocation(400, 300);

        BufferedImage imgTorso = getImage("src/resources/bodyParts/torso.png");
        LimbSprite torso = new LimbSprite("torso", imgTorso, GroupID.PLAYER_1,
                50,50);

        InactiveEvent pevent = new InactiveEvent();// TODO: SHOULD I CHANGE
                                                   // THESE INTO STATIC? IT'S A
                                                   // CLASS WRAPPING A SINGLE
                                                   // METHOD ONLY
        HealthEvent tevent = new HealthEvent();

        power.addCollisionEvent(GroupID.PLAYER_1, pevent);
        torso.addCollisionEvent(GroupID.UNCATEGORIZED, tevent);
        myTree = new FighterBody(torso, "fighter", new HealthDisplay(10, 20, 200));
        System.out.println(myTree.hasProperty(HealthProperty.NAME));
        ((HealthProperty) myTree.getProperty(HealthProperty.NAME)).addHealth(-25);
    }


    @Override
    public void render(Graphics2D pen) {
        myPen=pen;
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        myTree.render(pen);
        power.render(pen);
    }


    @Override
    public void update (long elapsedTime)
    {
        myTree.update(elapsedTime);
        power.update(elapsedTime);if(keyDown(KeyEvent.VK_LEFT)){
            myTree.move(myPen,-1, 0); 
        }
        if(keyDown(KeyEvent.VK_RIGHT)){
            myTree.move(myPen,1, 0);
        }
        if(keyDown(KeyEvent.VK_UP)){
            myTree.move(myPen,0, -1);
        }
        if(keyDown(KeyEvent.VK_DOWN)){
            myTree.move(myPen,0, 1);
        }
        
        if (keyPressed(KeyEvent.VK_C)) {
            fakeCollision(power, myTree);
        }
    }

    public void fakeCollision(SpriteTemplate p1, FighterBody myTree) {
        System.out.println("Health: " + myTree.getHealth());
        System.out.println(power.isActive());
        p1.collisionAction(myTree.getRoot());
        myTree.getRoot().collisionAction(p1);
        System.out.println("Health: " + myTree.getHealth());
        System.out.println(power.isActive());
    }


    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new NPSTest(), new Dimension(800, 600), false);
        loader.start();
    }
}
