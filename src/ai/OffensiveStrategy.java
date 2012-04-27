package ai;

import npsprite.FighterBody;
import game.CombatInstance;
import action.AdvancedFollowAction;
import action.FollowAction;
import action.Goal;
import action.WeaponAction;


/**
 * The OffensiveStrategy class creates an ActionSeries where the AI will move
 * toward a specified fighter by following it as the fighter moves for 5
 * seconds.
 * 
 * @author Hareesh
 */
public class OffensiveStrategy extends Strategy
{

    public OffensiveStrategy (FighterBody ai, CombatInstance ci)
    {
        super(ai, ci);
    }


    @Override
    public void initializeGoals ()
    {
        goals.add(new FollowGoal(myFighter, c.getFighters().get(0), this.c));
        goals.add(new AttackGoal(myFighter, c.getFighters().get(0), this.c));
    }

    private class FollowGoal extends Goal
    {
        FighterBody myFighter;
        FighterBody myEnemy;


        public FollowGoal (FighterBody me, FighterBody enemy, CombatInstance ci)
        {
            super(new AdvancedFollowAction(me, enemy, ci ,myPhysicsEngine), 10000);
            myFighter = me;
            myEnemy = enemy;
        }


        @Override
        protected void updateGoalState ()
        {
            if (myFighter.getCurrentLocation()
                         .distance(myEnemy.getCurrentLocation()) < 75) done =
                true;
        }

    }
    
    private class AttackGoal extends Goal
    {
        FighterBody myFighter;
        FighterBody myEnemy;


        public AttackGoal (FighterBody me, FighterBody enemy, CombatInstance ci)
        {
            super(new WeaponAction(me, 0, ci), 10000);
            myFighter = me;
            myEnemy = enemy;
        }


        @Override
        protected
        void updateGoalState ()
        {
            if(this.repeatedAction.isDone(0))
                done = true;
        }

    }

}
