package SpriteTree;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.golden.gamedev.util.ImageUtil;

import com.golden.gamedev.object.Sprite;

public class LimbNode extends Sprite {

    private String myName;

    private BufferedImage myCurrImage;
    private BufferedImage myOrigImage;

    private double dx;
    private double dy;
    private double defaultTheta;
    private double mutableTheta;
    private LimbNode Parent;

    private HashMap<Integer, BufferedImage> myPreGenImgs = new HashMap<Integer, BufferedImage>();
    private HashMap<Integer, BufferedImage> myFlippedImgs = new HashMap<Integer, BufferedImage>();
    private boolean flipped = false;

    private ArrayList<LimbNode> children = new ArrayList<LimbNode>();

    // to be implemented
    double damageMultiplier;
    double damageDealt;

    // constructor for root node who doesn't have a parent
    public LimbNode(String name, BufferedImage image, double x, double y) {
        super(image, x, y);
        this.myName = name;
        this.myOrigImage = image;
    }

    // constructor for limbs

    public LimbNode(String name, LimbNode parent, BufferedImage image,
            double dx, double dy, int baseTheta) {
        super(image, parent.getX() + dx, parent.getY() + dy);
        this.myName = name;
        this.myOrigImage = image;
        this.Parent = parent;
        this.mutableTheta = baseTheta;
        this.defaultTheta = baseTheta;
        this.dx = dx;
        this.dy = dy;
    }

    public String getName() {
        return this.myName;
    }

    public void flip(boolean flip) {
        this.flipped = flip;
        for (LimbNode child : this.children) {
            child.flip(flip);
        }
    }

    public double getTheta() {
        return this.mutableTheta;
    }

    public double getDefaultTheta() {
        return this.defaultTheta;
    }

    public void setTheta(double expTheta) {
        this.mutableTheta = expTheta;
    }

    public ArrayList<LimbNode> getChildren() {

        return this.children;
    }

    public void rotate(double dTheta) {
        this.mutableTheta += dTheta;

    }

    public void addChild(LimbNode child) {
        children.add(child);

    }

    public void removeChild(LimbNode child) {
        children.remove(child);
    }

    public LimbNode getParent() {
        return this.Parent;
    }

    public Integer roundTheta(double theta) {
        Integer n = 0;
        n = (int) Math.round(theta);
        return n;
    }

    // refactor this code somehow

    public void draw(double x, double y, double theta) {
        this.setX(x);
        this.setY(y);
        Integer roundedTheta = roundTheta(theta);

        if (this.flipped == true) {
            if (this.Parent != null) {
                // coordinate flipped sprite images
                double dxFromCenter = (this.Parent.getX() + this.Parent
                        .getWidth() / 2) - x;
                if (dxFromCenter > 0) {
                    this.setX((this.Parent.getX() + this.Parent.getWidth() / 2)
                            + dxFromCenter);
                }
                if (dxFromCenter < 0) {

                    this.setX((this.Parent.getX() + this.Parent.getWidth() / 2)
                            - dxFromCenter);
                }
            }

            if (myFlippedImgs.containsKey(roundedTheta)) {
                this.setImage(myFlippedImgs.get(roundedTheta));
            } else {
                this.myCurrImage = GraphicsTest.rotate(this.myOrigImage, theta);
                this.myCurrImage = GraphicsTest.horizFlip(this.myCurrImage);
                this.setImage(this.myCurrImage);
                myFlippedImgs.put(roundTheta(theta), this.myCurrImage);
            }
        } else {

            if (myPreGenImgs.containsKey(roundedTheta)) {
                this.setImage(myPreGenImgs.get(roundedTheta));
            } else {
                this.myCurrImage = GraphicsTest.rotate(this.myOrigImage, theta);
                this.setImage(this.myCurrImage);
                myPreGenImgs.put(roundTheta(theta), this.myCurrImage);
            }
        }
    }

    public void render(Graphics2D pen, double baseX, double baseY,
            double baseTheta) {

        super.render(pen);

        double dx = Math.cos(Math.toRadians(baseTheta)) * this.dx
                - Math.sin(Math.toRadians(baseTheta)) * this.dy;
        double dy = Math.sin(Math.toRadians(baseTheta)) * this.dx
                + Math.cos(Math.toRadians(baseTheta)) * this.dy;

        draw((baseX + dx), (baseY + dy), this.mutableTheta + baseTheta);

        for (LimbNode limb : this.children) {
            limb.render(pen, (baseX + dx), (baseY + dy), this.mutableTheta
                    + baseTheta);
        }

    }

    public void update(long elapsedTime) {

        super.update(elapsedTime);
    }

    public void print() {
        System.out.println(this.Parent);
        System.out.println(this.dx);
        System.out.println(this.dy);
        System.out.println(this.mutableTheta);
    }

}
