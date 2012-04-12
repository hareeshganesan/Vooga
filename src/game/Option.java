package game;

import com.golden.gamedev.GameEngine;

abstract class Option
{
    String myName;
    Object value;

    public Option(String name){
        myName = name;
        
    }
    
    protected Object getValue ()
    {
        return value;
    }
    protected void setValue (Object value)
    {
        this.value = value;
    }
    abstract void displayOption(GameEngine engine);
    
    
}
