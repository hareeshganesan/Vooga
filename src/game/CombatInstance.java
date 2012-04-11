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
import org.jdom.JDOMException;
import sprite.FighterSprite;
import sprite.GeneralSpriteCollision;
import sprite.PlatformBlock;
import sprite.SpriteGroupTemplate;
import action.Action;
import action.DownAction;
import camera.Camera;
import PhysicsEngine.BasicPhysicsEngine;
import PhysicsEngine.Collision;
import action.Action;
import action.DownAction;
import action.QuitAction;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;


public class CombatInstance extends GameState
{
    private String DEFAULT_IMAGE = "resources/title.png";

    //Engines
    MainGame myEngine;
    InputHandler myHandler;
    Camera camera;
    
    //Sprites
    ArrayList<FighterSprite> playerSprites;
    ArrayList<PlatformBlock> platform;
//    GeneralSpriteCollision temp;
//    GeneralSpriteCollision p_block;
    
    ArrayList<Collision> myCollisionList;
    
    Background bg;


    public CombatInstance (MainGame engine)
    {
        super(engine);
        myEngine = engine;
        myHandler = new InputHandler();
        camera = new Camera();
    }


    @Override
    public void initResources ()
    {
        LevelObjectsFactory lof = new LevelObjectsFactory(this);
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources"));
        fc.setApproveButtonText("load game file");
        int returnVal = fc.showOpenDialog(null);
        myHandler.addKey(KeyEvent.VK_Q, new QuitAction(myEngine));
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            try
            {
                lof.parseFile(file);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JDOMException e)
            {
                e.printStackTrace();
            }
        }

        //TODO: REMOVE HARDCODING LATER
        GameFont font = fontManager.getFont(getImage("resources/font.png"));
        BufferedImage HPimage = getImage("resources/frame.png");
//    	HealthDisplay display = new HealthDisplay(returnVal, returnVal, returnVal);
        //
        //TODO: MAKE IT SO DIFFERENT FIGHTERS CAN HAVE DIFFERENT DISPLAYS?
        try
        {
            playerSprites = lof.createFighters();
            platform = lof.createBlocks();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String back = lof.getBackground();
        if (back == null)
        {
            back = DEFAULT_IMAGE;
        }
        BufferedImage b = getImage(back);
        bg = new ImageBackground(b);

        //TODO: FML WHY ARE WE DOING THIS
        //this is temporary fix just to make the code work, will need to overwrite later when we implement finer collision checking and physics engine
//        SpriteGroup p1 = new SpriteGroup("p1");
//        p1.add(playerSprites.get(0));
//        p1.setBackground(bg);
//        SpriteGroup p2 = new SpriteGroup("p2");
//        p2.add(playerSprites.get(1));
//        p2.setBackground(bg);
//
//        temp = new GeneralSpriteCollision();
//        temp.setCollisionGroup(p1, p2);
//
//        SpriteGroup b1 = new SpriteGroup("b");
//        for (PlatformBlock p : platform)
//        {
//            b1.add(p);
//        }
//        SpriteGroup ps = new SpriteGroup("players");
//        for (FighterSprite f : playerSprites)
//        {
//            ps.add(f);
//        }
//        ps.setBackground(bg);
//        b1.setBackground(bg);
//        p_block = new GeneralSpriteCollision();
//        p_block.setCollisionGroup(ps, b1);
        
        SpriteGroupTemplate groupPlayer = new SpriteGroupTemplate("team1");
		groupPlayer.addFighterSpriteArray(playerSprites);

		SpriteGroupTemplate groupBlock = new SpriteGroupTemplate("team2");
		groupBlock.addPlatformBlockArray(platform);

		myCollisionList.add(new Collision(groupPlayer));
		myCollisionList.add(new Collision(groupPlayer, groupBlock));

    }

    @Override
    public void render (Graphics2D pen)
    {
        camera.render(pen);
        //bg.render(pen, camera, camera.getX(), camera.getY(), camera.getX(), camera.getY(), camera.getHeight(), camera.getWidth());
        bg.render(pen);        
        for (FighterSprite sprite : playerSprites)
            sprite.render(pen);
        for (PlatformBlock pb : platform)
        {
            pb.render(pen);
        }
    }


    @Override
    public void update (long elapsedTime)
    {
        myHandler.update(elapsedTime, myEngine);
        camera.update(playerSprites);
      //bg.setToCenter(playerSprites.get(0));
        bg.setToCenter(camera.getX(), camera.getY(), camera.getHeight(), camera.getWidth());
        myHandler.update(elapsedTime, myEngine);
        bg.update(elapsedTime);
        for (FighterSprite sprite : playerSprites)
        {
            Action gravity = new DownAction(sprite);
            //gravity.performAction(elapsedTime / 2);
            sprite.update(elapsedTime);
        }
        for (PlatformBlock pb : platform)
        {
            pb.update(elapsedTime);
        }

		// temp.checkCollision();
		// p_block.checkCollision();

		for (Collision collision : myCollisionList) {
			collision.checkGroupCollision();
		}

    }


    public InputHandler getMyHandler ()
    {
        return myHandler;
    }


    public List<FighterSprite> getFighters ()
    {
        return Collections.unmodifiableList(playerSprites);

    }

    @Override
    void transitionState ()
    {
        if(nextState != null)  
            myEngine.nextGame = this.nextState;
        else
            myEngine.nextGame = this.lastState;
        super.finish();
    }
}
