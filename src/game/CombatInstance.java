package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;
import npsprite.FighterBody;
import npsprite.GroupID;
import npsprite.NodeSprite;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;
import npsprite.SpriteGroupTemplate;
import org.jdom.JDOMException;
import camera.Camera;
import camera.CameraBackground;
import camera.CameraUtility;
import events.HealthEvent;
import PhysicsEngine.*;
import action.MotionAction;
import action.QuitAction;
import ai.AIAgent;
import camera.*;

public class CombatInstance extends GameState {

    private String DEFAULT_IMAGE = "resources/title.png";

    // Engines
    MainGame myEngine;
    InputHandler myHandler;
    // Camera camera;
    CameraUtility cameraUtility;
    private PhysicsEngine myPhysicsEngine;

    // Sprites
    // ArrayList<FighterSprite> playerSprites;
    // ArrayList<PlatformBlock> platform;
    ArrayList<FighterBody> playerSprites;
    ArrayList<PlatformBlock> platform;
    ArrayList<SpriteTemplate> spawns;
    ArrayList<SpriteTemplate> nonplayers;
    // ArrayList<SpriteTemplate> powerups;

    // Collision
    private Collision myCollision;
    private SpriteGroupTemplate groupSprite;

    CameraBackground bg;
    CameraSprite cs;

    public CombatInstance(MainGame engine) {
        super(engine);
        myEngine = engine;
        myHandler = new InputHandler();
        // camera = new FloatingCamera();
        cameraUtility = new CameraUtility();
        myPhysicsEngine = new FightPhysicsEngine(myEngine);
    }

