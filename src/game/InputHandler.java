package game;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import action.Action;
import com.golden.gamedev.GameEngine;


public class InputHandler
{
    private HashMap<Integer, Action> key_mapping;


    public InputHandler ()
    {
        key_mapping = new HashMap<Integer, Action>();
    }


    public void addKey (Integer key, Action value)
    {
        key_mapping.put(key, value);
    }


    /**
     * Iterates through the mapped keys. If a key is pressed, the Action
     * corresponding to it is performed.
     * 
     * @param engine
     */
    public void update (long elapsedTime, GameEngine engine)
    {
        for (Integer key : key_mapping.keySet())
            if (engine.keyDown(key.intValue())) {
//                System.out.println(key);
                key_mapping.get(key).performAction(elapsedTime);
            }
                
    }


    /**
     * Return the default mapping for a user player specified by the index Known
     * Bugs: only two mappings defined so far
     * 
     * @param i
     * @return
     */
    public static int[] defaultMapping (int i)
    {
        int[][] playermappings = new int[2][6];

        playermappings[0][0] = KeyEvent.VK_UP;
        playermappings[0][1] = KeyEvent.VK_DOWN;
        playermappings[0][2] = KeyEvent.VK_LEFT;
        playermappings[0][3] = KeyEvent.VK_RIGHT;
        playermappings[0][4] = KeyEvent.VK_N;
        playermappings[0][5] = KeyEvent.VK_M;
        
        
        playermappings[1][0] = KeyEvent.VK_W;
        playermappings[1][1] = KeyEvent.VK_S;
        playermappings[1][2] = KeyEvent.VK_A;
        playermappings[1][3] = KeyEvent.VK_D;
        playermappings[1][4] = KeyEvent.VK_C;
        playermappings[1][5] = KeyEvent.VK_V;
        

        return playermappings[i];

    }
}
