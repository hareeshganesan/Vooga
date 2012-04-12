package npsprite;

import action.CollisionEvent;
import action.PowerUpEvent;
import action.PracticeEvent;

public class PracticeSprite{

    private static PracticeEvent c;
    static {
        c=new PracticeEvent();
    }
    private int id=0;
    public void setID(int num){
        id=num;
    }
    public int getID(){
        return id;
    }
    public void collisionAction(){
        PracticeSprite o=new PracticeSprite();
        o.setID(1);
        c.performActionBy(this, o);
        PracticeSprite p=new PracticeSprite();
        p.setID(2);
        c.performActionBy(this, p);
    }
    public static void main(String[]arg){
        PracticeSprite p=new PracticeSprite();
        p.collisionAction();
    }
}
