package PhysicsEngine;

import java.util.ArrayList;
//import sprite.SpriteGroupTemplate;
//import sprite.SpriteTemplate;

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
	
	public Collision(SpriteGroupTemplate group,CollisionKind kind){
		myReactionList.add(kind);
		myGroup = group;
	}

	public Collision(SpriteGroupTemplate group, ArrayList<CollisionKind> KindList) {
		myGroup = group;
		myReactionList=KindList;
	}
	
	private boolean isCollided(SpriteTemplate s1, SpriteTemplate s2) {
		if (s1.getX()>s2.getX()+s2.getWidth()) return false;
		if (s1.getX()+s1.getWidth()<s2.getX()) return false;
		if (s1.getY()>s2.getY()+s2.getHeight()) return false;
		if (s1.getY()+s1.getHeight()<s2.getY()) return false;
		return true;
	}

	public void checkGroupCollision() {
		for (int i = 0; i < myGroup.getSize(); i++) {
			for (int j = i + 1; j < myGroup.getSize(); j++) {
				checkEachCollision(myGroup.getSprite(i),
						myGroup.getSprite(j));
			}
		}
	}

	private void checkEachCollision(SpriteTemplate s1, SpriteTemplate s2) {
		if (isCollided(s1, s2)) {
			s1.setCollisionStatus(true);
			s2.setCollisionStatus(true);
			for (CollisionKind r : myReactionList) {
				if (r.isThisKind(s1, s2)) {
					r.doThisReaction(s1,s2);	
				}
			}
			// for debug
			System.out.println("collision");
		}
	}
	
	public void addSprite(SpriteTemplate sprite){
		myGroup.addSpriteTemplate(sprite);
	}
	
	public void addSprite(SpriteGroupTemplate spriteGroup){
		myGroup.addSpriteGroup(spriteGroup);
	}
	
	public void addCollisionKind(CollisionKind kind){
		myReactionList.add(kind);
	}
	
	public void addCollisionKind(ArrayList<CollisionKind> kindList){
		myReactionList.addAll(kindList);
	}
	
	public void removeCollisionKind(CollisionKind kind){
		myReactionList.remove(kind);
	}
	
	public void removeCollisionKind(ArrayList<CollisionKind> kindList){
		myReactionList.removeAll(kindList);
	}
}
