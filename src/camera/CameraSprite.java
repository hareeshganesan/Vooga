package camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import sprite.SpriteTemplate;


@SuppressWarnings("serial")
public class CameraSprite extends SpriteTemplate{
        
    public CameraSprite(){
        super();
    }
    
    public void render(Graphics2D g, SpriteTemplate sprite, Camera camera) {
        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);
        //tr2.translate((camera.getWidth() / 2) - (camera.getBounds().getWidth() / 2),
        //        (camera.getHeight() / 2) - (camera.getBounds().getHeight() / 2));
        
        tr2.scale((double)Camera.CANVAS_WIDTH/camera.getWidth(), (double)Camera.CANVAS_HEIGHT/camera.getWidth());
 
        g.setTransform(tr2);
        sprite.render(g);
        g.setTransform(old);
    }


    @Override
    public void collisionAction(int otherGroupID) {
        this.collisionAction(otherGroupID);
        
    }


    @Override
    protected Point2D confineBounds(double dx, double dy) {
        return this.confineBounds(dx, dy);

    }
}
