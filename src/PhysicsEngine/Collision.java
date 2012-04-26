package PhysicsEngine;

import java.util.ArrayList;
import npsprite.FighterBody;
import npsprite.NodeSprite;
import npsprite.SpriteGroupTemplate;
import npsprite.SpriteTemplate;

/**
 * This is the basic class for collision In this class we check whether there is
 * a collision If yes here we create an associated collision reaction type
 * 
 * @author Donghe
 */
public class Collision {
	private ArrayList<CollisionKind> myReactionList = new ArrayList<CollisionKind>();
	private SpriteGroupTemplate myGroup;
	private PhysicsEngine myPhysicsEngine;
	private double myCollisionFactor = 3.0;

	public Collision(SpriteGroupTemplate group, CollisionKind kind,
			PhysicsEngine physicsEngine) {
		myGroup = group;
		myReactionList.add(kind);
		myPhysicsEngine = physicsEngine;
	}

	public Collision(SpriteGroupTemplate group,
			ArrayList<CollisionKind> KindList, PhysicsEngine physicsEngine) {
		myGroup = group;
		myReactionList = KindList;
		myPhysicsEngine = physicsEngine;
	}

	private boolean isCollided(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
		if (getLeft(spriteOne) > getRight(spriteTwo))
			return false;
		if (getRight(spriteOne) < getLeft(spriteTwo))
			return false;
		if (getUp(spriteOne) > getDown(spriteTwo))
			return false;
		if (getDown(spriteOne) < getUp(spriteTwo))
			return false;
		return true;
	}

	private boolean isBodyCollided(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
		ArrayList<SpriteTemplate> bodyPartsOne = getBodyParts(spriteOne);
		ArrayList<SpriteTemplate> bodyPartsTwo = getBodyParts(spriteTwo);
		for (SpriteTemplate bodyPartOne : bodyPartsOne) {
			for (SpriteTemplate bodyPartTwo : bodyPartsTwo) {
				if (isCollided(bodyPartOne, bodyPartTwo))
					return true;
			}
		}
		return false;
	}

	private ArrayList<SpriteTemplate> getBodyParts(SpriteTemplate sprite) {
		ArrayList<SpriteTemplate> bodyParts = new ArrayList<SpriteTemplate>();
		if (FighterBody.class.isAssignableFrom(sprite.getClass())) {
			for (NodeSprite n : ((FighterBody) sprite).getBodyParts()) {
				bodyParts.add(n);
			}
		} else {
			bodyParts.add(sprite);
		}
		return bodyParts;
	}

	public void checkGroupCollision() {
		for (int i = 0; i < myGroup.getTeamNum(); i++) {
			ArrayList<SpriteTemplate> teamOne = myGroup.getTeam(i);
			for (int j = i + 1; j < myGroup.getTeamNum(); j++) {
				ArrayList<SpriteTemplate> teamTwo = myGroup.getTeam(j);
				checkTeamCollision(teamOne, teamTwo);
			}
		}
	}

	private void checkTeamCollision(ArrayList<SpriteTemplate> teamOne,
			ArrayList<SpriteTemplate> teamTwo) {
		for (SpriteTemplate spriteOne : teamOne) {
			for (SpriteTemplate spriteTwo : teamTwo) {
				checkEachCollision(spriteOne, spriteTwo);
			}
		}
	}

	private void checkEachCollision(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
		if (isBodyCollided(spriteOne, spriteTwo)) {
			setGroupCollisionStatus(spriteOne, spriteTwo);
			for (CollisionKind kind : myReactionList) {
				if (kind.isThisKind(spriteOne, spriteTwo)) {
					kind.doThisReaction(spriteOne, spriteTwo, myPhysicsEngine);
				}
			}
			// for debug
			// System.out.println("collision");
		}
	}

	private void setGroupCollisionStatus(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
		CollisionStatus statusOne = spriteOne.getCollisionStatus();
		CollisionStatus statusTwo = spriteTwo.getCollisionStatus();
		ArrayList<SpriteTemplate> bodyPartsOne = getBodyParts(spriteOne);
		ArrayList<SpriteTemplate> bodyPartsTwo = getBodyParts(spriteTwo);
		for (SpriteTemplate bodyPartOne : bodyPartsOne) {
			for (SpriteTemplate bodyPartTwo : bodyPartsTwo) {
				setHorizontalCollision(bodyPartOne, bodyPartTwo, statusOne,
						statusTwo);
				setHorizontalCollision(bodyPartTwo, bodyPartOne, statusTwo,
						statusOne);
				setverticalCollision(bodyPartOne, bodyPartTwo, statusOne,
						statusTwo);
				setverticalCollision(bodyPartTwo, bodyPartOne, statusTwo,
						statusOne);
			}
		}
	}

	private void setHorizontalCollision(SpriteTemplate bodyPartOne,
			SpriteTemplate bodyPartTwo, CollisionStatus statusOne,
			CollisionStatus statusTwo) {
		if (Math.abs(getUp(bodyPartOne) - getDown(bodyPartTwo)) < myCollisionFactor) {
			statusOne.setUp(true);
			statusTwo.setDown(true);
		}
	}

	private void setverticalCollision(SpriteTemplate bodyPartOne,
			SpriteTemplate bodyPartTwo, CollisionStatus statusOne,
			CollisionStatus statusTwo) {
		if (Math.abs(getRight(bodyPartOne) - getLeft(bodyPartTwo)) < myCollisionFactor) {
			statusOne.setRight(true);
			statusTwo.setLeft(true);
		}
	}

	public SpriteGroupTemplate getCollisionGroup() {
		return myGroup;
	}

	public void setCollisionGroup(SpriteGroupTemplate group) {
		myGroup = group;
	}

	private double getRight(SpriteTemplate sprite) {
		return sprite.getX() + sprite.getWidth();
	}

	private double getLeft(SpriteTemplate sprite) {
		return sprite.getX();
	}

	private double getUp(SpriteTemplate sprite) {
		return sprite.getY();
	}

	private double getDown(SpriteTemplate sprite) {
		return sprite.getY() + sprite.getHeight();
	}

	public void addCollisionKind(CollisionKind kind) {
		myReactionList.add(kind);
	}

	public void addCollisionKind(ArrayList<CollisionKind> kindList) {
		myReactionList.addAll(kindList);
	}

	public void removeCollisionKind(CollisionKind kind) {
		myReactionList.remove(kind);
	}

	public void removeCollisionKind(ArrayList<CollisionKind> kindList) {
		myReactionList.removeAll(kindList);
	}

	public void setCollisionFactor(double factor) {
		myCollisionFactor = factor;
	}
}
