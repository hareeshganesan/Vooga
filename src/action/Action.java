package action;

// import npsprite.FighterSprite;

public interface Action
{

    public abstract void performAction (long elapsedTime);


    public boolean isDone (long elapsedTime);
}
