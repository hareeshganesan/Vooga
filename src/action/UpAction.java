package action;

import sprite.FighterSprite;


public class UpAction extends MotionAction
{

    public UpAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_speed = 0;
        y_speed = -1;
    }

}
