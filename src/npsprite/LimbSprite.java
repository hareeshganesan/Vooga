package npsprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import SpriteTree.GraphicsTest;

import npsprite.properties.DamageProperty;
import npsprite.properties.PropertyObject;

//for limb nodes on fighter sprites only
public class LimbSprite extends NodeSprite{
    protected FighterBody myPointer;
    
    private BufferedImage myCurrImage;
    protected BufferedImage myOrigImage;
    
    private double dx;
    private double dy;
    private int theta;

    final double DAMAGE_DEALT = -5;

    // to be implemented
    double damageMultiplier=1; // if this node is arm, damageMultiplier less than
                             // if node were torso

    /**
     * constructor for the torso (stored as root under fighterbody)
     */
    public LimbSprite(String name, BufferedImage image, GroupID g, double x, double y){
        super(image,g,x,y);
        this.myName = name;
        this.myOrigImage = image;
//        this.theta = baseTheta;
//        this.dx = x;
//        this.dy = y;
        
        this.addProperty(DamageProperty.NAME, new DamageProperty(DAMAGE_DEALT*damageMultiplier));
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
    public LimbSprite(String name, BufferedImage image, NodeSprite parent,GroupID g, double x, double y,int baseTheta){
        super(image,parent.getGroupID(), parent.getX()+x, parent.getY()+y);
        this.myName = name;
        this.myOrigImage = image;
        this.theta = baseTheta;
        this.dx = x;
        this.dy = y;
        
        this.addProperty(DamageProperty.NAME,new DamageProperty(DAMAGE_DEALT*damageMultiplier));
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
        myPointer.childRemoved(child);
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
        this.theta += dTheta;
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

    public void render(Graphics2D pen,double baseX, double baseY, int baseTheta){
        super.render(pen);
        
        double dx =Math.cos(Math.toRadians(baseTheta)) * this.dx - Math.sin(Math.toRadians(baseTheta)) * this.dy;
        double dy =Math.sin(Math.toRadians(baseTheta)) * this.dx + Math.cos(Math.toRadians(baseTheta)) * this.dy;

        draw((baseX + dx), (baseY + dy),this.theta+baseTheta);
        
        for(NodeSprite limb: this.children){
            ((LimbSprite)limb).render(pen, (baseX + dx), (baseY + dy), baseTheta);
        }
    }

    public FighterBody getMyPointer() {
        return myPointer;
    }
}
