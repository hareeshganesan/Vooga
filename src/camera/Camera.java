package camera;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;
import sprite.FighterSprite;
import com.golden.gamedev.object.Sprite;


@SuppressWarnings("serial")
public class Camera extends JPanel {
    
    public static final int X_OFFSET = 20;
    public static final int Y_OFFSET = 20;
    public static final int CANVAS_HEIGHT = 522;
    public static final int CANVAS_WIDTH = 522;

    Point center;
    Point position = new Point(0, 0);
    Rectangle bounds;

    public int prefHeight = 0;
    public int tolerance = 0;

    double zoom = 1;


    public Camera ()
    {
        super(null);
        setOpaque(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 200));
        this.center = new Point(0, 0);
        this.bounds = new Rectangle(200, 200);
    }
    
    public Camera(Point center, Rectangle bounds) {

        this.center = center;
        this.bounds = bounds;
    }
    
    public Camera(int x, int y) {
        this.center = new Point(0,0);
        this.bounds = new Rectangle(x,y);
    }


    public int getX ()
    {
        return bounds.x;
    }


    public int getY ()
    {
        return bounds.y;
    }


    public int getHeight ()
    {
        return bounds.height;
    }


    public int getWidth ()
    {
        return bounds.width;
    }


    public void setCenter (Point center)
    {
        this.center = center;
    }


    public Point getCenter ()
    {
        return center;
    }


    public void calculateNewCenter (ArrayList<FighterSprite> sprites)
    {
        double averageX = 0;
        double averageY = 0;

        for (Sprite s : sprites)
        {
            averageX += s.getX();
            averageY += s.getY();
        }

        averageX = averageX / sprites.size();
        averageY = averageY / sprites.size();

        setCenter(new Point((int) averageX, (int) averageY));
    }


    public void calculateNewBounds (ArrayList<FighterSprite> sprites)
    {
        double distanceX = center.x - sprites.get(0).getX();
        double distanceY = center.y - sprites.get(0).getY();

        double radius =
            Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

        bounds.x = (int) (center.x - radius - X_OFFSET);
        bounds.y = (int) (center.y - radius - Y_OFFSET);
        bounds.height = (int) (bounds.y + 2 * (radius + X_OFFSET));
        bounds.width = (int) (bounds.x + 2 * (radius + Y_OFFSET));
    }

    private void changeZoom(double zoom){
        this.zoom = zoom;

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


    public void update (ArrayList<FighterSprite> playerSprites,
                        CameraBackground bg)
    {
        calculateNewCenter(playerSprites);
        calculateNewBounds(playerSprites);
        changeZoom(bg.getX());        

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

//    public static void main(String[] args) {
//        JOptionPane.showMessageDialog(null, new Camera());
//    }

//        private float scaleFactor;
//     
//        private BufferedImage originalImage;
//     
//        public void setImage(BufferedImage image) {
//            this.originalImage = image;
//            this.setSize(image.getWidth(), image.getHeight());
//            //setSize does repainting, no need to call repaint()
//            //this.repaint();
//        }
//     
//        public void setScaleFactor(float scaleFactor) {
//            this.scaleFactor = scaleFactor;
//            this.repaint();
//        }
//     
//        @Override
//        public void paintComponent(Graphics g) {
//            if (this.originalImage != null) {
//                Graphics2D g2 = (Graphics2D) g;
//                int newW = (int) (originalImage.getWidth() * scaleFactor);
//                int newH = (int) (originalImage.getHeight() * scaleFactor);
//                this.setPreferredSize(new Dimension(newW, newH));
//                this.revalidate();
//                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                        //RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//                        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
//                        //RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//                g2.drawImage(originalImage, 0, 0, newW, newH, null);
//            }
//        }
//    }

}
