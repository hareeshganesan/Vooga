package action;

import PhysicsEngine.PhysicsEngine;
import npsprite.FighterBody;

public class JumpAction extends MotionAction
{

    public JumpAction (FighterBody r, double x, double y,   PhysicsEngine p)
    {
        super(r, x, y, p);
        done = true;
    }
    
    public void performAction (long elapsed_time)
    {
        if(done){
            myPhysicsEngine.process(this, elapsed_time);
            done = false;
        }
        setDone();
    }

    private void setDone ()
    {
        if(myFighter.getCollisionStatus().getDown())
            done = true;
    }
    
    

}
