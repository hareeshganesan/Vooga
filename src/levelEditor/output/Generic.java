package levelEditor.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * @author Peggy Li
 */

@SuppressWarnings("unused")
public class Generic
{

    private LevelObject myLevel;


    public Generic (LevelObject level)
    {
        myLevel = level;
    }


    public String getFileName ()
    {
        return myLevel.getLevelName();
    }


    public void writeToFile ()
    {
        try
        {
            FileOutputStream out =
                new FileOutputStream(new File(getFileName() + ".json"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
