package ai;

import game.CombatInstance;
import sprite.FighterSprite;
import action.FollowAction;


/**
 * The OffensiveStrategy class creates an ActionSeries where the AI will move
 * toward a specified fighter by following it as the fighter moves for 5
 * seconds.
 * 
 * @author Hareesh
 */
public class OffensiveStrategy extends Strategy
{

    public OffensiveStrategy (FighterSprite ai, CombatInstance ci)
    {
        super(ai, ci);
    }


    @Override
    public void initializeGoals ()
    {
        goals.add(new FollowGoal(myFighter, c.getFighters().get(0)));

    }

    private class FollowGoal extends Goal
    {
        FighterSprite myFighter;
        FighterSprite myEnemy;


        public FollowGoal (FighterSprite me, FighterSprite enemy)
        {
            super(new FollowAction(me, enemy), 10000);
            myFighter = me;
            myEnemy = enemy;
        }


        @Override
        void updateGoalState ()
        {
            if (myFighter.getCurrentLocation()
                         .distance(myEnemy.getCurrentLocation()) < 70) done =
                true;
        }

    }

}
