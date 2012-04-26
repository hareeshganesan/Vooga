package events;

import java.util.ArrayList;

import npsprite.GroupID;
import npsprite.SpriteTemplate;

public class CompositeEvent extends CollisionEvent{

    private ArrayList<CollisionEvent> events;

    public CompositeEvent(SpriteTemplate o) {
        events = new ArrayList<CollisionEvent>();
    }

    public CompositeEvent(SpriteTemplate o, ArrayList<CollisionEvent> events) {
        this.events = events;
    }

    public void addEvent(CollisionEvent e) {
        events.add(e);
    }

    public void addAll(ArrayList<CollisionEvent> e) {
        events.addAll(e);
    }

    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        for (CollisionEvent e : events) {
            e.performAction(me, o);
        }
    }
}
