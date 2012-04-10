package action;

import npsprite.FighterSprite;


public class UpAction extends MotionAction
{

    public UpAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_direction = 0;
        y_direction = -1;
    }

}
