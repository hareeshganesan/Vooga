package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import com.golden.gamedev.object.background.ImageBackground;


public class OptionsScreen extends GameState
{

    MainGame myEngine;
    ArrayList<Option> myOptions;


    protected void setMyOptions (ArrayList<Option> myOptions)
    {
        this.myOptions = myOptions;
    }

    int optionIndex;
    ImageBackground myBackground;


    public OptionsScreen (MainGame arg0,
                          String background,
                          ArrayList<Option> options)
    {
        super(arg0);
        myEngine = arg0;
        myOptions = options;
        myBackground = new ImageBackground(getImage(background));
        optionIndex = 0;
    }


    @Override
    public void initResources ()
    {
        this.lastState = myEngine.getCurrentState();
        this.nextState = (GameState) myEngine.getGame(0);
    }


    @Override
    public void render (Graphics2D g2)
    {
        myBackground.render(g2);
        for (Option o : myOptions)
        {
            o.renderOption(g2, 100, 20 * myOptions.indexOf(o) + 100);
        }
    }


    @Override
    public void update (long elapsedTime)
    {
        myHandler.update(elapsedTime, this.myEngine);
    }


    public void upAction ()
    {
        if (optionIndex == myOptions.size() - 1)
        {
            optionIndex = 0;
        }
        else optionIndex++;
    }


    public void downAction ()
    {
        if (optionIndex == 0)
        {
            optionIndex = myOptions.size() - 1;
        }
        else optionIndex--;
    }


    @Override
    public void finish ()
    {
        super.finish();
    }


    @Override
    public void transitionState ()
    {
        if (nextState != null) myEngine.nextGame = this.nextState;
        else myEngine.nextGame = this.lastState;
        finish();
    }

}
