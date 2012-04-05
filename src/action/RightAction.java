package action;

import sprite.FighterSprite;


public class RightAction extends MotionAction
{
    public RightAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_speed = 1;
        y_speed = 0;
    }

}
