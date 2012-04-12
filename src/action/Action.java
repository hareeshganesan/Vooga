package action;

import sprite.FighterSprite;

//import npsprite.FighterSprite;


public abstract class Action
{
    FighterSprite myFighter;
    public abstract void performAction (long elapsedTime);
}
