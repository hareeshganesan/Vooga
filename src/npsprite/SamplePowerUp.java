package npsprite;


public class SamplePowerUp extends NonPlayerSprite{
    
  //if I don't use the decorator, then maybe this can contain the attaching function...
    FighterSprite fighter;
    private int oldID;

    @Override
    public void collisionAction(SpriteTemplate otherSprite) {
        //TODO: if otherGroupID belongs to fightersprite, somehow affect the fightersprite...
    }
    
    //lol i don't know what i'm doing
}
