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

	private boolean isCollided(SpriteTemplate s1, SpriteTemplate s2) {
		if (s1.getX() > s2.getX() + s2.getWidth())
			return false;
		if (s1.getX() + s1.getWidth() < s2.getX())
			return false;
		if (s1.getY() > s2.getY() + s2.getHeight())
			return false;
		if (s1.getY() + s1.getHeight() < s2.getY())
			return false;
		return true;
	}

	private boolean isBodyCollided(SpriteTemplate s1, SpriteTemplate s2) {
		ArrayList<SpriteTemplate> body1 = getBodyPart(s1);
		ArrayList<SpriteTemplate> body2 = getBodyPart(s2);
		for (SpriteTemplate bodyPart1 : body1) {
			for (SpriteTemplate bodyPart2 : body2) {
				if (isCollided(bodyPart1, bodyPart2))
					return true;
			}
		}
		return false;

	}

	private ArrayList<SpriteTemplate> getBodyPart(SpriteTemplate s1) {
		ArrayList<SpriteTemplate> body1 = new ArrayList<SpriteTemplate>();
		if (FighterBody.class.isAssignableFrom(s1.getClass())) {
			for (NodeSprite n : ((FighterBody) s1).getBodyParts()) {
				body1.add(n);
			}
		} else {
			body1.add(s1);
		}
		return body1;
	}

	public void checkGroupCollision() {
		for (int i = 0; i < myGroup.getTeamNum(); i++) {
			ArrayList<SpriteTemplate> team1 = myGroup.getTeam(i);
			for (int j = i + 1; j < myGroup.getTeamNum(); j++) {
				ArrayList<SpriteTemplate> team2 = myGroup.getTeam(j);
				for (SpriteTemplate s1 : team1) {
					for (SpriteTemplate s2 : team2) {
						checkEachCollision(s1, s2);
					}
				}
				// checkEachCollision(myGroup.getSprite(i),
				// myGroup.getSprite(j));
			}
		}
	}

	private void checkEachCollision(SpriteTemplate s1, SpriteTemplate s2) {
		if (isBodyCollided(s1, s2)) {
			setCollisionStatus(s1, s2);
			for (CollisionKind r : myReactionList) {
				if (r.isThisKind(s1, s2)) {
					r.doThisReaction(s1, s2, myPhysicsEngine);
				}
			}
			// for debug
			// System.out.println("collision");
		}
	}

	private void setCollisionStatus(SpriteTemplate s1, SpriteTemplate s2) {
		// if (LimbSprite.class.isAssignableFrom(s1.getClass()))
		// if (FighterBody.class.isAssignableFrom(((LimbSprite) s1)
		// .getMyPointer().getClass()))
		// ((LimbSprite) s1).getMyPointer().setCollisionStatus(true);

		CollisionStatus status1 = s1.getCollisionStatus();
		// status1.setDown(true);
		// s1.setCollisionStatus(status1);
		CollisionStatus status2 = s2.getCollisionStatus();
		// status2.setDown(true);
		// s2.setCollisionStatus(status2);

		ArrayList<SpriteTemplate> body1 = getBodyPart(s1);
		ArrayList<SpriteTemplate> body2 = getBodyPart(s2);
		for (SpriteTemplate bodyPart1 : body1) {
			for (SpriteTemplate bodyPart2 : body2) {
				if (Math.abs(getRight(bodyPart1) - getLeft(bodyPart2)) < myCollisionFactor) {
					status1.setRight(true);
					status2.setLeft(true);
				}
				if (Math.abs(getRight(bodyPart2) - getLeft(bodyPart1)) < myCollisionFactor) {
					status2.setRight(true);
					status1.setLeft(true);
				}
				if (Math.abs(getUp(bodyPart1) - getDown(bodyPart2)) < myCollisionFactor) {
					status1.setUp(true);
					status2.setDown(true);
				}
				if (Math.abs(getUp(bodyPart2) - getDown(bodyPart1)) < myCollisionFactor) {
					status2.setUp(true);
					status1.setDown(true);
				}
			}
		}

		// s1.setCollisionStatus(true);
	}

	public SpriteGroupTemplate getCollisionGroup() {
		return myGroup;
	}

	public void setCollisionGroup(SpriteGroupTemplate group) {
		myGroup = group;
	}

	private double getRight(SpriteTemplate s) {
		return s.getX() + s.getWidth();
	}

	private double getLeft(SpriteTemplate s) {
		return s.getX();
	}

	private double getUp(SpriteTemplate s) {
		return s.getY();
	}

	private double getDown(SpriteTemplate s) {
		return s.getY() + s.getHeight();
	}

	// public void addSpriteAsNewTeam(SpriteTemplate sprite) {
	// myGroup.addSpriteTemplate(sprite);
	// }
	//
	// public void addSpriteToCertainTeam(SpriteTemplate sprite, int teamIndex){
	// myGroup.getTeamArray(teamIndex).add(sprite);
	// }

	// public void addSprite(SpriteGroupTemplate spriteGroup) {
	// myGroup.addSpriteGroup(spriteGroup);
	// }

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

	public void setCollisionFactor(double d) {
		myCollisionFactor = d;
	}
}
