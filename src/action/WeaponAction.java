package action;

import events.HealthEvent;
import game.CombatInstance;
import npsprite.FighterBody;

public class WeaponAction implements Action
{
    FighterBody myFighter;
    int myWeapon;
    CombatInstance myLevel;
    boolean done;
    
    public WeaponAction(FighterBody f, int weaponIndex, CombatInstance c){
        myFighter = f;
        myWeapon = weaponIndex;
        myLevel = c;
    }
    
    @Override
    public void performAction (long elapsedTime)
    {
        //trigger animation
        for(FighterBody f : myLevel.getFighters()){
            if(f!=myFighter){
                if(myFighter.getCurrentLocation().distance(f.getCurrentLocation())<75){
                    System.out.println("effective weapon");
                    //f.addCollisionEvent(myFighter.getGroupID(), new HealthEvent(f, myFighter));
                    (HealthEvent.getInstanceOf()).performAction(f, myFighter);
                }
            }
        }
        done = true;
        
    }
    @Override
    public boolean isDone (long elapsedTime)
    {
        return done;
    }
}
