package levelEditor.mvc;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;


public class SpriteLoader
{

    public static void main (String[] args)
    {
        SpriteLoader sl = new SpriteLoader();
        sl.loadClasses();
    }


    public SpriteLoader ()
    {

    }


    public void loadClasses ()
    {
        try
        {
            Class c = Class.forName("npsprite.PlatformBlock");
            //Controller x = (Controller)c.newInstance();
            Controller x =
                (Controller) c.getDeclaredConstructor(BufferedImage.class)
                              .newInstance(null);

            System.out.println(x.getClass());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
