package camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import sprite.SpriteTemplate;


@SuppressWarnings("serial")
public class CameraSprite extends SpriteTemplate
{

    public CameraSprite ()
    {
        super();
    }


    public void render (Graphics2D g,
                        Camera camera,
                        int xbg,
                        int ybg,
                        int x,
                        int y,
                        int w,
                        int h)
    {
        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);
        tr2.translate((this.getWidth() / 2) -
                              ((camera.getBounds().getWidth() * (camera.getZoom())) / 2),
                      (this.getHeight() / 2) -
                              ((camera.getBounds().getHeight() * (camera.getZoom())) / 2));
        tr2.scale(this.getWidth() / 522, this.getWidth() / 522);
        g.setTransform(tr2);
        g.draw(camera.getBounds());
        //g.setTransform(old);
        super.render(g, xbg, ybg);
    }


    @Override
    public void collisionAction (int otherGroupID)
    {
        // TODO Auto-generated method stub

    }


    @Override
    protected Point2D confineBounds (double dx, double dy)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
