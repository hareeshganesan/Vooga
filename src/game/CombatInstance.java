package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import org.jdom.JDOMException;
import sprite.FighterSprite;
import sprite.GeneralSpriteCollision;
import sprite.PlatformBlock;
import PhysicsEngine.BasicPhysicsEngine;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;


public class CombatInstance extends GameObject
{
    private String DEFAULT_IMAGE = "resources/title.png";

    //Engines
    GameEngine myEngine;
    InputHandler myHandler;
    BasicPhysicsEngine physics;

    //Sprites
    ArrayList<FighterSprite> playerSprites;
    ArrayList<PlatformBlock> platform;
    GeneralSpriteCollision temp;
    GeneralSpriteCollision p_block;

    Background bg;


    public CombatInstance (GameEngine engine)
    {
        super(engine);
        myEngine = engine;
        physics = new BasicPhysicsEngine();
        myHandler = new InputHandler();
    }


    @Override
    public void initResources ()
    {
        LevelObjectsFactory lof = new LevelObjectsFactory(this);
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("src/resources"));
        fc.setApproveButtonText("load game file");
        int returnVal = fc.showOpenDialog(null);

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
        SpriteGroup p1 = new SpriteGroup("p1");
        p1.add(playerSprites.get(0));
        p1.setBackground(bg);
        SpriteGroup p2 = new SpriteGroup("p2");
        p2.add(playerSprites.get(1));
        p2.setBackground(bg);

        temp = new GeneralSpriteCollision();
        temp.setCollisionGroup(p1, p2);

        SpriteGroup b1 = new SpriteGroup("b");
        for (PlatformBlock p : platform)
        {
            b1.add(p);
        }
        SpriteGroup ps = new SpriteGroup("players");
        for (FighterSprite f : playerSprites)
        {
            ps.add(f);
        }
        ps.setBackground(bg);
        b1.setBackground(bg);
        p_block = new GeneralSpriteCollision();
        p_block.setCollisionGroup(ps, b1);

    }


    @Override
    public void render (Graphics2D pen)
    {
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
        myHandler.update(myEngine);
        bg.update(elapsedTime);
        for (FighterSprite sprite : playerSprites)
            physics.update(sprite, this, elapsedTime);
        for (PlatformBlock pb : platform)
        {
            pb.update(elapsedTime);
        }

        temp.checkCollision();
        p_block.checkCollision();

    }


    public InputHandler getMyHandler ()
    {
        return myHandler;
    }

}
