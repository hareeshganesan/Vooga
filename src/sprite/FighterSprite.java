package sprite;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
 * @author Wendy, Helena, Hareesh
 */
public class FighterSprite extends SpriteTemplate
{

    private String myName;
    private int myHealth;
    private double mySpeed;

    // defaults
    private int MIN_HEALTH = 5;
    private int MAX_HEALTH = 50;
    private double DEFAULT_SPEED = 0.1;

    private HealthDisplay myDisplay;
    private List<WeaponSprite> myWeapons;

    private HashMap<Integer, Integer> keyMap = new HashMap<Integer, Integer>();


    // values for groupID will be selectable in spriteValues
    //TODO: figure out how groupIDs will work, especially with collisions
    public FighterSprite (String name, HealthDisplay display, int groupID)
    {
        myName = name;
        myHealth = MAX_HEALTH;
        mySpeed = DEFAULT_SPEED;
        myDisplay = display;

        myDisplay.setStat(myName, myHealth);
        myWeapons = new ArrayList<WeaponSprite>();

        this.setID(groupID);

        //set this direction to whichever direction the image originally faces
        super.setDirection(SpriteValues.RIGHT);
    }


    public void addWeapon (WeaponSprite w)
    {
        myWeapons.add(w);
    }

    public void removeWeapon(WeaponSprite w) {
        myWeapons.remove(w);
    }


    public String getName ()
    {
        return myName;
    }


    /**
     * Changes maximum health to @param change
     * Resets to full health
     */
    public void setMaxHealth (int change)
    {
        if (change<=MIN_HEALTH){
            MAX_HEALTH=MIN_HEALTH;
            myHealth=MAX_HEALTH;
        }
        else{
        MAX_HEALTH = change;
        myHealth = MAX_HEALTH;
        }
        myDisplay.setStat(myName, MAX_HEALTH);
    }


    public int getMaxHealth ()
    {
        return MAX_HEALTH;
    }


    public void addHealth (int h)
    {
        myHealth += h;
        wrapHealth();
    }


    public int getHealth ()
    {
        return myHealth;
    }


    public void setSpeed (double speed)
    {
        mySpeed = speed;
    }


    public double getSpeed ()
    {
        return mySpeed;
    }


    /**
     * should only be called if collision occurred default is to stay at old
     * position, override to have new actions
     */
    public void collisionAction (int otherGroupID)
    {
        // TODO: if groupID is not same as my group id, do stuff like health
        // reduction.
        // if groupID is same, then...something (depends on if group memebers
        // can hurt each other or not)
        this.forceX(this.getOldX());
        this.forceY(this.getOldY() - 1);
    }


    protected void animationChanged (int oldStat,
                                     int oldDir,
                                     int status,
                                     int direction)
    {
        if ((direction == SpriteValues.LEFT) ||
            (direction == SpriteValues.RIGHT))
        {
            if (this.getImages() != null)
            {
                flipImagesHoriz();
            }
        }
    }


    // DOES THIS NEED TO BE PUBLIC?
    private void changeDirection (int dir)
    {
        if (super.getDirection() != dir)
        {
            super.setDirection(dir);
            for (WeaponSprite w : myWeapons)
            {
                w.setDirection(dir);
            }
        }
    }


    private void wrapHealth ()
    {
        if (myHealth > MAX_HEALTH)
        {
            myHealth = MAX_HEALTH;
        }
        if (myHealth < 0)
        {
            myHealth = 0;
        }
    }


    // TODO: THIS MAY CHANGE WITH CHANGING COLLISIONCHECKER
    protected void confineBounds ()
    {
        if (!this.isOnScreen())
        {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY());
        }
    }


    public void move (double dx, double dy)
    {
        super.move(dx, dy);
        if (dx < 0)
        {
            changeDirection(SpriteValues.LEFT);
        }
        else if (dx > 0)
        {
            changeDirection(SpriteValues.RIGHT);
        }

        confineBounds();
        moveWeapons(dx, dy);
    }


    // notifies observer weapons that they need to move dx, dy
    private void moveWeapons (double dx, double dy)
    {
        for (WeaponSprite w : myWeapons)
        {
            w.updateLocation(dx, dy);
        }
    }


    public void render (Graphics2D g)
    {
        myDisplay.render(g);
        super.render(g);
    }


    public void update (long elapsedTime)
    {
        if (myHealth <= 0)
        {
            this.setActive(false); // PARENT WILL NEED TO CHECK FOR ACTIVE
        }
        myDisplay.update(elapsedTime, myHealth);

        super.update(elapsedTime);
    }

}
