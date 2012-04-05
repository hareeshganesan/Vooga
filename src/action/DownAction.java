package action;

import sprite.FighterSprite;


public class DownAction extends MotionAction
{

    public DownAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_speed = 0;
        y_speed = 1;
    }

}
