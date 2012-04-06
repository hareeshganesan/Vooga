package sprite;

public class SpriteDecoratorTest {
    public FighterSprite createPlayer(String name,int groupID){
        return new FighterSprite(name,new HealthDisplay(1,1,1),groupID);
    }
    public NonPlayerSprite createBlock(){
        return new PlatformBlock(null);
    }
    
    public static void main(String[]arg){
        SpriteDecoratorTest t=new SpriteDecoratorTest();
        FighterSprite player1=t.createPlayer("player1", 10);
        NonPlayerSprite p=t.createBlock();
        p.setID(11);
        System.out.println(player1.getName()+" : "+player1.getID());
        System.out.println(p.getID());
        AttachableSprite b=new AttachableSprite(p); //THIS ISN'T A DECORATOR
        b.attachToFighter(player1);
        System.out.println(b.getID());
        b.detachFromFighter(player1);
        System.out.println(b.getID());
        AutomateSprite c=new AutomateSprite(p);
        p.setX(1); p.setY(1);
        System.out.println(p.getX()+", "+p.getY());
        c.collisionAction(12);
        c.respawn();
        System.out.println(p.getX()+", "+p.getY());
        
    }

}
