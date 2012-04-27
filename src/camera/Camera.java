package camera;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

import npsprite.FighterBody;
import ai.AIAgent;

import com.golden.gamedev.object.Sprite;


@SuppressWarnings("serial")
public class Camera extends JPanel {
    
    public static final int X_OFFSET = 10;
    public static final int Y_OFFSET = 10;
    public static final int CANVAS_HEIGHT = 544;
    public static final int CANVAS_WIDTH = 544;
    
    private int MIN_X = 400;
    private int MIN_Y = 400;
    private int MAX_X = CANVAS_WIDTH;
    private int MAX_Y = CANVAS_HEIGHT;

    Point center;
    Point position = new Point(0, 0);
    Rectangle currentBounds;
    Rectangle targetBounds;

    public int prefHeight = 0;
    public int tolerance = 0;

    double zoom = 1;
    double zoomSpeed = 100;
    double timer = 0;
    double duration = 0;
    boolean inSpecialMode = false;
    SpecialCamera mySpec = null;
    
    public Camera ()
    {
        super(null);
        setOpaque(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        this.center = new Point(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        this.currentBounds = new Rectangle(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.targetBounds = new Rectangle(CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    
    public Camera(Point center, Rectangle bounds) {

        this.center = center;
        this.currentBounds = bounds;
        this.targetBounds = bounds;
    }
    
    public Camera(int x, int y) {
        this.center = new Point(0,0);
        this.currentBounds = new Rectangle(x,y);
        this.targetBounds = new Rectangle(x,y);
    }
    
    // get and set for min bounds
    public void setMinX(int newMin){ MIN_X = newMin; }
    public void setMinY(int newMin){ MIN_Y = newMin; }
    public int getMinX(){ return MIN_X; }
    public int getMinY(){ return MIN_Y; }
    
    // get and set for max bounds
    public void setMaxX(int newMax){ MAX_X = newMax; }
    public void setMaxY(int newMax){ MAX_Y = newMax; }
    public int getMaxX(){ return MAX_X; }
    public int getMaxY(){ return MAX_Y; }
    
    // get and set for current bounds
    public int getX (){ return currentBounds.x; }
    public int getY (){ return currentBounds.y; }
    public int getHeight (){ return currentBounds.height; }
    public int getWidth (){ return currentBounds.width; }

    public void setCenter (Point center) { this.center = center; }
    public Point getCenter (){ return center; }
    public void setBounds (Rectangle bounds) { this.currentBounds = bounds; }
    public Rectangle getBounds (){ return currentBounds;}
    
    public void calculateNewCenter (ArrayList<FighterBody> sprites)
    {
        double averageX = 0;
        double averageY = 0;

        for (FighterBody s : sprites)
        {
            averageX += s.getX();
            averageY += s.getY();
        }

        averageX = averageX / sprites.size();
        averageY = averageY / sprites.size();
        setCenter(new Point((int) averageX, (int) averageY));
    }


    public void calculateNewBounds (ArrayList<FighterBody> sprites)
    {
        double maxRadius = 0;
        
        for (int i = 0; i < sprites.size(); i++)
        {

            double radius =
                Math.sqrt(Math.pow(center.x - sprites.get(i).getX(), 2) + Math.pow(center.y - sprites.get(i).getY(), 2));
            if (radius > maxRadius)
                maxRadius = radius;
        }
        
        targetBounds.height = (int) ((2 * maxRadius) + Y_OFFSET);
        targetBounds.width = (int) ((2 * maxRadius) + X_OFFSET);
        
        maxRadius = maxRadius * Math.sqrt(2);

        targetBounds.x = (int) (center.x - maxRadius - X_OFFSET);
        targetBounds.y = (int) (center.y - maxRadius - Y_OFFSET);

        if (targetBounds.x < 0) targetBounds.x = 0;
        if (targetBounds.y < 0) targetBounds.y = 0;
        
        if (targetBounds.height > MAX_Y) targetBounds.height = MAX_Y + Y_OFFSET;
        if (targetBounds.width > MAX_X) targetBounds.width = MAX_X + X_OFFSET;
        
        if (targetBounds.height < MIN_Y) targetBounds.height = MIN_Y;
        if (targetBounds.width < MIN_X) targetBounds.width = MIN_X;
        
        currentBounds.x = (int) (currentBounds.x + ((targetBounds.x-currentBounds.x)/zoomSpeed));
        currentBounds.y = (int) (currentBounds.y + ((targetBounds.y-currentBounds.y)/zoomSpeed));
        currentBounds.height = (int) (currentBounds.height + ((targetBounds.height-currentBounds.height)/zoomSpeed));
        currentBounds.width = (int) (currentBounds.width + ((targetBounds.width-currentBounds.width)/zoomSpeed));
 
    }

    public void changeZoom(double zoom){
        this.zoom = zoom;
    }
    
    public void setZoomSpeed(double zoom)
    {
        this.zoomSpeed = zoom;
    }

    public double getZoom ()
    {
        return this.zoom;
    }

    public void follow (Sprite sprite)
    {

        position =
            (new Point((int) sprite.getX() + sprite.getWidth() / 2,
                       (int) sprite.getY() + sprite.getHeight() / 2));
    }

    public void update(ArrayList<FighterBody> playerSprites,
                        CameraBackground bg)
    {
        calculateNewCenter(playerSprites);
        calculateNewBounds(playerSprites);
        
        if (!inSpecialMode)
        {
            for (FighterBody sprite : playerSprites)
            {    
                if (sprite.getCollisionStatus().getStatus() && !sprite.getCollisionStatus().getStandOnSth() 
                        && FighterBody.class.isAssignableFrom(sprite.getClass()))
                {
                    notifySpecialMode(new SpecialShake(), 60);
                    break;
                }
            }
        }
        else if (inSpecialMode)
        {
            System.out.println("IN SPECIAL MODE");
            mySpec.update(playerSprites, bg, this, timer);
            timer++;
        }
        
        if (timer == duration)
        {
            inSpecialMode = false;
        }

    }
    
    public void notifySpecialMode(SpecialCamera spec, double duration)
    {
        timer = 0;
        mySpec = spec;
        inSpecialMode = true;
        this.duration = duration;
    }

    public void render (Graphics g1, CameraBackground bg)
    {
        Rectangle r = new Rectangle(5, 5, getWidth() - 10, getHeight() - 10);
        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.BLACK);
        g.draw(r);
        AffineTransform old = g.getTransform();
        AffineTransform tr2 = new AffineTransform(old);
        tr2.translate((this.getWidth() / 2) - (r.getWidth() * (zoom)) / 2,
                      (this.getHeight() / 2) - (r.getHeight() * (zoom)) / 2);
        tr2.scale(zoom, zoom);
        g.setTransform(tr2);
        
        g.draw(r);
        g.setTransform(old);
        super.paintComponent(g1);
       
    }
    
}
