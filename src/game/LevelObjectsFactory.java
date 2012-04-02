package game;
import java.awt.image.BufferedImage;
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

public class LevelObjectsFactory {
    private Element root;
    private CombatInstance c;
    
    public LevelObjectsFactory(CombatInstance ci){
        c=ci;
    }
    public Element getRoot(File xmlFile) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(xmlFile);
        return doc.getRootElement();
    }
    
    public void parseFile(File xmlFile) throws IOException, JDOMException{
        root=getRoot(xmlFile);
    }
    public ArrayList<FighterSprite> createFighters() throws JDOMException{
        List<Element> fighters=findAllInstancesOfElement("Fighter");
        ArrayList<FighterSprite>fs=new ArrayList<FighterSprite>();
        
        ArrayList<HealthDisplay> displays = new ArrayList<HealthDisplay>();
        int playerNum = 1;

        //so far, only handles two player displays
        for (Element e:fighters){
        	 HealthDisplay dis;
             if(playerNum ==1){
             //display bar for the first player
             dis = new HealthDisplay(10, 10, c.getWidth()/2-30);

            playerNum+=1;
             
             }
             else{
             dis = new HealthDisplay((c.getWidth()/2), 10, c.getWidth()/2-30);
             }
             
        	FighterSprite s = new FighterSprite(e.getChildText("name"),dis,0);
        	
            s.setMaxHealth(Integer.parseInt(e.getChildText("health")));
            s.setLocation(Double.parseDouble(e.getChildText("x")), Double.parseDouble(e.getChildText("y")));
            s.setSpeed(Double.parseDouble(e.getChildText("speed")));
            s.setImages(c.getImages(e.getChildText("img"), 1, 1));
            
      
            fs.add(s);
        }
        return fs;
    }
    
    public ArrayList<PlatformBlock> createBlocks() throws JDOMException{
        List<Element> b=findAllInstancesOfElement("Block");
        ArrayList<PlatformBlock>fs=new ArrayList<PlatformBlock>();
        for (Element e:b){
            PlatformBlock s=new PlatformBlock(c.getImages(e.getChildText("image"), 1, 1));
            s.setLocation(Double.parseDouble(e.getChildText("x")), Double.parseDouble(e.getChildText("y")));
            fs.add(s);
        }
        return fs;
    }
    

    
    public String getBackground(){
        return root.getChildText("bg");
    }
    private List<Element> findAllInstancesOfElement(String info) throws JDOMException {
        XPath xpath=XPath.newInstance("//"+info);
        List<Element>list=xpath.selectNodes(root);
        return list;
    }
    
}
