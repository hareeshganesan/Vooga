package action;

import sprite.FighterSprite;


public class LeftAction extends MotionAction
{
    public LeftAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_speed = -1;
        y_speed = 0;
    }
}
