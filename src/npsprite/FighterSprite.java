package npsprite;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sprite.HealthDisplay;
import sprite.SpriteValues;


/*
 * Note: speed is used to determine distance travelled upon keypresses. getSpeed is used for this purpose
 * To set actual sprite speed (moving w/o keypresses), call the golden t functions
 * @author Wendy, Helena, Hareesh
 */

public class FighterSprite extends SpriteTemplate {

    private String myName;
    private int myHealth;

    // defaults
    private double DEFAULT_SPEED = 0.5;
    private Point2D moveBy;

    private HealthDisplay myDisplay;
    private List<NonPlayerSprite> myWeapons;

    

    // values for groupID will be selectable in spriteValues
    // TODO: figure out how groupIDs will work, especially with collisions
    public FighterSprite(String name, HealthDisplay display, int groupID) {
        myName = name;
//        myHealth = MAX_HEALTH;
        super.setDefaultSpeed(DEFAULT_SPEED);
//        super.resetSpeed();
        myDisplay = display;

        myDisplay.setStat(myName, myHealth);
        myWeapons = new ArrayList<NonPlayerSprite>();

        this.setID(groupID);
        moveBy = new Point2D.Double();
        
        //set this direction to whichever direction the image originally faces
        super.setDirection(SpriteValues.RIGHT);
    }
    public void setMaxHealth(int change){
        super.setMaxHealth(change);
        myDisplay.setStat(myName, MAX_HEALTH);
    }

    public Point2D getCurrentLocation(){
        return new Point2D.Double(getX()+moveBy.getX(), getY()+moveBy.getY());
    }

    public void addWeapon(NonPlayerSprite child) {
        myWeapons.add(child);
        child.setID(this.getID());
    }

    public void removeWeapon(NonPlayerSprite child) {
        myWeapons.remove(child);
    }


    public String getName ()
    {
        return myName;
    }
    /**
     * should only be called if collision occurred default is to stay at old
     * position, override to have new actions
     */
    public void collisionAction(SpriteTemplate otherSprite) {
        if (otherSprite != this.getID()) {
            this.forceX(this.getOldX());
            this.forceY(this.getOldY() - 1);
        }
    }

    protected void animationChanged(int oldStat, int oldDir, int status,
            int direction) {
        //TODO: SEE SPRITETREE
    }



    public void setNextLocationIncrement (Point2D nextLocation)
    {
        
        this.moveBy = new Point2D.Double(nextLocation.getX(),nextLocation.getY());
    }

    // DOES THIS NEED TO BE PUBLIC?
    private void changeDirection(int dir) {
        if (super.getDirection() != dir) {
            super.setDirection(dir);
            for (NonPlayerSprite w : myWeapons) {
                w.setDirection(dir);
            }
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
        if((getX()-dx)<0)
            dx = -getX();
        if((getX()+getWidth()+dx)>getBackground().getWidth())
            dx -= getY()+getHeight();
        if((getY()+getHeight()+dy)>getBackground().getHeight())
            dy -= getY()+getHeight();
        if(getY()+dy<0)
            dy = 0;
        return new Point2D.Double(dx, dy);
    }


    public void move (double dx, double dy)
    {
        System.out.println(dx+" "+dy);
        //Point2D finaldelta = confineBounds(dx, dy);
        //dx = finaldelta.getX();
        //dy = finaldelta.getY();
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
    private void moveWeapons(double dx, double dy) {
        for (NonPlayerSprite w : myWeapons) {
            w.move(dx, dy);
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

    public void setNextLocation(Double double1) {
        // TODO Auto-generated method stub
        
    }
}