    @Override
    public void initResources() {

        nextState = (GameState) myEngine.getGame(myEngine.getMain());

        LevelObjectsFactory lof = new LevelObjectsFactory(this);
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources"));
        fc.setApproveButtonText("load game file");
        int returnVal = fc.showOpenDialog(null);
        myHandler.addKey(KeyEvent.VK_Q, new QuitAction(myEngine));
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                lof.parseFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JDOMException e) {
                e.printStackTrace();
            }
        }

        // TODO: REMOVE HARDCODING LATER
        // GameFont font = fontManager.getFont(getImage("resources/font.png"));
        // BufferedImage HPimage = getImage("resources/frame.png");
        // HealthDisplay display = new HealthDisplay(returnVal, returnVal,
        // returnVal);
        //
        // TODO: MAKE IT SO DIFFERENT FIGHTERS CAN HAVE DIFFERENT DISPLAYS?
        try {
            playerSprites = lof.createNPFighters();
            platform = lof.createPlatforms();
            nonplayers = lof.createNPSprites();
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String back = lof.getBackground();
        if (back == null) {
            back = DEFAULT_IMAGE;
        }
        BufferedImage b = getImage(back);
        bg = new CameraBackground(b);
        cs = new CameraSprite(null);

        // When we create Collision for a game, we need to pass in three
        // arguments. 1. a SpriteGroupTemplate, which contains several teams,
        // and we just check collisions between sprites from different teams. 2.
        // a list of collisionKind, it will help us to find which kind of
        // collision it is, for example, the collision between sprites and
        // blocks or collision between two sprites from the same team. 3. a
        // physicsEngine which we need to set collision reaction

        spawns=new ArrayList<SpriteTemplate>();
        groupSprite = new SpriteGroupTemplate();
        groupSprite.addPlatformBlockArray(platform);
        groupSprite.addFighterSpriteArray(playerSprites);
        groupSprite.addSpriteArray(nonplayers);

        ArrayList<CollisionKind> CollisionkindList = new ArrayList<CollisionKind>();
        CollisionkindList.add(new CollisionKindFriends(
                new ReactionMomentumConservation()));
        CollisionkindList.add(new CollisionKindEnemy(new ReactionPush()));
        CollisionkindList.add(new CollisionKindNeutral(new ReactionRebound()));

        myCollision = new Collision(groupSprite, CollisionkindList,
                myPhysicsEngine);

    }

    @Override
    public void render(Graphics2D pen) {
        // camera.render(pen, bg);
        // bg.render(pen, camera, camera.getX(), camera.getY(), camera.getX(),
        // camera.getY(), camera.getHeight(), camera.getWidth());
        // bg.render(pen);

        bg.render(pen);
        for (FighterBody sprite : playerSprites)
            // cs.render(pen, sprite, camera);
            sprite.render(pen);
        for (PlatformBlock pb : platform) {
            // cs.render(pen, pb, camera);
            pb.render(pen);
            pen.drawRect((int) pb.getX() + pb.getWidth() / 2 - 2,
                    (int) pb.getY() + pb.getHeight() / 2 - 2, 4, 4);
            pen.draw(new Rectangle2D.Double(pb.getX(), pb.getY(),
                    pb.getWidth(), pb.getHeight()));
        }
        for (SpriteTemplate p : nonplayers) {
            p.render(pen);
        }
        // FighterBody ai = playerSprites.get(2);
        // FighterBody user = playerSprites.get(0);
        //
        // pen.drawLine((int)ai.getX(), (int)ai.getY(), (int) user.getX(), (int)
        // user.getY());
        // pen.drawLine((int) ai.getX()+ai.getWidth(),
        // (int)ai.getY()+ai.getHeight(), (int) user.getX()+user.getWidth(),
        // (int) user.getY()+user.getHeight());

    }

    @Override
    public void update(long elapsedTime) {
        commitSpawnedSprites();
        myHandler.update(elapsedTime, myEngine);
        // camera.update(playerSprites, bg);
        myHandler.update(elapsedTime, myEngine);
        bg.update(elapsedTime);

        for (FighterBody sprite : playerSprites) {
            if (AIAgent.class.isAssignableFrom(sprite.getClass())) {
                AIAgent ai = (AIAgent) sprite;
                ai.calculateLocation(elapsedTime);
            }
        }

        for (FighterBody sprite : playerSprites) {
            MotionAction.Gravity(sprite, 0.6, myPhysicsEngine).performAction(
                    elapsedTime);
        }

        myCollision.checkGroupCollision();

        for (FighterBody sprite : playerSprites) {
            // printCollision(sprite);
            sprite.update(elapsedTime);
        }

        for (PlatformBlock pb : platform)
            pb.update(elapsedTime);

        for (SpriteTemplate sprite : nonplayers) {
            sprite.update(elapsedTime);
        }
    }

    private void printCollision(FighterBody sprite) {
        if (sprite.getCollisionStatus().getDown())
            System.out.println(sprite.getGroupID() + " is collided at DOWN");
        if (sprite.getCollisionStatus().getUp())
            System.out.println(sprite.getGroupID() + " is collided at UP");
        if (sprite.getCollisionStatus().getLeft())
            System.out.println(sprite.getGroupID() + " is collided at LEFT");
        if (sprite.getCollisionStatus().getRight())
            System.out.println(sprite.getGroupID() + " is collided at RIGHT");
        if (sprite.getCollisionStatus().getStandOnSth())
            System.out.println(sprite.getGroupID() + " is standing on sth.");
    }

    public InputHandler getMyHandler() {
        return myHandler;
    }

    public List<FighterBody> getFighters() {
        return Collections.unmodifiableList(playerSprites);

    }

    @Override
    public void transitionState() {
        if (nextState != null)
            myEngine.nextGame = this.nextState;
        else
            myEngine.nextGame = this.lastState;
        super.finish();
    }

    /*
     * TODO IT'D BE A LOT EASIER FOR THE SPRITE TO ADD ITSELF TO A GROUP
     * (depending on groupID) INSIDE OF THE SPAWNS-PROPERTY, AND HAVE COLLISIONS
     * AUTOMATICALLY UPDATE ITSELF
     */
    public void addSprite(SpriteTemplate s) {
        spawns.add(s);
        // nonplayers.add(s);
        // groupSprite.addSpriteInNewTeam(s);

        // change group here
        // can find the sprite index in its team, and the team index in the
        // group
        // you can remove a sprite
        // you also can add some sprite in certain team or in a new team
        // myCollision.setCollisionGroup(groupSprite);
    }

    public void commitSpawnedSprites() {
        if (!spawns.isEmpty()) {
            for (SpriteTemplate s:spawns){
                nonplayers.add(s);
                int teamIndex=groupSprite.getTeam(s.getGroupID());
                groupSprite.addSpriteInOldTeam(s, teamIndex);
            }
            myCollision.setCollisionGroup(groupSprite);
            spawns.clear();
        }
    }

    public PhysicsEngine getPhysicsEngine() {
        return myPhysicsEngine;
    }

    public ArrayList<SpriteTemplate> getObstacles() {
        ArrayList<SpriteTemplate> obstacles = new ArrayList<SpriteTemplate>();
        obstacles.addAll(platform);
        // obstacles.addAll(playerSprites);
        return obstacles;
    }
}
