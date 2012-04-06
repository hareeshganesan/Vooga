package action;

import sprite.FighterSprite;


public class LeftAction extends MotionAction
{
    public LeftAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_direction = -1;
        y_direction = 0;
    }
}
