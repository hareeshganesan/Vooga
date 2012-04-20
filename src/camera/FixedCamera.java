package camera;

import java.awt.Graphics;
import java.util.ArrayList;
import sprite.FighterSprite;


public class FixedCamera extends Camera
{
    @Override
    public void update (ArrayList<FighterSprite> playerSprites,
                        CameraBackground bg)
    {
        super.update(playerSprites, bg);
    }


    public void render (Graphics g1, CameraBackground bg)
    {
        super.render(g1, bg);
    }
}
