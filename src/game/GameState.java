package game;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

abstract public class GameState extends GameObject
{
    GameState nextState;
    GameState lastState;
    
    public GameState (GameEngine arg0)
    {
        super(arg0);
    }
    
    abstract public void transitionState();
}
