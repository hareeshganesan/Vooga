package npsprite;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import SpriteTree.GraphicsTest;

import npsprite.properties.DamageProperty;
import npsprite.properties.HealthProperty;

//for limb nodes on fighter sprites only
public class LimbSprite extends NodeSprite{

    private String myName;
    private BufferedImage myCurrImage;
    protected BufferedImage myOrigImage;

    private int allAngles = 0;
    private int currAngle = 0;
    
    private double dx;
    private double dy;
    private int theta;
    
    public LimbSprite(BufferedImage image, GroupID g, double x, double y) {
        super(image, g, x, y);
        this.myOrigImage = image;
        this.addProperty(DamageProperty.getName(),new DamageProperty(-5));
    }
    
    //WHAT IS BASE THETA?
    public LimbSprite(String name, BufferedImage image, NodeSprite parent,GroupID g, double x, double y,int baseTheta){
        super(image,parent.getGroupID(), parent.getX()+x, parent.getY()+y);
        this.myName = name;
        this.myOrigImage = image;
        this.myOrigImage= image;
        this.theta = baseTheta;
        this.dx = x;
        this.dy = y;
    }

    public String getName(){
        return this.myName;
    }
    public void rotate(int dTheta)
    {   
        this.theta += dTheta;
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
    
    public void update(long elapsedTime){
        if (myHealth<= 0) { //TODO: TRACK
            this.setActive(false);
        }
        super.update(elapsedTime);
    }

}
