package game;

import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
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
import org.jdom.JDOMException;
import npsprite.SpriteGroupTemplate;
import camera.Camera;
import camera.CameraBackground;
import camera.CameraUtility;
import events.HealthEvent;
import PhysicsEngine.Collision;
import PhysicsEngine.CollisionKind;
import PhysicsEngine.CollisionKindEnemy;
import PhysicsEngine.CollisionKindFriends;
import PhysicsEngine.CollisionKindNeutral;
import PhysicsEngine.FightPhysicsEngine;
import PhysicsEngine.PhysicsEngine;
import PhysicsEngine.ReactionMomentumConservation;
import PhysicsEngine.ReactionPunch;
import PhysicsEngine.ReactionPush;
import PhysicsEngine.ReactionRebound;
import PhysicsEngine.ReactionStop;
import action.MotionAction;
import action.QuitAction;
import ai.AIAgent;
import camera.*;


public class CombatInstance extends GameState {
    private String DEFAULT_IMAGE = "resources/title.png";

    // Engines
    MainGame myEngine;
    InputHandler myHandler;
    //Camera camera;
    CameraUtility cameraUtility;
    private PhysicsEngine myPhysicsEngine;

    // Sprites
    // ArrayList<FighterSprite> playerSprites;
    // ArrayList<PlatformBlock> platform;
    ArrayList<FighterBody> playerSprites;
    ArrayList<PlatformBlock> platform;
    ArrayList<SpriteTemplate> spawns;
//    ArrayList<SpriteTemplate> powerups;

    // Collision
    private Collision myCollision;
    private SpriteGroupTemplate groupSprite;

    CameraBackground bg;
    CameraSprite cs;

    public CombatInstance(MainGame engine) {
        super(engine);
        myEngine = engine;
        myHandler = new InputHandler();
        //camera = new FloatingCamera();
        cameraUtility = new CameraUtility();
        myPhysicsEngine = new FightPhysicsEngine(myEngine);
    }

    public ArrayList<SpriteTemplate> getObstacles(){
        ArrayList<SpriteTemplate> objects = new ArrayList<SpriteTemplate>();
        objects.addAll(platform);
        objects.addAll(playerSprites);
        return objects;
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
            platform = lof.createNPBlocks();
//            powerups=lof.createPowerUps();
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

        // Collision setting:
        // collision is created by passing in:
        // 1. all the sprites as a SpriteGroupTemplate
        // 2. a CollisionKind or a list of CollisionKind (can add or delete
        // CollisonKind later)
        //
        // CollisionKind means the particular kind of the collison
        // CollisionKindFriends is used when collision happens between
        // FighterSprites or AI
        // CollisionKindEnemy is used when collision happens between one
        // FighterSprite and one WeaponSprite
        // CollisionKindNeutral is used when collision happens if there is a
        // PlatformBlock
        //
        // CollisionKind is created by passing in a Reaction or a list of
        // Reaction
        //
        // you can create any new concrete CollisionKind or concrete Reaction
        // then add it into myCollision

        groupSprite = new SpriteGroupTemplate("team");
        groupSprite.addFighterSpriteArray(playerSprites);
        groupSprite.addPlatformBlockArray(platform);
//        groupSprite.addSpriteArray(powerups);

        ArrayList<CollisionKind> CollisionkindList = new ArrayList<CollisionKind>();
        CollisionkindList.add(new CollisionKindFriends(
                new ReactionMomentumConservation()));
        CollisionkindList.add(new CollisionKindEnemy(new ReactionPush()));
        CollisionkindList.add(new CollisionKindNeutral(new ReactionRebound()));

        myCollision = new Collision(groupSprite, CollisionkindList, myPhysicsEngine);

    }

    // TODO: remove hard-coding, this is for testing on newsample.xml
    private void addCollisionActions() {
        for (FighterBody f : playerSprites) {
            for (NodeSprite n : f.getBodyParts()) {
                n.addCollisionEvent(GroupID.POWER_UP, new HealthEvent());
            }
        }
//        for (SpriteTemplate f : powerups) {
//            f.addCollisionEvent(GroupID.PLAYER_1, new InactiveEvent());
//            f.addCollisionEvent(GroupID.PLAYER_2, new InactiveEvent());
//        }

    }

    @Override
    public void render(Graphics2D pen) {
        //camera.render(pen, bg);
        //bg.render(pen, camera, camera.getX(), camera.getY(), camera.getX(),
        //        camera.getY(), camera.getHeight(), camera.getWidth());
        // bg.render(pen);

        bg.render(pen);
        for (FighterBody sprite : playerSprites)
            //cs.render(pen, sprite, camera);
            sprite.render(pen);
        for (PlatformBlock pb : platform) {
            //cs.render(pen, pb, camera);
            pb.render(pen);
            pen.drawOval(((int)pb.getCenterX())-3, ((int)pb.getCenterY())-3, 3, 3);
        }
        pen.drawLine((int) playerSprites.get(2).getX(), (int) playerSprites.get(2).getY(), (int) playerSprites.get(0).getX(), (int) playerSprites.get(0).getY());

//        for (SpriteTemplate p : powerups) {
//            p.render(pen);
//        }
        
    }

    @Override
    public void update(long elapsedTime) {
        myHandler.update(elapsedTime, myEngine);
        //camera.update(playerSprites, bg);
        myHandler.update(elapsedTime, myEngine);
        bg.update(elapsedTime);

        for (FighterBody sprite : playerSprites) {
            if (AIAgent.class.isAssignableFrom(sprite.getClass())) {
                AIAgent ai = (AIAgent) sprite;
                ai.calculateLocation(elapsedTime);
            }
        }
        
        for (FighterBody sprite : playerSprites){
        	//MotionAction.Gravity(sprite,0.3,myPhysicsEngine).performAction(elapsedTime);
        }

        myCollision.checkGroupCollision();

        for (FighterBody sprite : playerSprites) {
//        	printCollision(sprite);
            sprite.update(elapsedTime);
        }

        for (PlatformBlock pb : platform)
            pb.update(elapsedTime);

//        for (SpriteTemplate sprite : powerups) {
//            sprite.update(elapsedTime);
//        }
    }
    
    private void printCollision(FighterBody sprite){
    	if(sprite.getCollisionStatus().getDown()) System.out.println(sprite.getGroupID()+" is collided at DOWN");
    	if(sprite.getCollisionStatus().getUp()) System.out.println(sprite.getGroupID()+" is collided at UP");
    	if(sprite.getCollisionStatus().getLeft()) System.out.println(sprite.getGroupID()+" is collided at LEFT");
    	if(sprite.getCollisionStatus().getRight()) System.out.println(sprite.getGroupID()+" is collided at RIGHT");
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
        groupSprite.addSpriteInNewTeam(s);
        
    	// change group here
    	// can find the sprite index in its team, and the team index in the group
    	// you can remove a sprite
    	// you also can add some sprite in certain team or in a new team
    	myCollision.setCollisionGroup(groupSprite);
    }
    
    public PhysicsEngine getPhysicsEngine(){
    	return myPhysicsEngine;
    }
}
