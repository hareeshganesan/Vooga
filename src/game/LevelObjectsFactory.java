package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import sprite.FighterSprite;
import sprite.HealthDisplay;
import sprite.PlatformBlock;
import action.MotionAction;
import ai.AIAgent;
import ai.BasicAIAgent;
import ai.BasicStrategyAgent;
import ai.DefensiveStrategy;
import ai.OffensiveStrategy;


public class LevelObjectsFactory
{
    private Element root;
    private CombatInstance c;


    public LevelObjectsFactory (CombatInstance ci)
    {
        c = ci;
    }


    public Element getRoot (File xmlFile) throws IOException, JDOMException
    {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(xmlFile);
        return doc.getRootElement();
    }


    public void parseFile (File xmlFile) throws IOException, JDOMException
    {
        root = getRoot(xmlFile);
    }


    public ArrayList<FighterSprite> createFighters () throws JDOMException
    {
        List<Element> fighters = findAllInstancesOfElement("Fighter");
        ArrayList<FighterSprite> fs = new ArrayList<FighterSprite>();

        //ArrayList<HealthDisplay> displays = new ArrayList<HealthDisplay>();
        //TODO: resolve playerNum and playerIndex
        int playerNum = 1;
        int playerIndex = 0;
        //so far, only handles two player displays
        for (Element e : fighters)
        {
            HealthDisplay dis;
            if (playerNum == 1)
            {
                //display bar for the first player
                dis = new HealthDisplay(10, 10, c.getWidth() / 2 - 30);

                playerNum += 1;

            }
            else
            {
                dis =
                    new HealthDisplay((c.getWidth() / 2),
                                      10,
                                      c.getWidth() / 2 - 30);
            }

            FighterSprite s = new FighterSprite(e.getChildText("name"), dis, 0);

            mapFighter(playerIndex, s);
            playerIndex++;

            s.setMaxHealth(Integer.parseInt(e.getChildText("health")));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                          Double.parseDouble(e.getChildText("y")));
            s.setDefaultSpeed(Double.parseDouble(e.getChildText("speed")));
            System.out.println(e.getChildText("img"));
            s.setImages(c.getImages(e.getChildText("img"), 1, 1));

            fs.add(s);
        }
        
        fs.add(createAIStrategyFighter());
        return fs;
    }


    private AIAgent createAIFighter(){
        BasicAIAgent ai = new BasicAIAgent("ai1", new HealthDisplay(50,50, c.getWidth()/2 -30),0,c);
        ai.setMaxHealth(60);
        ai.setLocation(400, 500);
        ai.setDefaultSpeed(.3);
        ai.setImages(c.getImages("resources\\flame.png",1,1));
        return ai;
    }
    private AIAgent createAIStrategyFighter(){
        BasicStrategyAgent ai = new BasicStrategyAgent("ai1", new HealthDisplay(50,50, c.getWidth()/2 -30),0,c);
        ai.setMaxHealth(60);
        ai.setLocation(400, 500);
        ai.setDefaultSpeed(.3);
        ai.setImages(c.getImages("C:\\Users\\Hareesh\\Desktop\\CS108\\Vooga\\src\\resources\\flame.png",1,1));
        
        ai.addStrategy(.5, new OffensiveStrategy());
        ai.addStrategy(1, new DefensiveStrategy());
        return ai;
    }
    private void mapFighter (int playerIndex, FighterSprite s)
    {
        InputHandler h = c.getMyHandler();
        int[] map = InputHandler.defaultMapping(playerIndex);
        h.addKey(map[0], MotionAction.UP(s));
        h.addKey(map[1], MotionAction.DOWN(s));
        h.addKey(map[2], MotionAction.LEFT(s));
        h.addKey(map[3], MotionAction.RIGHT(s));
    }


    public ArrayList<PlatformBlock> createBlocks () throws JDOMException
    {
        List<Element> b = findAllInstancesOfElement("Block");
        ArrayList<PlatformBlock> fs = new ArrayList<PlatformBlock>();
        for (Element e : b)
        {
            PlatformBlock s =
                new PlatformBlock(c.getImages(e.getChildText("img"), 1, 1));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                          Double.parseDouble(e.getChildText("y")));
            fs.add(s);
        }
        return fs;
    }


    public String getBackground ()
    {
        return root.getChildText("bg");
    }


    private List<Element> findAllInstancesOfElement (String info)
        throws JDOMException
    {
        XPath xpath = XPath.newInstance("//" + info);
        List<Element> list = xpath.selectNodes(root);
        return list;
    }

}
