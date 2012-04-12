package ai;

import game.CombatInstance;
import java.util.List;
import sprite.FighterSprite;
import sprite.HealthDisplay;
import action.MotionAction;

public class BasicAIAgent extends AIAgent {

    public BasicAIAgent(String name, HealthDisplay display, int groupID,
            CombatInstance c) {
        super(name, display, groupID, c);
    }

    public void update (long elapsedTime)
    {
        List<FighterSprite> fs = myLevel.getFighters();
        if(getCurrentLocation().distance(fs.get(0).getCurrentLocation())>40){
            MotionAction follow = new MotionAction(this, fs.get(0).getCurrentLocation());
            follow.performAction(elapsedTime);
        }
        super.update(elapsedTime);
    }

}
