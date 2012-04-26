package npsprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import SpriteTree.GraphicsTest;

import npsprite.SpriteValues.DIR;
import npsprite.properties.DamageProperty;
import npsprite.properties.PropertyObject;

//for limb nodes on fighter sprites only
public class LimbSprite extends NodeSprite{
    protected FighterBody myPointer;
        
    // TODO to be implemented
    double damageMultiplier=1; // if this node is arm, damageMultiplier less than
                             // if node were torso

    /**
     * constructor for the torso (stored as root under fighterbody)
     */
    public LimbSprite(String name, BufferedImage image, GroupID g, double x, double y, double damage){
        super(image,g,x,y);
        this.myName = name;
        this.myOrigImage = image;
        
        this.addProperty(DamageProperty.NAME, new DamageProperty(damage*damageMultiplier));
    }
    
    /**
     * constructor for non-root limbs
     * @param name
     * @param image
     * @param parent
     * @param g
     * @param x
     * @param y
     * @param baseTheta
     */
    public LimbSprite(String name, BufferedImage image, NodeSprite parent, double x, double y, double damage,int baseTheta){
        super(parent, image,parent.getX()+x, parent.getY()+y,baseTheta);
        this.myName = name;
        this.myOrigImage = image;
        this.mutableTheta = baseTheta;
        this.defaultTheta = baseTheta;
        this.dx = x;
        this.dy = y;
        
        this.addProperty(DamageProperty.NAME,new DamageProperty(damage*damageMultiplier));
    }
    
    public void setFighter(FighterBody fighterBody) {
        myPointer=fighterBody;
        myID=fighterBody.getGroupID();
    }
    
    public void addChild(NodeSprite child) {
        super.addChild(child);
        myPointer.childAdded(child);
    }
    public void removeChild(NodeSprite child){
        super.removeChild(child);
    }
    
    /* PROPERTIES (mostly wrapping parent fighterbody) */
    public boolean hasProperty(String name){
        if (myPointer.hasProperty(name)){
            return true;
            }
        return super.hasProperty(name);
        }
            
    public PropertyObject getProperty(String name){
        PropertyObject o=myPointer.getProperty(name);
        if (o==null){
            return super.getProperty(name);
        }
        return o;
    }

    public void setMass(double mass){
        myPointer.setMass(mass);
    }
    public double getMass(){
        return myPointer.getMass();
    }
    /* IMAGERY */ 
    public void rotate(int dTheta)
    {   
        this.mutableTheta += dTheta;
    }
    public double getTheta(){
        return this.mutableTheta;
    }
    public double getDefaultTheta(){
        return this.defaultTheta;
    }

    public void setTheta(double expTheta){
        this.mutableTheta = expTheta;
    }
    public Integer roundTheta(double theta){
        Integer n = 0;
        n = (int) Math.round(theta);
        return n;
    }
    

    public void setPosition(int moveX, int moveY){
        super.setPosition(moveX, moveY);
    }
    
    public void draw(double x, double y, int theta){
        this.setX(x);
        this.setY(y);
        this.myCurrImage =GraphicsTest.rotate(this.myOrigImage,theta);
        this.setImage(this.myCurrImage);
    }

    public FighterBody getMyPointer() {
        return myPointer;
    }


    public void print() {
        System.out.println(this.Parent);
        System.out.println(this.dx);
        System.out.println(this.dy);
        System.out.println(this.mutableTheta);
    }
}
