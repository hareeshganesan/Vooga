package camera;

import java.awt.Graphics;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import npsprite.FighterBody;


public class FollowCamera extends Camera
{
    public FollowCamera ()
    {
        super();
    }


    @Override
    public void update (ArrayList<FighterBody> playerSprites, CameraBackground bg)
    {
        FighterBody focus = playerSprites.get(0);
        bg.setToCenter((int)focus.getX(), (int)focus.getY(), focus.getWidth(), focus.getHeight());
        super.update(playerSprites, bg);
    }


    public void render (Graphics g1, CameraBackground bg)
    {
        super.render(g1, bg);
    }
}
