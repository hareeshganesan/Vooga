package events;

import java.util.ArrayList;

import npsprite.GroupID;
import npsprite.SpriteTemplate;

public class CompositeEvent extends CollisionEvent{

    private ArrayList<CollisionEvent> events;

    public CompositeEvent() {
        events = new ArrayList<CollisionEvent>();
    }

    public CompositeEvent(SpriteTemplate o, ArrayList<CollisionEvent> events) {
        this.events = events;
    }

    public void addEvent(CollisionEvent e) {
        if (e==null){return;}
        events.add(e);
    }

    public void addAll(ArrayList<CollisionEvent> e) {
        if (e==null){return;}
        events.addAll(e);
    }

    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        for (CollisionEvent e : events) {
            e.performAction(me, o);
        }
    }

    public static String getName() {
        return "composite";
    }

    //THIS IS NOT A SINGLETON, NO TIME TO FIX
    public static CollisionEvent getInstanceOf() {
        return new CompositeEvent();
    }
    

}
