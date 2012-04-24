package action;

public interface Action
{

    public abstract void performAction (long elapsedTime);


    public boolean isDone (long elapsedTime);
}
