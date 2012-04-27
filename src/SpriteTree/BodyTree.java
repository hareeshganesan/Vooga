package SpriteTree;

import java.awt.Graphics2D;
import java.util.HashMap;


public class BodyTree
{
    private LimbNode root;
    private HashMap<String, LimbNode> map;
    private boolean flipped = false;
    private HashMap<String, Animation> myAnimations;


    public BodyTree (LimbNode root)
    {
        this.root = root;
        map = new HashMap<String, LimbNode>();
        createMap(this.root);

    }


    public void setAnimations (HashMap<String, Animation> animations)
    {
        this.myAnimations = animations;
    }


    public Animation getAnimation (String animationName)
    {
        return myAnimations.get(animationName);
    }


    public void createMap (LimbNode currNode)
    {
        if (!map.containsKey(currNode.getName()))
        {
            map.put(currNode.getName(), currNode);
        }
        for (LimbNode limb : currNode.getChildren())
        {
            createMap(limb);
        }
    }


    public void attach (String parentName, LimbNode childNode)
    {
        LimbNode parent = map.get(parentName);
        parent.addChild(childNode);
        map.put(childNode.getName(), childNode);
    }


    public void flip (boolean flipped)
    {
        root.flip(flipped);
        this.flipped = flipped;
    }


    public boolean isFlipped ()
    {
        return this.flipped;
    }


    public void detach (String removeNode)
    {
        try
        {
            LimbNode node = map.get(removeNode);

            if (node.getChildren().size() != 0)
            {
                for (LimbNode child : node.getChildren())
                {
                    map.remove(child.getName());
                }
            }
            LimbNode parent = node.getParent();
            parent.removeChild(node);

            map.remove(removeNode);
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException("You have already removed limb: " +
                                           removeNode);
        }
    }


    public HashMap<String, LimbNode> getMap ()
    {
        return map;
    }


    public LimbNode getNode (String name)
    {
        return map.get(name);
    }


    public void render (Graphics2D pen)
    {
        root.render(pen, root.getX(), root.getY(), 0);
    }


    public void move (Graphics2D pen, double moveX, double moveY)
    {
        root.setX(root.getX() + moveX);
        root.setY(root.getY() + moveY);
    }


    public LimbNode getRoot ()
    {
        return root;
    }


    public String print (LimbNode currentNode)
    {
        String tree = currentNode.getName();
        if (currentNode.getChildren().size() == 0)
        {
            return currentNode.getName();
        }
        for (LimbNode child : currentNode.getChildren())
        {
            tree += print(child);
            tree += "--";
        }
        return tree;

    }
}
