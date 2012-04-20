package sprite;

import java.awt.image.BufferedImage;


//sample subclass

@Deprecated
public class LandmineBlock extends NonPlayerSprite
{
    private int xradius;
    private int yradius;

    public LandmineBlock (BufferedImage[] b)
    {
        super(b);
        super.setDamage(-5);
        xradius=0;
        yradius=0;
    }
    public void setExplodeRadius(int x,int y){
        xradius=x;
        yradius=y;
    }
    
    //these are used in calculating collision rectangle
    @Override
    public int getWidth(){
        if (xradius==0){
            xradius=this.getWidth()*2;
        }
        return xradius;
    }
    @Override
    public int getHeight(){
        if (yradius==0){
            yradius=this.getHeight()*2;
        }
        return yradius;
    }
    

    @Override
    public void collisionAction (int otherGroup)
    {
        this.setActive(false);
    }

    public void move (double dx, double dy)
    {
        //do nothing, you can't move these blocks
    }
}
