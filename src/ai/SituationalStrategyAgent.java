package ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import npsprite.LimbSprite;
import SpriteTree.LimbNode;
import game.CombatInstance;
import sprite.HealthDisplay;


public class SituationalStrategyAgent extends BasicStrategyAgent
{
    ArrayList<Situation> situations = new ArrayList<Situation>();

    public SituationalStrategyAgent (String name,
                               LimbSprite root,
                               HealthDisplay display,
                               int groupID,
                               CombatInstance c)
    {
        super(name, root, display, groupID, c);
        situations.add(new DefaultSituation(this,c));
    }


    @Override
    protected Strategy selectRandomStrategy ()
    {
        Situation s = null;
        Iterator<Situation> i = situations.iterator();
        while (s == null && i.hasNext())
        {
            s = i.next();
            if (s.isOccurring())
            {
                return s.selectRandomStrategy();
            }
        }
        return null;
    }


    public void addWeightingScheme (Situation s)
    {
        situations.add(s);
    }
}
