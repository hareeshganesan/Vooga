package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import npsprite.FighterBody;
import npsprite.GroupID;
import npsprite.HealthSprite;
import npsprite.LimbSprite;
import npsprite.NodeSprite;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;
import npsprite.properties.DamageProperty;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import sprite.HealthDisplay;
import PhysicsEngine.PhysicsEngine;
import action.MotionAction;
import ai.AIAgent;
import ai.BasicAIAgent;
import ai.BasicStrategyAgent;
import ai.DefensiveStrategy;
import ai.OffensiveStrategy;
import ai.SituationalStrategyAgent;

public class LevelObjectsFactory {
    private Element root;
    private CombatInstance c;
    private PhysicsEngine myPhysicsEngine;
    
    //TODO: SAVE LISTS OF ALL PROPERTYOBJECT SUBCLASSES AND COLLISIONEVENT SUBCLASSES WHEN THIS CLASS IS LOADED - REFLECTION?

    public LevelObjectsFactory(CombatInstance ci) {
        c = ci;
        myPhysicsEngine = c.getPhysicsEngine();
    }

    public Element getRoot(File xmlFile) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(xmlFile);
        return doc.getRootElement();
    }

    public void parseFile(File xmlFile) throws IOException, JDOMException {
        root = getRoot(xmlFile);
    }

    public ArrayList<FighterBody> createNPFighters() throws JDOMException {
        List<Element> fighters = findAllInstancesOfElement("Fighter");
        ArrayList<FighterBody> fs = new ArrayList<FighterBody>();

        int playerNum = 1;
        int playerIndex = 0;
        // so far, only handles two player displays
        for (Element e : fighters) {
            HealthDisplay dis;
            if (playerNum == 1) {
                // display bar for the first player
                dis = new HealthDisplay(10, 10, c.getWidth() / 2 - 30);

                playerNum += 1;

            } else {
                dis = new HealthDisplay((c.getWidth() / 2), 10,
                        c.getWidth() / 2 - 30);
            }
            Element torsoe=e.getChild("limb");
            LimbSprite torso = new LimbSprite(torsoe.getChildText("name"), 
                    c.getImage(torsoe.getChildText("img")), 
                    GroupID.getIdFromString(e.getChildText("id")), 
                    Double.parseDouble(torsoe.getChildText("x")),
                    Double.parseDouble(torsoe.getChildText("y")),
                    Double.parseDouble(torsoe.getChildText("damage")));
            torso.setDefaultSpeed(Double.parseDouble(torsoe.getChildText("speed")));

            addProperties(torso, torsoe.getChildren("properties"));
            addCollisions(torso, torsoe.getChildren("collision"));

            addLimbs(torso,torsoe.getChildren("limb"));
            FighterBody tree = new FighterBody(torso,e.getChildText("name"), dis);
            tree.setHealth(Integer.parseInt(e.getChildText("health")));
            mapFighter(playerIndex, tree);
            playerIndex++;

            fs.add(tree);
        }

        fs.add(createAIStrategyFighter());
        return fs;
    }
    @SuppressWarnings("unchecked")
    private void addLimbs(LimbSprite torso, List<Element> elements){
        for (Element e:elements){
            LimbSprite s = new LimbSprite(e.getChildText("name"), 
                    c.getImage(e.getChildText("img")), 
                    torso,
                    Double.parseDouble(e.getChildText("x")),
                    Double.parseDouble(e.getChildText("y")),
                    Double.parseDouble(e.getChildText("damage")),
                    Integer.parseInt(e.getChildText("theta")));

            addProperties(s, e.getChildren("properties"));
            addCollisions(s,e.getChildren("collision"));
            addLimbs(s,e.getChildren("limb"));
            torso.addChild(s);
        }
    }

    private AIAgent createAIFighter() {
        LimbSprite body = new LimbSprite("ai1body",
                c.getImage("resources/flame.png"), GroupID.PLAYER_AI, 400, 500, 0);
        BasicAIAgent ai = new BasicAIAgent("ai1main", body, new HealthDisplay(
                50, 50, c.getWidth() / 2 - 30), 0, c);

        ai.setRoot(body);
        return ai;
    }

    private AIAgent createAIStrategyFighter() {
        LimbSprite body = new LimbSprite("ai2body",
                c.getImage("resources/flame.png"), GroupID.PLAYER_AI, 400, 500, 0);
        BasicStrategyAgent ai = new SituationalStrategyAgent("ai2main", body,
                new HealthDisplay(50, 50, c.getWidth() / 2 - 30), 0, c);

        ai.setRoot(body);
        return ai;
    }

    private void mapFighter(int playerIndex, FighterBody s) {
        InputHandler h = c.getMyHandler();
        int[] map = InputHandler.defaultMapping(playerIndex);
        h.addKey(map[0], MotionAction.UP(s, myPhysicsEngine));
        h.addKey(map[1], MotionAction.DOWN(s, myPhysicsEngine));
        h.addKey(map[2], MotionAction.LEFT(s, myPhysicsEngine));
        h.addKey(map[3], MotionAction.RIGHT(s, myPhysicsEngine));
    }

    public ArrayList<PlatformBlock> createPlatforms() throws JDOMException {
        List<Element> b = findAllInstancesOfElement("Block");
        ArrayList<PlatformBlock> fs = new ArrayList<PlatformBlock>();
        for (Element e : b) {
            PlatformBlock s = new PlatformBlock(c.getImage(e
                    .getChildText("img")), GroupID.getIdFromString(e
                    .getChildText("id")));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                    Double.parseDouble(e.getChildText("y")));
            fs.add(s);
        }
        return fs;
    }
    
    public ArrayList<SpriteTemplate> createNPSprites() throws JDOMException {
        List<Element> b = findAllInstancesOfElement("Sprite");
        ArrayList<SpriteTemplate> fs = new ArrayList<SpriteTemplate>();
        for (Element e : b) {
            SpriteTemplate s = new SpriteTemplate(c.getImage(e
                    .getChildText("img")), GroupID.getIdFromString(e
                    .getChildText("id")));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                    Double.parseDouble(e.getChildText("y")));
            addProperties(s,e.getChildren("properties"));
            addCollisions(s,e.getChildren("collisions"));
            fs.add(s);
        }
        return fs;
    }
    private void addProperties(SpriteTemplate s,List<Element> props){
        for (Element e:props){
            String p=e.getChildText(DamageProperty.NAME);
            if (p!=null){
                s.addProperty(DamageProperty.NAME, new DamageProperty(Double.parseDouble(p)));
            }
        }
        //TODO: create property objects based on name
    }

    private void addCollisions(SpriteTemplate s,List<Element> coll){
        //TODO
    }
    
    public String getBackground() {
        return root.getChildText("bg");
    }

    private List<Element> findAllInstancesOfElement(String info)
            throws JDOMException {
        XPath xpath = XPath.newInstance("//" + info);
        List<Element> list = xpath.selectNodes(root);
        return list;
    }
}
