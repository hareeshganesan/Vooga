package game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class OptionsScreen extends GameState
{

    MainGame myEngine;
    ArrayList<Option> myOptions;
    Option selected;
    int optionIndex;
    String myBackground;


    public OptionsScreen (MainGame arg0,
                          String background,
                          ArrayList<Option> options)
    {
        super(arg0);
        myEngine = arg0;
        myOptions = options;
        myBackground = background;
        optionIndex = 0;
        selected = myOptions.get(optionIndex);
    }


    @Override
    public void initResources ()
    {
        this.lastState = myEngine.getCurrentState();
        this.nextState = myEngine.getCurrentState();
    }


    @Override
    public void render (Graphics2D g2)
    {
        g2.setFont(new Font("Serif", Font.BOLD, 20));
        for (int i = 0; i < myOptions.size(); i++)
        {
            if (myOptions.get(i).myName.equals(selected.myName))
            {
                //g2.drawImage(getImage("/resources/arrow.jpg"), 20, 20 + (i*30), (ImageObserver)this);
            }
            g2.drawString((String) myOptions.get(i).getValue(),
                          100,
                          20 + (i * 30));
        }

    }


    @Override
    public void update (long elapsedTime)
    {
        selected = myOptions.get(optionIndex);

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


    public void enterAction ()
    {
        this.nextState =
            new OptionsScreen(myEngine, myBackground, new ArrayList<Option>());
        transitionState();
    }


    @Override
    public void finish ()
    {
        super.finish();
    }


    @Override
    public void transitionState ()
    {
        myEngine.setCurrentState(this.nextState);

    }

}
