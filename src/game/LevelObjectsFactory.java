package game;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import npsprite.FighterBody;
import npsprite.GroupID;
import npsprite.HealthDisplay;
import npsprite.NodeSprite;
import npsprite.PlatformBlock;
import npsprite.SpriteTemplate;
import npsprite.properties.DamageProperty;
import npsprite.properties.HealthProperty;
import npsprite.properties.PropertyObject;
import npsprite.properties.SpawnsProperty;
import npsprite.properties.TimerProperty;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import PhysicsEngine.PhysicsEngine;
import action.MotionAction;
import action.WeaponAction;
import ai.AIAgent;
import ai.BasicAIAgent;
import ai.BasicStrategyAgent;
import ai.SituationalStrategyAgent;
import events.ActivateTimerEvent;
import events.CollisionEvent;
import events.CompositeEvent;
import events.GravityEvent;
import events.HealthEvent;
import events.InactiveEvent;
import events.SpawnEvent;


public class LevelObjectsFactory
{
    private Element root;
    private CombatInstance c;
    private PhysicsEngine myPhysicsEngine;


    // TODO: SAVE LISTS OF ALL PROPERTYOBJECT SUBCLASSES AND COLLISIONEVENT
    // SUBCLASSES WHEN THIS CLASS IS LOADED - REFLECTION?

//    private static HashMap<String,CollisionEvent> myCollisions;
//    
//    static{
//        myCollisions=new HashMap<String,CollisionEvent>();
//        myCollisions.put(ActivateTimerEvent.getName(), ActivateTimerEvent.getInstanceOf());
//        myCollisions.put(CompositeEvent.getName(),CompositeEvent.getInstanceOf());
//        myCollisions.put(GravityEvent.getName(), GravityEvent.getInstanceOf());
//        myCollisions.put(HealthEvent.getName(), HealthEvent.getInstanceOf());
//        myCollisions.put(InactiveEvent.getName(), InactiveEvent.getInstanceOf());
//        myCollisions.put(SpawnEvent.getName(), SpawnEvent.getInstanceOf());
//    }

