package ai;

import npsprite.FighterBody;
import game.CombatInstance;
import sprite.FighterSprite;
import action.AvoidAction;


/**
 * The DefensiveStrategy class creates an ActionSeries where the AI will move
 * toward a specified fighter by following it as the fighter moves for 5
 * seconds.
 * 
 * @author Hareesh
 */
public class DefensiveStrategy extends Strategy
{

    public DefensiveStrategy (FighterBody ai, CombatInstance ci)
    {
        super(ai, ci);

    }


    @Override
    public void initializeGoals ()
    {
        goals.add(new AvoidGoal(myFighter, c.getFighters().get(0)));

    }

    private class AvoidGoal extends Goal
    {
        FighterBody myFighter;
        FighterBody myEnemy;


        public AvoidGoal (FighterBody me, FighterBody enemy)
        {
            super(new AvoidAction(me, enemy,myPhysicsEngine), 10000);
            myFighter = me;
            myEnemy = enemy;
        }


        @Override
        void updateGoalState ()
        {
            if (myFighter.getCurrentLocation()
                         .distance(myEnemy.getCurrentLocation()) > 200) done =
                true;
        }

    }

}
