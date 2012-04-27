package events;

import java.awt.Point;

import npsprite.SpriteTemplate;

//would presumably move sprites without physics engine
public class GravityEvent extends CollisionEvent{
    private double gravity=1;

    private static GravityEvent he;
    protected GravityEvent(){}
    
    public static GravityEvent getInstanceOf(){
        if (he==null){
            he=new GravityEvent();
        }
        return he;
    }
    
    @Override
    public void performAction(SpriteTemplate me, SpriteTemplate o) {
        if (o.getY()+o.getHeight()-1<me.getY()){
//            System.out.println("moveto "+(me.getY()+gravity)+" "+me.getGroupID());
            me.setNextLocationIncrement(new Point.Double(0,gravity));
        }
    }
    public static String getName(){
        return "gravity";
    }

}
