package PhysicsEngine;

import java.util.ArrayList;
import sprite.SpriteGroupTemplate;
import sprite.SpriteTemplate;

/**
 * This is the basic class for collision In this class we check whether there is
 * a collision If yes here we create an associated collision reaction type
 * 
 * @author Donghe
 */
public class Collision {
	private ArrayList<CollisionReaction> myReactionList = new ArrayList<CollisionReaction>();
	private SpriteGroupTemplate myGroupOne;
	private SpriteGroupTemplate myGroupTwo;

	private boolean onlyOneGroup;

	public Collision(SpriteGroupTemplate g1) {
		myGroupOne = g1;
		onlyOneGroup = true;
		setReactionList();
	}

	public Collision(SpriteGroupTemplate g1, SpriteGroupTemplate g2) {
		myGroupOne = g1;
		myGroupTwo = g2;
		onlyOneGroup = false;
		setReactionList();
	}

	private void setReactionList() {
		myReactionList.add(new CollisionReactionFriends());
		myReactionList.add(new CollisionReactionEnemy());
		myReactionList.add(new CollisionReactionNeutral());
	}

	public boolean isCollided(SpriteTemplate s1, SpriteTemplate s2) {
		if (s1.getX()>s2.getX()+s2.getWidth()) return false;
		if (s1.getX()+s1.getWidth()<s2.getX()) return false;
		if (s1.getY()>s2.getY()+s2.getHeight()) return false;
		if (s1.getY()+s1.getHeight()<s2.getY()) return false;
		return true;
	}

	public void checkGroupCollision() {
		if (onlyOneGroup) {
			checkInGroup();
		} else {
			checkBetweenGroups();
		}
	}

	private void checkInGroup() {
		for (int i = 0; i < myGroupOne.getSize(); i++) {
			for (int j = i + 1; j < myGroupOne.getSize(); j++) {
				checkEachCollision(myGroupOne.getSprite(i),
						myGroupOne.getSprite(j));
			}
		}
	}

	private void checkBetweenGroups() {
		for (SpriteTemplate s1 : myGroupOne.getSpriteArray()) {
			for (SpriteTemplate s2 : myGroupTwo.getSpriteArray()) {
				checkEachCollision(s1, s2);
			}
		}
	}

	private void checkEachCollision(SpriteTemplate s1, SpriteTemplate s2) {
		if (isCollided(s1, s2)) {
			for (CollisionReaction r : myReactionList) {
				if (r.isThisComposition(s1, s2)) {
					CollisionReaction reaction = r.createCollisionReaction(s1,
							s2);
					reaction.doThisReaction();
				}
			}
			// for debug
			System.out.println("collision");
		}
	}
}
