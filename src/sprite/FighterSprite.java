package sprite;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
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
    private int MAX_HEALTH = 50;
    private double DEFAULT_SPEED = 0.1;
    private Point2D moveBy;
    

    public Point2D getMoveBy() {
		return moveBy;
	}


	private HealthDisplay myDisplay;
    private List<WeaponSprite> myWeapons;

    private HashMap<Integer, Integer> keyMap = new HashMap<Integer, Integer>();

    

    // values for groupID will be selectable in spriteValues
    public FighterSprite (String name, HealthDisplay display, int groupID)
    {
        myName = name;
        myHealth = MAX_HEALTH;
        mySpeed = DEFAULT_SPEED;
        myDisplay = display;

        myDisplay.setStat(myName, myHealth);
        myWeapons = new ArrayList<WeaponSprite>();
        /**
         * TODO: remove the mapping commented code below
         */
//
//        // default mapping, maybe be moved into input handler later
//        keyMap.put(KeyEvent.VK_UP, KeyEvent.VK_UP);
//        keyMap.put(KeyEvent.VK_DOWN, KeyEvent.VK_DOWN);
//        keyMap.put(KeyEvent.VK_LEFT, KeyEvent.VK_LEFT);
//        keyMap.put(KeyEvent.VK_RIGHT, KeyEvent.VK_RIGHT);

        this.setID(groupID);
        moveBy = new Point2D.Double();
        
        //set this direction to whichever direction the image originally faces
        super.setDirection(SpriteValues.RIGHT);
    }

    public Point2D getCurrentLocation(){
        return new Point2D.Double(getX(), getY());
    }

    public void addWeapon (WeaponSprite w)
    {
        myWeapons.add(w);
    }


    public String getName ()
    {
        return myName;
    }


    //TODO: make these set fxns safer (check that a legit value is being passed in)
    public void setMaxHealth (int change)
    {
        MAX_HEALTH = change;
        myHealth = MAX_HEALTH;
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



    public void setNextLocation (Point2D nextLocation)
    {
        this.moveBy = nextLocation;
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
    protected Point2D confineBounds (double dx, double dy)
    {
        if (!this.isOnScreen())
        {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY());
        }
        if((getX()+dx)<0)
            dx = getX();
        if((getX()+getWidth()/2+dx)>getBackground().getWidth())
            dx = getBackground().getWidth()-getX()-getWidth();
        if((getY()+dy)<0)
            dy = getY();
        if((getY()+getHeight()/2+dy)>getBackground().getHeight())
            dy = getBackground().getHeight()-getY()-getHeight();
        return new Point2D.Double(dx, dy);
    }


    public void move (double dx, double dy)
    {

        Point2D finaldelta = confineBounds(dx, dy);
        dx = finaldelta.getX();
        dy = finaldelta.getY();
        super.move(dx, dy);
        if (dx < 0)
        {
            changeDirection(SpriteValues.LEFT);
        }
        else if (dx > 0)
        {
            changeDirection(SpriteValues.RIGHT);
        }

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
        
        move(moveBy.getX(),moveBy.getY());
        super.update(elapsedTime);
    }
}
