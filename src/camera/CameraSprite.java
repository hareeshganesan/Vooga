package camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import npsprite.GroupID;
import npsprite.SpriteTemplate;


@SuppressWarnings("serial")
public class CameraSprite extends SpriteTemplate{
        
    public CameraSprite(GroupID myID){
        super(myID);
    }
    
    public void render(Graphics2D g, SpriteTemplate sprite, Camera camera) {
                
        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);
        tr2.translate(-50, -50);
        tr2.scale((double)Camera.CANVAS_WIDTH/camera.getWidth(), (double)Camera.CANVAS_HEIGHT/camera.getHeight());
        g.setTransform(tr2);
        sprite.render(g);
        g.setTransform(old);
        
    }
}
