package action;

import npsprite.FighterSprite;


public abstract class Action
{
    sprite.FighterSprite myFighter;

    public abstract void performAction (long elapsedTime);
}
