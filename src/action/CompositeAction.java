package action;

import java.util.ArrayList;


public class CompositeAction implements Action
{
    ArrayList<Action> myActions;
    boolean done;


    public CompositeAction (ArrayList<Action> actions)
    {
        myActions = actions;
    }


    public void addAction (Action a)
    {
        myActions.add(a);
    }


    @Override
    public void performAction (long elapsedTime)
    {
        for (Action a : myActions)
            a.performAction(elapsedTime);

        done = true;
    }


    @Override
    public boolean isDone (long elapsedTime)
    {
        return done;
    }

}
