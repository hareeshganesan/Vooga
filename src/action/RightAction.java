package action;

import sprite.FighterSprite;


public class RightAction extends MotionAction
{
    public RightAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_direction = 1;
        y_direction = 0;
    }

}
