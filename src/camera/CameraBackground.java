package camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.background.ImageBackground;

public class CameraBackground extends ImageBackground{
    
    public CameraBackground(BufferedImage b){
        super(b);
    }
    
    public void render(Graphics2D g, Camera camera,int xbg, int ybg, int x, int y, int w, int h) {
        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);
        tr2.translate((this.getWidth() / 2) - (camera.getBounds().getWidth() * (camera.getZoom())) / 2,
                (this.getHeight() / 2) - (camera.getBounds().getHeight() * (camera.getZoom())) / 2);

        tr2.scale(camera.getZoom(), camera.getZoom());
        g.setTransform(tr2);
        g.draw(camera.getBounds());
        g.setTransform(old);
        super.render(g, xbg, ybg, x, y, w, h);
    }
}
