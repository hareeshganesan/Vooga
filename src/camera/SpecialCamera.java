package camera;

import java.util.ArrayList;

import npsprite.FighterBody;

@SuppressWarnings("serial")
public abstract class SpecialCamera extends Camera{
    double duration = 0;
    
    SpecialCamera()
    {
        this.duration = 0;
    }
    
    SpecialCamera(double duration)
    {
        this.duration = duration;
    }
    
    public void setDuration(double duration)
    {
        this.duration = duration;
    }
    
    public double getDuration()
    {
        return this.duration;
    }
    
    public abstract void update(ArrayList<FighterBody> playerSprites,
            CameraBackground bg, Camera camera, double duration);
    

}
