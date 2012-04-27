package action;

import npsprite.Animation;

import events.HealthEvent;
import game.CombatInstance;
import npsprite.FighterBody;


public class WeaponAction implements Action
{
    FighterBody myFighter;
    String myWeapon;
    CombatInstance myLevel;
    boolean done;
    private Animation a;
    
    public WeaponAction(FighterBody f, String weapon, CombatInstance c){
        myFighter = f;
        myWeapon = weapon;
        myLevel = c;


    }
    
    @Override
    public void performAction (long elapsedTime)
    {
    	if(a == null){
        	a = myFighter.getAnimation(myWeapon);
    	}
    	else{
    		if(!a.getStatus()){
    			a.activateAnimation();
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
