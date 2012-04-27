package action;

public abstract class Goal implements Action
{
    protected boolean done;
    protected Action repeatedAction;
    long timer;


    public Goal (Action actionToCompleteGoal, long cutoff)
    {
        repeatedAction = actionToCompleteGoal;
        timer = cutoff;
    }


    @Override
    public void performAction (long elapsedTime)
    {
        if (!done) repeatedAction.performAction(elapsedTime);
    }


    public boolean isDone (long elapsedTime)
    {
        updateGoalState();
        if (timer > 0) timer -= elapsedTime;
        else done = true;
        return done;
    }


    protected abstract void updateGoalState ();
}