    public LevelObjectsFactory (CombatInstance ci)
    {
        c = ci;
        myPhysicsEngine = c.getPhysicsEngine();
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


    @SuppressWarnings("unchecked")
    public ArrayList<FighterBody> createNPFighters () throws JDOMException
    {
        List<Element> fighters = findAllInstancesOfElement("Fighter");
        ArrayList<FighterBody> fs = new ArrayList<FighterBody>();

        int playerNum = 1;
        int playerIndex = 0;
        // so far, only handles two player displays
        for (Element e : fighters)
        {
            HealthDisplay dis;
            if (playerNum == 1)
            {
                // display bar for the first player
                dis = new HealthDisplay(10, 10, (c.getWidth() / 2) - 30);

                playerNum += 1;

            }
            else
            {
                dis =
                    new HealthDisplay((c.getWidth() / 2),
                                      10,
                                      (c.getWidth() / 2) - 30);
            }
            Element torsoe = e.getChild("limb");
            NodeSprite torso =
                new NodeSprite(torsoe.getChildText("name"),
                               c.getImage(torsoe.getChildText("img")),
                               GroupID.getIdFromString(e.getChildText("id")),
                               Double.parseDouble(torsoe.getChildText("x")),
                               Double.parseDouble(torsoe.getChildText("y")),
                               Double.parseDouble(torsoe.getChildText("damage")));
            torso.setDefaultSpeed(Double.parseDouble(torsoe.getChildText("speed")));

            addProperties(torso, torsoe.getChildren("property"));
            torso.addCollisionEvents(getCollisions(torsoe.getChildren("collision")));

            FighterBody tree =
                new FighterBody(torso, e.getChildText("name"), dis);
            tree.setHealth(Integer.parseInt(e.getChildText("health")));

            addLimbs(torso, torsoe.getChildren("limb"));

            mapFighter(playerIndex, tree);
            playerIndex++;

//            System.out.println(torso.getGroupID()+" "+torso.getCollisionSize());
            fs.add(tree);
        }

        fs.add(createAIStrategyFighter());

        return fs;
    }


    @SuppressWarnings("unchecked")
    private void addLimbs (NodeSprite torso, List<Element> elements)
    {
        for (Element e : elements)
        {
            NodeSprite s =
                new NodeSprite(e.getChildText("name"),
                               torso,
                               c.getImage(e.getChildText("img")),
                               Double.parseDouble(e.getChildText("x")),
                               Double.parseDouble(e.getChildText("y")),
                               Double.parseDouble(e.getChildText("damage")),
                               Integer.parseInt(e.getChildText("theta")));

            addProperties(s, e.getChildren("property"));
            s.addCollisionEvents(getCollisions(e.getChildren("collision")));
            addLimbs(s, e.getChildren("limb"));
//            System.out.println("limb: " + s.getName());
            torso.addChild(s);
        }
    }


    private AIAgent createAIFighter ()
    {
        NodeSprite body =
            new NodeSprite("ai1body",
                           c.getImage("resources/flame.png"),
                           GroupID.PLAYER_AI,
                           400,
                           500,
                           0);
        BasicAIAgent ai =
            new BasicAIAgent("ai1main",
                             body,
                             new HealthDisplay(50, 50, c.getWidth() / 2 - 30),
                             0,
                             c);

//        body.setDefaultSpeed(Double.parseDouble(e.getChildText("speed"))); //TODO
        ai.setRoot(body);
        ai.setDefaultSpeed(1.5);
        return ai;
    }


    private AIAgent createAIStrategyFighter ()
    {
        NodeSprite body =
            new NodeSprite("ai2body",
                           c.getImage("resources/flame.png"),
                           GroupID.PLAYER_AI,
                           400,
                           500,
                           0);
        BasicStrategyAgent ai =
            new SituationalStrategyAgent("ai2main",
                                         body,
                                         new HealthDisplay(50,
                                                           50,
                                                           c.getWidth() / 2 - 30),
                                         0,
                                         c);

//        body.setDefaultSpeed(Double.parseDouble(e.getChildText("speed"))); //TODO
        ai.setRoot(body);

        return ai;
    }


    private void mapFighter (int playerIndex, FighterBody s)
    {
        InputHandler h = c.getMyHandler();
        int[] map = InputHandler.defaultMapping(playerIndex);
        h.addKey(map[0], MotionAction.UP(s, myPhysicsEngine));
        h.addKey(map[1], MotionAction.DOWN(s, myPhysicsEngine));
        h.addKey(map[2], MotionAction.LEFT(s, myPhysicsEngine));
        h.addKey(map[3], MotionAction.RIGHT(s, myPhysicsEngine));
        h.addKey(map[4], new WeaponAction(s, "weapon0", c));
        h.addKey(map[5], new WeaponAction(s, "weapon1", c));
    }


    public ArrayList<PlatformBlock> createPlatforms () throws JDOMException
    {
        List<Element> b = findAllInstancesOfElement("Block");
        ArrayList<PlatformBlock> fs = new ArrayList<PlatformBlock>();
        for (Element e : b)
        {
            PlatformBlock s =
                new PlatformBlock(c.getImage(e.getChildText("img")),
                                  GroupID.getIdFromString(e.getChildText("id")));
            s.setLocation(Double.parseDouble(e.getChildText("x")),
                          Double.parseDouble(e.getChildText("y")));
            fs.add(s);
        }
        return fs;
    }


    @SuppressWarnings("unchecked")
    public ArrayList<SpriteTemplate> createNPSprites () throws JDOMException
    {
        List<Element> b = findAllInstancesOfElement("Sprite");
        ArrayList<SpriteTemplate> fs = new ArrayList<SpriteTemplate>();
        for (Element e : b)
        {
            fs.add(createSprite(e));
        }
        return fs;
    }


    private SpriteTemplate createSprite (Element e)
    {
        SpriteTemplate s =
            new SpriteTemplate(c.getImage(e.getChildText("img")),
                               GroupID.getIdFromString(e.getChildText("id")));
        s.setLocation(Double.parseDouble(e.getChildText("x")),
                      Double.parseDouble(e.getChildText("y")));
        if (e.getChild("inactive") != null)
        {
            s.setActive(false);
        }
        addProperties(s, e.getChildren("property"));
        s.addCollisionEvents(getCollisions(e.getChildren("collision")));
        return s;
    }


    //TODO: refactor
    private void addProperties (SpriteTemplate s, List<Element> props)
    {
        for (Element e : props)
        {
            String p = e.getChildText(DamageProperty.NAME);
            if (p != null)
            {
                s.addProperty(DamageProperty.NAME,
                              new DamageProperty(Double.parseDouble(p)));
            }
            p = e.getChildText(HealthProperty.NAME);
            if (p != null)
            {
                s.addProperty(HealthProperty.NAME,
                              new HealthProperty(Double.parseDouble(p)));
            }
            p = e.getChildText(SpawnsProperty.NAME);
            if (p != null)
            {
                System.out.println("creating spawner");
                s.addProperty(SpawnsProperty.NAME, createSpawnsProp(e));
            }
            p = e.getChildText(TimerProperty.NAME);
            if (p != null)
            {
                s.addProperty(TimerProperty.NAME, createTimerProp(e));
            }
        }
    }


    private PropertyObject createTimerProp (Element e)
    {
        Element nexte = e.getChild(TimerProperty.NAME);
        int ms = Integer.parseInt(nexte.getText().trim());
        CollisionEvent ce =
            getCollision((Element) nexte.getChild("collision")
                                        .getChildren()
                                        .get(0));
        return new TimerProperty(ce, ms);
    }


    private PropertyObject createSpawnsProp (Element e)
    {
        Element nexte = e.getChild(SpawnsProperty.NAME);

        SpriteTemplate temp = createSprite(nexte.getChild("Sprite"));
        SpawnsProperty sp = new SpawnsProperty(c, temp);

        String n = nexte.getChildText("num");
        if (n != null)
        {
            sp.setNumber(Integer.parseInt(n));
        }
        List<Element> coord = nexte.getChildren("point");
        if (!coord.isEmpty())
        {
            List<Point2D> locs = new ArrayList<Point2D>();
            for (Element c : coord)
            {
                String xy = c.getText();
                double x = Double.parseDouble(xy.split(",")[0]);
                double y = Double.parseDouble(xy.split(",")[1]);
                locs.add(new Point2D.Double(x, y));
            }
            sp.setSpawnSpots(locs);
        }
        return sp;

    }


    //TODO: refactor
    private HashMap<GroupID, CollisionEvent> getCollisions (List coll)
    {
        HashMap<GroupID, CollisionEvent> allEvents =
            new HashMap<GroupID, CollisionEvent>();
        for (Object d : coll)
        {
            Element e = (Element) d;
            for (Object c : e.getChildren(HealthEvent.getName()))
            {
                String p = ((Element) c).getText();
//                allEvents.put(GroupID.getIdFromString(p), new HealthEvent());
                allEvents.put(GroupID.getIdFromString(p),
                              HealthEvent.getInstanceOf());
            }
            for (Object c : e.getChildren(InactiveEvent.getName()))
            {
                String p = ((Element) c).getText();
//                allEvents.put(GroupID.getIdFromString(p), new InactiveEvent());
                allEvents.put(GroupID.getIdFromString(p),
                              InactiveEvent.getInstanceOf());
            }
            for (Object c : e.getChildren(SpawnEvent.getName()))
            {
                String p = ((Element) c).getText();
//                allEvents.put(GroupID.getIdFromString(p), new SpawnEvent());
                allEvents.put(GroupID.getIdFromString(p),
                              SpawnEvent.getInstanceOf());
            }
            for (Object c : e.getChildren(GravityEvent.getName()))
            {
                String p = ((Element) c).getText();
//                allEvents.put(GroupID.getIdFromString(p), new SpawnEvent());
                allEvents.put(GroupID.getIdFromString(p),
                              GravityEvent.getInstanceOf());
            }

            for (Object c : e.getChildren(CompositeEvent.getName()))
            {
                String p = ((Element) c).getText();
                CompositeEvent ce = new CompositeEvent();
                for (Object o : ((Element) c).getChildren())
                { //i wish this could be done with recursion
                    ce.addEvent(getCollision((Element) o));
                }
                allEvents.put(GroupID.getIdFromString(p), ce);
            }
        }
        return allEvents;
    }


    /**
     * @param o the element that is the specific collision tag
     */
    private CollisionEvent getCollision (Element o)
    {
        if (o.getName().equals(HealthEvent.getName()))
        {
//            return new HealthEvent();
            return HealthEvent.getInstanceOf();
        }
        if (o.getName().equals(InactiveEvent.getName()))
        {
//            return new InactiveEvent();
            return InactiveEvent.getInstanceOf();
        }
        if (o.getName().equals(SpawnEvent.getName()))
        {
//            return new SpawnEvent();
            return SpawnEvent.getInstanceOf();
        }
        if (o.getName().equals(ActivateTimerEvent.getName()))
        {
            return ActivateTimerEvent.getInstanceOf();
        }
        if (o.getName().equals(GravityEvent.getName()))
        {
            return GravityEvent.getInstanceOf();
        }
        return null;
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
