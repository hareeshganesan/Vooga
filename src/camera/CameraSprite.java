package camera;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import npsprite.GroupID;
import npsprite.SpriteTemplate;


@SuppressWarnings("serial")
public class CameraSprite extends SpriteTemplate
{

    public CameraSprite (GroupID myID)
    {
        super(myID);
    }


    public void render (Graphics2D g, SpriteTemplate sprite, Camera camera)
    {

        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);

        double xScale = (double) Camera.CANVAS_WIDTH / camera.getWidth();
        double yScale = (double) Camera.CANVAS_HEIGHT / camera.getHeight();

        double xTrans =
            (double) (camera.getWidth() / 2) - (Camera.CANVAS_WIDTH * xScale) /
                    2;
        double yTrans =
            (double) (camera.getHeight() / 2) -
                    (Camera.CANVAS_HEIGHT * yScale) / 2;

        tr2.translate(xTrans, yTrans);
        tr2.scale(xScale, yScale);

        g.setTransform(tr2);
        sprite.render(g);
        g.setTransform(old);

        //System.out.println("translating by " + xTrans + "," + yTrans);

    }
}
