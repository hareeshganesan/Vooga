package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import action.QuitAction;

public class WinScreen extends OptionsScreen
{

   
    public WinScreen (MainGame arg0,
                      String background, String winner
                      )
    {
        super(arg0, background, new ArrayList<Option>());
        ArrayList<Option> options = new ArrayList<Option>();        
        options.add(new Label("Congrats on winning "+winner));
        options.add(new Label("Press r to return to title"));
        this.setMyOptions(options);
        this.setNextState((GameState)arg0.getGame(0));
        myHandler = new InputHandler();
        myHandler.addKey(KeyEvent.VK_R, new QuitAction(arg0));
    }
    
    
}
