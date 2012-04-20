package camera;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

import sprite.SpriteTemplate;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

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

    @Override
    public String getSpriteKind() {
        return this.getSpriteKind();
    }
}
