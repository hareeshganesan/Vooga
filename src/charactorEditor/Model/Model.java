package charactorEditor.Model;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;


public class Model
{
    private ArrayList<MyComponent> componentList = new ArrayList<MyComponent>();
    private boolean setSizeFlag = false;
    private int willPut = -1;
    private boolean next = false;
    private ArrayList<String> properties = new ArrayList<String>();
    private static Model instance;


    public static Model Instance ()
    {
        if (instance == null) instance = new Model();
        return instance;
    }


    private Model ()
    {
        try
        {
            loadPropertyList();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<MyComponent> getComponentList ()
    {
        return componentList;
    }


    public void load (ArrayList<MyComponent> t)
    {
        HashMap<String, MyComponent> map = new HashMap<String, MyComponent>();
        for (MyComponent m : t)
        {
            map.put(m.getText(), m);
            m.resetChildren();
        }
        for (MyComponent m : t)
        {
            m.setParent(map.get(m.getParentForFile()));
            int numberofchilren = m.getChildrenForFile().size();
            for (int i = 0; i < numberofchilren; i++)
            {
                String s = m.getChildrenForFile().get(i);
                m.addChild(map.get(s));
            }
        }
        for (MyComponent m : t)
            for (int i = 0; i < m.getChildrenForFile().size() -
                                m.getChildern().size(); i++)
                m.getChildrenForFile().remove(0);
        componentList = t;
    }


    public MyComponent findComponent (Point2D e)
    {
        for (MyComponent m : componentList)
        {
            if (m.isInDragSize(e))
            {
                setSizeFlag = true;
                return m;
            }
            if (m.isInBorder(e))
            {
                setSizeFlag = false;
                return m;
            }
        }
        return null;
    }


    public String getName (MyComponent myComponent)
    {
        int count = 1;
        for (MyComponent m : componentList)
        {
            if (m.getSort() == myComponent.getSort())
            {
                if (count <= m.getSortID())
                {
                    count = m.getSortID() + 1;
                }
            }
        }
        myComponent.setSortID(count);
        return Sort.NAME[myComponent.getSort()] + count;
    }


    public ArrayList<String> getProperties ()
    {
        return properties;
    }


    @SuppressWarnings("unchecked")
    public void setProperties (Object toSet)
    {
        properties = (ArrayList<String>) toSet;
    }


    public boolean isPuttingComponent ()
    {
        return willPut == -1 ? false : true;
    }


    private void loadPropertyList () throws FileNotFoundException
    {

        try
        {
            Class<?> cls = Class.forName("npsprite.NodeSprite");
            Field fieldlist[] = cls.getDeclaredFields();
            ArrayList<String> list = new ArrayList<String>();
            list.add("GroupID");
            list.add("damage");
            list.add("baseTheta");
            list.add("myExpAngle");
            list.add("time");
            list.add("startTime");
            setProperties(list);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }


    public int getWillPut ()
    {
        return willPut;
    }


    public void setWillPut (int toSet)
    {
        willPut = toSet;
    }


    public void reSetWillPut ()
    {
        willPut = -1;
    }


    public boolean isNext ()
    {
        return next;
    }


    public void trueNext ()
    {
        next = true;
    }


    public void falseNext ()
    {
        next = false;
    }


    public boolean getSetSizeFlag ()
    {
        return setSizeFlag;
    }


    public void setFigherName (String s)
    {
        for (MyComponent m : this.componentList)
            m.setName(s);
    }


    public void setHealth (String s)
    {
        for (MyComponent m : this.componentList)
            m.setHealthProperty(s);
    }
//	public LimbNode buildLimbNodeTree(MyComponent root, LimbNode toReturn) {
//
//		if (root.getParent() == null) {
//			toReturn = new LimbNode(root.getText(), GraphicsTest.loadImage(root
//					.getImg().toString()), root.getBorderX(), root.getBorderY());
//		}
//
//		for (MyComponent m : root.getChildern()) {
//			double dx = m.getBorderX() - root.getBorderX();
//			double dy = m.getBorderY() - root.getBorderY();
//			LimbNode child = new LimbNode(m.getText(), toReturn,
//					GraphicsTest.loadImage(m.getImg().toString()), dx, dy,
//					Integer.valueOf(m.getProperties().get(properties.get(0))));
//			toReturn.addChild(child);
//		}
//		for (int i = 0; i < root.getChildern().size(); i++)
//			buildLimbNodeTree(root.getChildern().get(i), toReturn.getChildren()
//					.get(i));
//		return toReturn;
//	}
}
