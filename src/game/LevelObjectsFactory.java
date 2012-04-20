package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import npsprite.FighterBody;
import npsprite.GroupID;
import npsprite.HealthSprite;
import npsprite.LimbSprite;
import npsprite.PlatformBlock;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
//import sprite.FighterSprite;
import sprite.HealthDisplay;
//import sprite.PlatformBlock;
import action.MotionAction;
import ai.AIAgent;
import ai.BasicAIAgent;
import ai.BasicStrategyAgent;
import ai.DefensiveStrategy;
import ai.OffensiveStrategy;
import ai.SituationalStrategyAgent;


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

    public ArrayList<FighterBody> createNPFighters () throws JDOMException
    {
        List<Element> fighters = findAllInstancesOfElement("Fighter");
        ArrayList<FighterBody> fs = new ArrayList<FighterBody>();

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
            LimbSprite s=new LimbSprite(e.getChildText("name"), 
                    c.getImage(e.getChildText("img")), 
                    GroupID.getIdFromString(e.getChildText("id")),
                    Double.parseDouble(e.getChildText("x")), 
                    Double.parseDouble(e.getChildText("y"))
                    ); //OMG WTF BBQ
            
            FighterBody tree= new FighterBody(s, e.getChildText("name"), dis);
            tree.setHealth(Integer.parseInt(e.getChildText("health")));
            
            mapFighter(playerIndex, tree);
            playerIndex++;

            s.setDefaultSpeed(Double.parseDouble(e.getChildText("speed")));

            fs.add(tree);
        }
        
        fs.add(createAIStrategyFighter());
        return fs;
    }

    private AIAgent createAIFighter(){
        LimbSprite body=new LimbSprite("ai1body",
                                       c.getImage("resources/flame.png"),
                                       GroupID.PLAYER_AI, 400, 500);
        BasicAIAgent ai = new BasicAIAgent("ai1main",body, new HealthDisplay(50,50, c.getWidth()/2 -30),0,c);
        
        ai.setRoot(body);
        return ai;
    }
    
    private AIAgent createAIStrategyFighter(){
        LimbSprite body=new LimbSprite("ai2body",
                                       c.getImage("resources/flame.png"),
                                       GroupID.PLAYER_AI, 400, 500);
        BasicStrategyAgent ai = new SituationalStrategyAgent("ai2main",body, new HealthDisplay(50,50, c.getWidth()/2 -30),0,c);

        ai.setRoot(body);
        return ai;
    }

    private void mapFighter (int playerIndex, FighterBody s)
    {
        InputHandler h = c.getMyHandler();
        int[] map = InputHandler.defaultMapping(playerIndex);
        h.addKey(map[0], MotionAction.UP(s));
        h.addKey(map[1], MotionAction.DOWN(s));
        h.addKey(map[2], MotionAction.LEFT(s));
        h.addKey(map[3], MotionAction.RIGHT(s));
    }


    public ArrayList<PlatformBlock> createNPBlocks () throws JDOMException
    {
        List<Element> b = findAllInstancesOfElement("Block");
        ArrayList<PlatformBlock> fs = new ArrayList<PlatformBlock>();
        for (Element e : b)
        {
            PlatformBlock s =
                new PlatformBlock(c.getImage(e.getChildText("img")));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                          Double.parseDouble(e.getChildText("y")));
            fs.add(s);
        }
        return fs;
    }
    //TODO: MAKE BETTER 
    public ArrayList<HealthSprite> createPowerUps () throws JDOMException
    {
        List<Element> b = findAllInstancesOfElement("Power");
        ArrayList<HealthSprite> fs = new ArrayList<HealthSprite>();
        for (Element e : b)
        {
            HealthSprite s =
                new HealthSprite(c.getImage(e.getChildText("img")), 
                        GroupID.getIdFromString(e.getChildText("id")));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                          Double.parseDouble(e.getChildText("y")));
            fs.add(s);
        }
        return fs;
    }
    
//    @Deprecated
//    public ArrayList<PlatformBlock> createBlocks () throws JDOMException
//    {
//        List<Element> b = findAllInstancesOfElement("Block");
//        ArrayList<PlatformBlock> fs = new ArrayList<PlatformBlock>();
//        for (Element e : b)
//        {
//            PlatformBlock s =
//                new PlatformBlock(c.getImages(e.getChildText("img"), 1, 1));
//            s.setLocation(Double.parseDouble(e.getChildText("x")),
//                          Double.parseDouble(e.getChildText("y")));
//            fs.add(s);
//        }
//        return fs;
//    }


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
