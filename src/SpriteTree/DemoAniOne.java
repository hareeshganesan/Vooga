package SpriteTree;

public class DemoAniOne extends Animation
{

    private boolean activate;


    public DemoAniOne (long currTime)
    {
        super(currTime);
        activate = false;
    }


    public boolean getStatus ()
    {
        return activate;
    }


    public void animate (LimbNode leg, LimbNode lowerLeg)
    {

        if (getCurrentTime() <= 40)
        {
            leg.rotate(-1);
            lowerLeg.rotate(2);
        }
        if (getCurrentTime() > 40 && getCurrentTime() <= 80)
        {
            lowerLeg.rotate(-2);
        }
        if (getCurrentTime() > 80 && getCurrentTime() <= 120)
        {
            lowerLeg.rotate(2);

        }
        if (getCurrentTime() > 120 && getCurrentTime() <= 160)
        {
            leg.rotate(1);
            lowerLeg.rotate(-2);
            activate = false;

        }
    }


    @Override
    public void activateAnimation ()
    {
        activate = true;
    }

}
