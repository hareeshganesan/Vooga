package camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.background.ImageBackground;


@SuppressWarnings("serial")
public class CameraBackground extends ImageBackground
{

    public CameraBackground (BufferedImage b)
    {
        super(b);
    }
    
    public void render(Graphics2D g, BufferedImage b, Camera camera) {
        
        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);
        tr2.translate(-10, -10);
        tr2.scale((double)Camera.CANVAS_WIDTH/camera.getWidth(), (double)Camera.CANVAS_HEIGHT/camera.getHeight());
        g.setTransform(tr2);
        g.drawImage(b, tr2, null);
        super.render(g);
        //g.drawImage(this.getImage(), 0, 0, this.getWidth(), this.getHeight(), (ImageObserver)this);
        g.setTransform(old);
        
    }
}
