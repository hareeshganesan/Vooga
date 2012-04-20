package camera;

import java.awt.Graphics;
import java.util.ArrayList;
import sprite.FighterSprite;


public class FollowCamera extends Camera
{
    public FollowCamera ()
    {
        super();
    }


    @Override
    public void update (ArrayList<FighterSprite> playerSprites,
                        CameraBackground bg)
    {
        bg.setToCenter(playerSprites.get(0));
        super.update(playerSprites, bg);
    }


    public void render (Graphics g1, CameraBackground bg)
    {
        super.render(g1, bg);
    }
}
