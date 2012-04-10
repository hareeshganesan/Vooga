package PhysicsEngine;

import java.util.ArrayList;

import npsprite.SpriteTemplate;
import sprite.SpriteGroupTemplate;

/**
 * This is the basic class for collision In this class we check whether there is
 * a collision If yes here we create an associated collision reaction type
 * 
 * @author Donghe
 */
public class Collision {
    // the default maximum distance that we regard as a collision
    private final int SIZE_DEFAULT = 30;

    // two fighters and the reaction list
    // private SpriteTemplate myFighterSpriteOne;
    // private SpriteTemplate myFighterSpriteTwo;
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

    // public Collision(SpriteTemplate ps1, SpriteTemplate ps2) {
    // myFighterSpriteOne = ps1;
    // myFighterSpriteTwo = ps2;
    // setReactionList();
    // }

    private void setReactionList() {
        myReactionList.add(new CollisionReactionFriends());
        myReactionList.add(new CollisionReactionEnemy());
        myReactionList.add(new CollisionReactionNeutral());
    }

    // public boolean isCollided() {
    // // is just a rough calculaition, will improve later
    // if (Math.abs(myFighterSpriteOne.getX() - myFighterSpriteTwo.getX())
    // + Math.abs(myFighterSpriteOne.getY()
    // - myFighterSpriteTwo.getY()) < SIZE_DEFAULT)
    // return true;
    // return false;
    // }

    public boolean isCollided(SpriteTemplate s1, SpriteTemplate s2) {
        // is just a rough calculaition, will improve later
        if (Math.abs(s1.getX() - s2.getX()) + Math.abs(s1.getY() - s2.getY()) < SIZE_DEFAULT)
            return true;
        return false;
    }

    // public boolean isCoverThisPoint(SpriteTemplate fs, int x, int y) {
    // if (fs.getX() < x)
    // return false;
    // if (fs.getX() + fs.getWidth() > x)
    // return false;
    // if (fs.getY() < y)
    // return false;
    // if (fs.getY() + fs.getHealth() > y)
    // return false;
    // return true;
    // }

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

    // public void checkCollison() {
    // if (isCollided()) {
    // for (CollisionReaction r : myReactionList) {
    // if (r.isThisComposition(myFighterSpriteOne, myFighterSpriteTwo)) {
    // CollisionReaction reaction = r.createCollisionReaction(
    // myFighterSpriteOne, myFighterSpriteTwo);
    // reaction.doThisReaction();
    // }
    // }
    // // for debug
    // System.out.println("collision");
    // }
    //
    // }

}
