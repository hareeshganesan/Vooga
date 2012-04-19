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
import sprite.PlatformBlock;
import sprite.SpriteGroupTemplate;
import camera.Camera;
import camera.CameraBackground;
import camera.FollowCamera;
import PhysicsEngine.Collision;
import PhysicsEngine.CollisionKind;
import PhysicsEngine.CollisionKindEnemy;
import PhysicsEngine.CollisionKindFriends;
import PhysicsEngine.CollisionKindNeutral;
import PhysicsEngine.Reaction;
import PhysicsEngine.ReactionPunch;
import PhysicsEngine.ReactionPush;
import PhysicsEngine.ReactionRebound;
import action.QuitAction;
import ai.AIAgent;

public class CombatInstance extends GameState {
	private String DEFAULT_IMAGE = "resources/title.png";

	// Engines
	MainGame myEngine;
	InputHandler myHandler;
	Camera camera;

	// Sprites
	ArrayList<FighterSprite> playerSprites;
	ArrayList<PlatformBlock> platform;

	// Collision
	private Collision myCollision;

	CameraBackground bg;

	public CombatInstance(MainGame engine) {
		super(engine);
		myEngine = engine;
		myHandler = new InputHandler();
		camera = new FollowCamera();
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
			playerSprites = lof.createFighters();
			platform = lof.createBlocks();
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

		SpriteGroupTemplate groupSprite = new SpriteGroupTemplate("team");
		groupSprite.addFighterSpriteArray(playerSprites);
		groupSprite.addPlatformBlockArray(platform);

		ArrayList<CollisionKind> CollisionkindList = new ArrayList<CollisionKind>();
		ArrayList<Reaction> reaction1 = new ArrayList<Reaction>();
		reaction1.add(new ReactionPush());
		ArrayList<Reaction> reaction2 = new ArrayList<Reaction>();
		ArrayList<Reaction> reaction3 = new ArrayList<Reaction>();
		reaction3.add(new ReactionRebound(20.0));
		CollisionkindList.add(new CollisionKindFriends(reaction1));
		CollisionkindList.add(new CollisionKindEnemy(reaction2));
		CollisionkindList.add(new CollisionKindNeutral(reaction3));

		myCollision = new Collision(groupSprite, CollisionkindList);

	}

	@Override
	public void render(Graphics2D pen) {
		camera.render(pen, bg);
		bg.render(pen, camera, camera.getX(), camera.getY(), camera.getX(),
				camera.getY(), camera.getHeight(), camera.getWidth());
		// bg.render(pen);

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
		camera.update(playerSprites, bg);
		myHandler.update(elapsedTime, myEngine);
		bg.update(elapsedTime);

		for (FighterSprite sprite : playerSprites) {
			if (AIAgent.class.isAssignableFrom(sprite.getClass())) {
				AIAgent ai = (AIAgent) sprite;
				ai.calculateLocation(elapsedTime);
			}
		}

		myCollision.checkGroupCollision();

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
	public void transitionState() {
		if (nextState != null)
			myEngine.nextGame = this.nextState;
		else
			myEngine.nextGame = this.lastState;
		super.finish();
	}
}
