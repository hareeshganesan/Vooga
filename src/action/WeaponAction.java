package action;

import npsprite.FighterBody;

public class WeaponAction implements Action
{
    FighterBody myFighter;
    int myWeapon;
    boolean done;
    
    public WeaponAction(FighterBody f, int weaponIndex){
        myFighter = f;
        myWeapon = weaponIndex;
    }
    
    @Override
    public void performAction (long elapsedTime)
    {
        //trigger animation
        System.out.println("using weapon "+myWeapon);
        done = true;
        
    }
    @Override
    public boolean isDone (long elapsedTime)
    {
        return done;
    }
}
