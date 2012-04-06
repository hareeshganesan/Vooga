package ai;

import java.util.List;
import action.MotionAction;
import game.CombatInstance;
import sprite.FighterSprite;
import sprite.HealthDisplay;


public class BasicAIAgent extends AIAgent
{

    public BasicAIAgent (String name,
                         HealthDisplay display,
                         int groupID,
                         CombatInstance c)
    {
        super(name, display, groupID, c);
    }

    public void update (long elapsedTime)
    {
        List<FighterSprite> fs = myLevel.getFighters();
        if(getCurrentLocation().distance(fs.get(0).getCurrentLocation())>40){
            MotionAction x = new MotionAction(this, fs.get(0).getCurrentLocation());
            x.performAction(elapsedTime);
        }
        super.update(elapsedTime);
    }

}
