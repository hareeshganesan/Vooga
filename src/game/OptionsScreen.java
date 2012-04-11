package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import com.golden.gamedev.GameEngine;

public class OptionsScreen extends GameState
{

    GameEngine myEngine;
    ArrayList<Option> myOptions;
    public OptionsScreen (GameEngine arg0, String background, ArrayList<Option> options)
    {
        super(arg0);
        myEngine = arg0;
        myOptions = options;
        
    }


    @Override
    public void initResources ()
    {
        

    }

    @Override
    public void render (Graphics2D arg0)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void update (long arg0)
    {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void finish(){
       super.finish();
    }


    @Override
    void transitionState ()
    {
        // TODO Auto-generated method stub
        
    }

}
