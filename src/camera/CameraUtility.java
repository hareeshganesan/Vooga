package camera;

import java.util.HashMap;
import java.util.Set;


@SuppressWarnings("serial")
public class CameraUtility
{

    private HashMap<String, Camera> cameraModes = new HashMap<String, Camera>()
    {
        {
            put("Fixed", new FixedCamera());
            put("Floating", new FloatingCamera());
            put("Follow", new FollowCamera());
            put("HorizontalScrolling", new HorizontalScrollingCamera());
            put("VerticalScrolling", new VerticalScrollingCamera());
        }
    };

    private HashMap<String, SpecialCamera> specialCameraModes =
        new HashMap<String, SpecialCamera>()
        {
            {
                put("Shake", new SpecialShake());
                put("QuickZoom", new SpecialZoom());
            }
        };


    public void changeCamera (Camera camera, String cameraType)
    {
        camera = cameraModes.get(cameraType);
    }


    public SpecialCamera getSpecialCamera (String cameraType)
    {
        return specialCameraModes.get(cameraType);
    }


    public void getCameraTypes ()
    {
        Set<String> cameraTypes = cameraModes.keySet();

        for (String s : cameraTypes)
        {
            System.out.println(s);
        }
    }


    public void getSpecialCameraTypes ()
    {
        Set<String> cameraTypes = specialCameraModes.keySet();

        for (String s : cameraTypes)
        {
            System.out.println(s);
        }
    }


    public void addCameraType (String cameraName, Camera type)
    {
        cameraModes.put(cameraName, type);
    }


    public void addSpecialCameraType (String cameraName, SpecialCamera type)
    {
        specialCameraModes.put(cameraName, type);
    }

}
