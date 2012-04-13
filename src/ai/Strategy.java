package ai;

import game.CombatInstance;
import sprite.FighterSprite;
import action.ActionSeries;


/**
 * A strategy must implement a method to generate an action series that can be
 * executed by the AI Agent calling it.
 * 
 * @author Hareesh
 */
abstract class Strategy
{

    abstract ActionSeries generateAction (CombatInstance c, FighterSprite f);
}
