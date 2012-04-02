package sprite;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.AdvanceSprite;

/*
 * @author Wendy, Helena, Hareesh
 */
public class FighterSprite extends SpriteTemplate {
    private String myName;
    private int myHealth;
    private double mySpeed;

    // defaults
    private int MAX_HEALTH = 50;
    private double DEFAULT_SPEED = 0.1;
    //

    private HealthDisplay myDisplay;
    private List<WeaponSprite> myWeapons;

    private HashMap<Integer, Integer> keyMap = new HashMap<Integer, Integer>();

    // values for groupID will be selectable in spriteValues
    public FighterSprite(String name, HealthDisplay display, int groupID) {
        myName = name;
        myHealth = MAX_HEALTH;
        mySpeed = DEFAULT_SPEED;
        myDisplay = display;

        myDisplay.setStat(myName, myHealth);
        myWeapons = new ArrayList<WeaponSprite>();

        // default mapping, maybe be moved into input handler later
        keyMap.put(KeyEvent.VK_UP, KeyEvent.VK_UP);
        keyMap.put(KeyEvent.VK_DOWN, KeyEvent.VK_DOWN);
        keyMap.put(KeyEvent.VK_LEFT, KeyEvent.VK_LEFT);
        keyMap.put(KeyEvent.VK_RIGHT, KeyEvent.VK_RIGHT);

        this.setID(groupID);
        
      //set this direction to whichever direction the image originally faces
        super.setDirection(SpriteValues.RIGHT); 
    }

    public void addWeapon(WeaponSprite w) {
        myWeapons.add(w);
    }

    public String getName() {
        return myName;
    }

    //TODO: make these set fxns safer (check that a legit value is being passed in)
    public void setMaxHealth(int change) {
        MAX_HEALTH = change;
        myHealth = MAX_HEALTH;
        myDisplay.setStat(myName, MAX_HEALTH);
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public void addHealth(int h) {
        myHealth += h;
        wrapHealth();
    }

    public int getHealth() {
        return myHealth;
    }

    public void setSpeed(double speed) {
        mySpeed = speed;
    }

    public double getSpeed() {
        return mySpeed;
    }
    

    /**
     * should be used for input engine and level editor
     */
    public void setKeyMap(int defaultKey, int newKey) {
        if (keyMap.containsValue(defaultKey)) {
            for (Entry<Integer, Integer> s : keyMap.entrySet()) {
                if (s.getValue() == defaultKey) {
                    keyMap.remove(s.getKey());
                }
            }
        }
        keyMap.put(newKey, defaultKey);
    }

    /**
     * Should be used for input engine
     */
    public int getDefaultKey(int key) {
        return keyMap.get(key);
    }

    // should only be called if collision occurred
    // default is to stay at old position, override to have new actions
    public void collisionAction(int otherGroupID) {
        // TODO: if groupID is not same as my group id, do stuff like health
        // reduction.
        // if groupID is same, then...something (depends on if group memebers
        // can hurt each other or not)
        this.forceX(this.getOldX());
        this.forceY(this.getOldY() - 1);
    }

    protected void animationChanged(int oldStat, int oldDir, int status,
            int direction) {
        if ((direction == SpriteValues.LEFT)
                || (direction == SpriteValues.RIGHT)) {
            if (this.getImages()!=null){
            flipImagesHoriz();
            }
        }
    }

    // DOES THIS NEED TO BE PUBLIC?
    public void changeDirection(int dir) {
        if (super.getDirection() != dir) {
            super.setDirection(dir);
            for (WeaponSprite w : myWeapons) {
                w.setDirection(dir);
            }
        }
    }

    private void wrapHealth() {
        if (myHealth > MAX_HEALTH) {
            myHealth = MAX_HEALTH;
        }
        if (myHealth < 0) {
            myHealth = 0;
        }
    }

    // TODO: THIS MAY CHANGE WITH CHANGING COLLISIONCHECKER
    protected void confineBounds() {
        if (!this.isOnScreen()) {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY());
        }
    }

    public void move(double dx, double dy) {
        super.move(dx, dy);
        if (dx < 0) {
            changeDirection(SpriteValues.LEFT);
        } else if (dx > 0) {
            changeDirection(SpriteValues.RIGHT);
        }
//        if (dy < 0) {
//            changeDirection(SpriteValues.UP);
//        } else if (dy > 0) {
//            changeDirection(SpriteValues.DOWN);
//        }

        confineBounds();
        moveWeapons(dx, dy);
    }

    // notifies observer weapons that they need to move dx, dy
    private void moveWeapons(double dx, double dy) {
        for (WeaponSprite w : myWeapons) {
            w.updateLocation(dx, dy);
        }
    }

    public void render(Graphics2D g) {
        myDisplay.render(g);
        super.render(g);
    }

    public void update(long elapsedTime) {
        if (myHealth <= 0) {
            this.setActive(false); // PARENT WILL NEED TO CHECK FOR ACTIVE
        }
        myDisplay.update(elapsedTime, myHealth);

        super.update(elapsedTime);
    }
}
