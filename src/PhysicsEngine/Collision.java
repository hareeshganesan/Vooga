package PhysicsEngine;

import java.util.ArrayList;
import npsprite.FighterBody;
import npsprite.GroupID;
import npsprite.NodeSprite;
import npsprite.SpriteGroupTemplate;
import npsprite.SpriteTemplate;

/**
 * This is a basic class for collision issue. When we create it for a game, we
 * need to pass in three arguments. 1. a SpriteGroupTemplate, which contains
 * several teams, and we just check collisions between sprites from different
 * teams. 2. a list of collisionKind, it will help us to find which kind of
 * collision it is, for example, the collision between sprites and blocks or
 * collision between two sprites from the same team. 3. a physicsEngine which we
 * need to set collision reaction
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

	/**
	 * check whether these two parts are collided
	 */
	private boolean isCollided(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
	    if ((!spriteOne.isActive())||(!spriteTwo.isActive())){
	        return false;
	    }
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

	/**
	 * check whether these two sprites(maybe they has some body parts) are
	 * collided
	 */
	private boolean isBodyCollided(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
		ArrayList<SpriteTemplate> bodyPartsOne = getBodyParts(spriteOne);
		ArrayList<SpriteTemplate> bodyPartsTwo = getBodyParts(spriteTwo);
		for (SpriteTemplate bodyPartOne : bodyPartsOne) {
			for (SpriteTemplate bodyPartTwo : bodyPartsTwo) {
				if (isCollided(bodyPartOne, bodyPartTwo)){
				    
		            setGroupCollisionStatus(bodyPartOne, bodyPartTwo);
		            for (CollisionKind kind : myReactionList) {
		                if (kind.isThisKind(spriteOne, spriteTwo)) {
		                    kind.doThisReaction(bodyPartOne, bodyPartTwo, myPhysicsEngine);
		                }
		            }
				    return true;
				}
			}
		}
		return false;
	}

	/**
	 * use in BodyFighter since it has some body parts, this class help us to
	 * get all body parts of this sprite
	 */
	private ArrayList<SpriteTemplate> getBodyParts(SpriteTemplate sprite) {
		ArrayList<SpriteTemplate> bodyParts = new ArrayList<SpriteTemplate>();
//		if (GroupID.isFighter(sprite.getGroupID())){
		if (FighterBody.class.isAssignableFrom(sprite.getClass())) {
		    
			for (NodeSprite n : ((FighterBody) sprite).getBodyParts()) {
				bodyParts.add(n);
			}
		} else {
			bodyParts.add(sprite);
		}
		return bodyParts;
	}

	/**
	 * check this whole group collision
	 */
	public void checkGroupCollision() {
		for (int i = 0; i < myGroup.getTeamNum(); i++) {
			ArrayList<SpriteTemplate> teamOne = myGroup.getTeam(i);
			for (int j = i + 1; j < myGroup.getTeamNum(); j++) {
				ArrayList<SpriteTemplate> teamTwo = myGroup.getTeam(j);
				checkTeamCollision(teamOne, teamTwo);
			}
		}
	}

	/**
	 * check the collision between two teams
	 */
	private void checkTeamCollision(ArrayList<SpriteTemplate> teamOne,
			ArrayList<SpriteTemplate> teamTwo) {
		for (SpriteTemplate spriteOne : teamOne) {
			for (SpriteTemplate spriteTwo : teamTwo) {
				checkEachCollision(spriteOne, spriteTwo);
			}
		}
	}

	/**
	 * check the collision between two sprites (including the case that it is a
	 * BodyFighter with some bodyparts in it)
	 */
	private void checkEachCollision(SpriteTemplate spriteOne,
			SpriteTemplate spriteTwo) {
//	    isBodyCollided(spriteOne,spriteTwo);
		if (isBodyCollided(spriteOne, spriteTwo)){
//
		    //TODO: refactor
            setGroupCollisionStatus(spriteOne, spriteTwo);
            for (CollisionKind kind : myReactionList) {
                if (kind.isThisKind(spriteOne, spriteTwo)) {
                    kind.doThisReaction(spriteOne, spriteTwo, myPhysicsEngine);
                }
            }
		}
			// for debug
			// System.out.println("collision");
//		}
	}

	/**
	 * set the group collision status, including the specific position
	 */
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

	/**
	 * set the horizontal collision status
	 */
	private void setHorizontalCollision(SpriteTemplate bodyPartOne,
			SpriteTemplate bodyPartTwo, CollisionStatus statusOne,
			CollisionStatus statusTwo) {
		if (Math.abs(getUp(bodyPartOne) - getDown(bodyPartTwo)) < myCollisionFactor) {
			statusOne.setUp(true);
			statusTwo.setDown(true);
		}
	}

	/**
	 * set the vertical collision status
	 */
	private void setverticalCollision(SpriteTemplate bodyPartOne,
			SpriteTemplate bodyPartTwo, CollisionStatus statusOne,
			CollisionStatus statusTwo) {
		if (Math.abs(getRight(bodyPartOne) - getLeft(bodyPartTwo)) < myCollisionFactor) {
			statusOne.setRight(true);
			statusTwo.setLeft(true);
		}
	}

	/**
	 * @return the group in this collision
	 */
	public SpriteGroupTemplate getCollisionGroup() {
		return myGroup;
	}

	/**
	 * set the group in this collision
	 * 
	 * @param group
	 *            the group needs to be set into this collision engine
	 */
	public void setCollisionGroup(SpriteGroupTemplate group) {
		myGroup = group;
	}

	/**
	 * the right bound of this sprite
	 */
	private double getRight(SpriteTemplate sprite) {
		return sprite.getX() + sprite.getWidth();
	}

	/**
	 * the left bound of this sprite
	 */
	private double getLeft(SpriteTemplate sprite) {
		return sprite.getX();
	}

	/**
	 * the up bound of this sprite
	 */
	private double getUp(SpriteTemplate sprite) {
		return sprite.getY();
	}

	/**
	 * the down bound of this sprite
	 */
	private double getDown(SpriteTemplate sprite) {
		return sprite.getY() + sprite.getHeight();
	}

	/**
	 * add new collisionKind into this collision engine
	 * 
	 * @param kind
	 *            the new collision kind
	 */
	public void addCollisionKind(CollisionKind kind) {
		myReactionList.add(kind);
	}

	/**
	 * add a list of new collisionKinds into this collision engine
	 * 
	 * @param kindList
	 *            a list of new collisionKinds
	 */
	public void addCollisionKind(ArrayList<CollisionKind> kindList) {
		myReactionList.addAll(kindList);
	}

	/**
	 * remove the collisionKind from this collision engine
	 * 
	 * @param kind
	 *            the collision kind that needs to be removed
	 */
	public void removeCollisionKind(CollisionKind kind) {
		myReactionList.remove(kind);
	}

	/**
	 * remove a list of collisionKinds from this collision engine
	 * 
	 * @param kindList
	 *            a list of collisionKinds that need to be removed
	 */
	public void removeCollisionKind(ArrayList<CollisionKind> kindList) {
		myReactionList.removeAll(kindList);
	}

	/**
	 * use for the defination of collision position if the distance between two
	 * edges is smaller than this factor we regard they collide at this edge
	 * 
	 * @param factor
	 *            the distance as a standard
	 */
	public void setCollisionFactor(double factor) {
		myCollisionFactor = factor;
	}
}
