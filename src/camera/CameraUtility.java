package camera;

import java.util.HashMap;
import java.util.Set;

public class CameraUtility {
    
    private HashMap<String, Camera> cameraModes = new HashMap<String, Camera>()
    {
        {
            put("Fixed",new FixedCamera());
            put("Floating",new FloatingCamera());
            put("Follow",new FollowCamera());
            put("HorizontalScrolling", new HorizontalScrollingCamera());
            put("VerticalScrolling", new VerticalScrollingCamera());
        }
    };
    
    public void changeCamera(Camera camera, String cameraType){
        camera = cameraModes.get(cameraType);
    }
    
    public void getCameraTypes() {
        Set<String> cameraTypes = cameraModes.keySet();
        
        for (String s : cameraTypes)
        {
            System.out.println(s);
        }
    }
    
    // dont think this is correct
    public void addCameraType(String cameraName, Camera type) {
        cameraModes.put(cameraName, type);
    }
}
