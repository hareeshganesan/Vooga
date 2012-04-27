package camera;

import java.awt.Graphics;
import java.util.ArrayList;
import npsprite.FighterBody;


@SuppressWarnings("serial")
public class FloatingCamera extends Camera
{

    public FloatingCamera ()
    {
        super();
    }


    @Override
    public void update (ArrayList<FighterBody> playerSprites,
                        CameraBackground bg)
    {
        bg.setToCenter(this.getX(),
                       this.getY(),
                       this.getHeight(),
                       this.getWidth());
        super.update(playerSprites, bg);

    }


    public void render (Graphics g1, CameraBackground bg)
    {
        super.render(g1, bg);
    }
}
