package events;

import java.util.ArrayList;
import npsprite.SpriteID.GroupID;
import npsprite.SpriteTemplate;

public class CompositeEvent {
    private GroupID groupID; //the groupID you want to match against
    private ArrayList<CollisionEvent> events;
    
    public CompositeEvent(SpriteTemplate o){
        groupID=o.getGroupID();
        events=new ArrayList<CollisionEvent>();
    }
    public CompositeEvent(SpriteTemplate o, ArrayList<CollisionEvent> events){
        groupID=o.getGroupID();
        this.events=events;
    }
    public void addEvent(CollisionEvent e){
        events.add(e);
    }
    public void addAll(ArrayList<CollisionEvent> e){
        events.addAll(e);
    }
    public void performActions(SpriteTemplate me, SpriteTemplate o){
        for (CollisionEvent e:events){
            e.performAction(me, o);
        }
    }
    public GroupID getGroupID() {
        return groupID;
    }
}
