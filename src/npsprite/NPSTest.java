package npsprite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import npsprite.SpriteID.GroupID;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

import events.*;

//12 Apr - this test shows how two sprites can "collide" (press c on keyboard), leading 
//the torso sprite to gain health and the health sprite (prototyped power-up) to disappear.
public class NPSTest extends Game {

    private ArrayList<SpriteTemplate> myGroups;
    private NodeSprite myTree;
    private HealthSprite power;

    @Override
    public void initResources() {
        // bg = new Background();

        power = new HealthSprite(getImage("resources/block.png"),
                GroupID.UNCATEGORIZED);
        power.setLocation(400, 300);

        BufferedImage imgTorso = getImage("src/resources/bodyParts/torso.png");
        myTree = new NodeSprite(imgTorso, GroupID.PLAYER_1, 100, 100);
        myTree.addHealth(-25);

        CompositeEvent pevent = new CompositeEvent(myTree);
        pevent.addEvent(new InactiveEvent());//TODO: SHOULD I CHANGE THESE INTO STATIC? IT'S A CLASS WRAPPING A SINGLE METHOD ONLY
        CompositeEvent tevent = new CompositeEvent(power);
        tevent.addEvent(new HealthEvent());

        power.addCollisionEvent(pevent);
        myTree.addCollisionEvent(tevent);
    }

    @Override
    public void render(Graphics2D pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        myTree.render(pen);
        power.render(pen);
    }

    @Override
    public void update(long elapsedTime) {
        myTree.update(elapsedTime);
        power.update(elapsedTime);
        if (keyDown(KeyEvent.VK_RIGHT)) {
            myTree.move(1, 0);
        }
        if (keyDown(KeyEvent.VK_LEFT)) {
            myTree.move(-1, 0);
        }
        if (keyDown(KeyEvent.VK_UP)) {
            myTree.move(0, -1);
        }
        if (keyDown(KeyEvent.VK_DOWN)) {
            myTree.move(0, 1);
        }
        if (keyPressed(KeyEvent.VK_C)) {
            fakeCollision(power, myTree);
        }
    }

    public void fakeCollision(SpriteTemplate p1, SpriteTemplate p2) {
        System.out.println("Health: " + myTree.getHealth());
        System.out.println(power.isActive());
        p1.collisionAction(p2);
        p2.collisionAction(p1);
        System.out.println("Health: " + myTree.getHealth());
        System.out.println(power.isActive());
    }

    public static void main(String[] args) {
        GameLoader loader = new GameLoader();
        loader.setup(new NPSTest(), new Dimension(800, 600), false);
        loader.start();
    }
}
