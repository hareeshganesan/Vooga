package PhysicsEngine;

import java.util.ArrayList;

import sprite.FighterSprite;
import sprite.SpriteTemplate;

/**
 * This is the basic class for collision
 * In this class we check whether there is a collision
 * If yes here we create an associated collision reaction type
 * 
 * @author Donghe
 */
public class Collision {
	//the default maximum distance that we regard as a collision
	private final int SIZE_DEFAULT = 30;
	
	//two fighters and the reaction list
	private SpriteTemplate myFighterSpriteOne;
	private SpriteTemplate myFighterSpriteTwo;
	private ArrayList<CollisionReaction> myReactionList = new ArrayList<CollisionReaction>();

	
	
	public Collision(SpriteTemplate ps1, SpriteTemplate ps2) {
		myFighterSpriteOne = ps1;
		myFighterSpriteTwo = ps2;
		myReactionList.add(new CollisionReactionFriends());
		myReactionList.add(new CollisionReactionEnemy());
		myReactionList.add(new CollisionReactionNeutral());
	}

	
	
	public boolean isCollided() {
		//is just a rough calculaition, will improve later
		if (Math.abs(myFighterSpriteOne.getX() - myFighterSpriteTwo.getX())
				+ Math.abs(myFighterSpriteOne.getY()
						- myFighterSpriteTwo.getY()) < SIZE_DEFAULT)
			return true;
		return false;
	}

//	public boolean isCoverThisPoint(SpriteTemplate fs, int x, int y) {
//		if (fs.getX() < x)
//			return false;
//		if (fs.getX() + fs.getWidth() > x)
//			return false;
//		if (fs.getY() < y)
//			return false;
//		if (fs.getY() + fs.getHealth() > y)
//			return false;
//		return true;
//	}

	public void checkCollison() {
		if (isCollided()) {
			for (CollisionReaction r : myReactionList) {
				if (r.isThisComposition(myFighterSpriteOne, myFighterSpriteTwo)) {
					CollisionReaction reaction = r.createCollisionReaction(
							myFighterSpriteOne, myFighterSpriteTwo);
					reaction.doThisReaction();
				}
			}
			//for debug
			System.out.println("collision");
		}

	}
}
