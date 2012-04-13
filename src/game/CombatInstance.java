package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
import sprite.PlatformBlock;
import sprite.SpriteGroupTemplate;
import PhysicsEngine.Collision;
import action.QuitAction;
import ai.AIAgent;
import camera.Camera;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.background.ImageBackground;

public class CombatInstance extends GameState {
	private String DEFAULT_IMAGE = "resources/title.png";

	// Engines just for test this time, will change back later
	public static MainGame myEngine;

	InputHandler myHandler;
	Camera camera;

	// Sprites
	ArrayList<FighterSprite> playerSprites;
	ArrayList<PlatformBlock> platform;

	ArrayList<Collision> myCollisionList = new ArrayList<Collision>();

	Background bg;

	public CombatInstance(MainGame engine) {
		super(engine);
		myEngine = engine;
		myHandler = new InputHandler();
		camera = new Camera(new Point(544 / 2, 544 / 2),
				new Rectangle(100, 100));
	}

	@Override
	public void initResources() {
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

		nextState = (GameState) myEngine.getGame(myEngine.getMain());

		GameFont font = fontManager.getFont(getImage("resources/font.png"));
		BufferedImage HPimage = getImage("resources/frame.png");
		try {
			playerSprites = lof.createFighters();
			platform = lof.createBlocks();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		String back = lof.getBackground();
		if (back == null) {
			back = DEFAULT_IMAGE;
		}
		BufferedImage b = getImage(back);
		bg = new ImageBackground(b);

		SpriteGroupTemplate groupPlayer = new SpriteGroupTemplate("team1");
		groupPlayer.addFighterSpriteArray(playerSprites);

		SpriteGroupTemplate groupBlock = new SpriteGroupTemplate("team2");
		groupBlock.addPlatformBlockArray(platform);

		myCollisionList.add(new Collision(groupPlayer));
		myCollisionList.add(new Collision(groupPlayer, groupBlock));

	}

	@Override
	public void render(Graphics2D pen) {
		camera.render(pen);
		
		bg.render(pen);
		for (FighterSprite sprite : playerSprites)
			sprite.render(pen);
		for (PlatformBlock pb : platform) {
			pb.render(pen);
		}
	}

	@Override
	public void update(long elapsedTime) {
		myHandler.update(elapsedTime, myEngine);
		camera.update(playerSprites);
		
		bg.setToCenter(camera.getX(), camera.getY(), camera.getHeight(),
				camera.getWidth());
		myHandler.update(elapsedTime, myEngine);
		bg.update(elapsedTime);

		for(FighterSprite sprite : playerSprites){
		    if(sprite.getSpriteKind().contains("AI")){
		        AIAgent ai = (AIAgent) sprite;
		        ai.calculateLocation(elapsedTime);
		    }
		        
		}
		for (Collision collision : myCollisionList) {
			collision.checkGroupCollision();
		}

		for (FighterSprite sprite : playerSprites) {
			sprite.update(elapsedTime);
		}

		for (PlatformBlock pb : platform)
			pb.update(elapsedTime);


	}

	public InputHandler getMyHandler() {
		return myHandler;
	}

	public List<FighterSprite> getFighters() {
		return Collections.unmodifiableList(playerSprites);

	}

    @Override
    public void transitionState ()
    {
        if(nextState != null)  
            myEngine.nextGame = this.nextState;
        else
            myEngine.nextGame = this.lastState;
        super.finish();
    }
}
