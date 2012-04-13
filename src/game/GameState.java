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
    
    public void setNextState(GameState next) {
        nextState = next;
    }
    
    public GameState getNextState() {
        return nextState;
    }
    
    public GameState getLastState() {
        return lastState;   
    }
    
    abstract void transitionState();
}
