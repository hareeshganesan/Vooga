package camera;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import npsprite.FighterBody;

@SuppressWarnings("serial")
public class SpecialShake extends SpecialCamera{

    SpecialShake() {
        super();
    }

    public void update(ArrayList<FighterBody> playerSprites,
            CameraBackground bg, Camera camera, double duration){
        
        Point oldPoint = camera.getCenter();
        Point newPoint = new Point((int)(camera.getX() - (50 * Math.sin(duration))), camera.getY());
        
        Rectangle oldBounds = camera.getBounds();
        Rectangle newBounds = new Rectangle((int)(oldBounds.x - (20*Math.sin(duration))), oldBounds.y, oldBounds.width, oldBounds.height);
                        
        camera.setCenter(newPoint);
        camera.setBounds(newBounds);
        
        //super.update(playerSprites, bg);
    }
}
