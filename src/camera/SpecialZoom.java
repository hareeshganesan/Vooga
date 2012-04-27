package camera;

import java.util.ArrayList;
import npsprite.FighterBody;


@SuppressWarnings("serial")
public class SpecialZoom extends SpecialCamera
{

    SpecialZoom ()
    {
        super();
    }


    SpecialZoom (double duration)
    {
        super(duration);
        // TODO Auto-generated constructor stub
    }


    public void update (ArrayList<FighterBody> playerSprites,
                        CameraBackground bg,
                        Camera camera,
                        double duration)
    {

        camera.changeZoom(25);

        //super.update(playerSprites, bg);
    }
}
