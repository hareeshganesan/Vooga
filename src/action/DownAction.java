package action;

import sprite.FighterSprite;

//import npsprite.FighterSprite;


public class DownAction extends MotionAction
{

    public DownAction (FighterSprite fighter)
    {
        myFighter = fighter;
        x_direction = 0;
        y_direction = 1;
    }

}
