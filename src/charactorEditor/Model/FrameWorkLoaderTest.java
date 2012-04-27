package charactorEditor.Model;

import java.io.FileNotFoundException;
import npsprite.FighterBody;


public class FrameWorkLoaderTest
{
    static public void main (String[] args) throws FileNotFoundException
    {
        FighterBody root =
            FrameWorkLoader.load("C:/Users/Chen/workspace/Vooga/chen.json");

    }
}
