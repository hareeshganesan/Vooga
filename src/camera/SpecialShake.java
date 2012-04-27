package camera;

import java.awt.Point;
import java.util.ArrayList;

import npsprite.FighterBody;

@SuppressWarnings("serial")
public class SpecialShake extends SpecialCamera{

    SpecialShake() {
        super();
    }

    public void update(ArrayList<FighterBody> playerSprites,
            CameraBackground bg, Camera camera, double duration){
        
        Point newPoint = new Point((int)(camera.getX() + (0.1 * Math.sin(camera.getX()))), camera.getY());
        camera.setCenter(newPoint);
        
        
        //super.update(playerSprites, bg);
    }
}
