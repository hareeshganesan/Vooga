package sprite;

import java.awt.image.BufferedImage;


// for demo game. Sample block that doesn't move/react
public class PlatformBlock extends NonPlayerSprite
{

    //default speed is 0

    public PlatformBlock (BufferedImage[] b)
    {
        super(b);
    }


    @Override
    public void collisionAction (int otherGroup)
    {
        //do nothing
    }


    @Override
    public void setSpeed (double speed)
    {
        //do nothing
    }


    public void move (double dx, double dy)
    {
        //do nothing, you can't move these blocks
    }


	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getSpriteKind() {
		// TODO Auto-generated method stub
		return "PlatformBlock";
	}

//    //TODO
//    public void render(Graphics2D g){
//        
//    }

}
