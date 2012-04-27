package events;

import npsprite.SpriteTemplate;


public class InactiveEvent extends CollisionEvent
{
    private static InactiveEvent he;
    protected InactiveEvent(){}
    
    public static InactiveEvent getInstanceOf(){
        if (he==null){
            he=new InactiveEvent();
        }
        return he;
    }
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
//        System.out.println("inactivating: "+me);
        me.setActive(false);
    }

    public static String getName(){
        return "inactive";
    }
}
