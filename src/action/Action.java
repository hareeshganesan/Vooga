package action;

public interface Action
{

    public void performAction (long elapsedTime);


    public boolean isDone (long elapsedTime);
    
}
