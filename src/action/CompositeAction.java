package action;

import java.util.ArrayList;

public class CompositeAction extends Action
{
    ArrayList<Action> myActions;
    
    public CompositeAction(ArrayList<Action> actions){
        myActions = actions;
    }
    
    public void addAction(Action a){
        myActions.add(a);
    }
    @Override
    public void performAction (long elapsedTime)
    {
        for(Action a : myActions)
            a.performAction(elapsedTime);

    }

}
