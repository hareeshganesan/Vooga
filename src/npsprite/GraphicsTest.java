package npsprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;

import SpriteTree.JImagePanel;

public class GraphicsTest {
	BufferedImage img;

	public static BufferedImage loadImage(String ref){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(ref));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return img;
	}
	

	public void loadAndDisplayImage(JFrame frame){
		BufferedImage loadImg = loadImage("src/resources/bodyParts/head.png");
		//this.img = rotate(loadImg,100);
		
		frame.setBounds(0,0, this.img.getWidth(),this.img.getHeight());
		JImagePanel panel = new JImagePanel(this.img,0,0);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public static BufferedImage rotate(BufferedImage img, int angle, double x, double y){
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(w,h,img.getType());
		Graphics2D g = dimg.createGraphics();
		g.rotate(Math.toRadians(angle),x,y);
		g.drawImage(img,null, 0,0);
		return dimg;
	}
	
	public static BufferedImage horizFlip(BufferedImage img){
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage (w, h, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.drawImage(img,0, 0, w, h, w,0,0, h, null);
		g.dispose();
		return dimg;
	}
	public static void main(String[] args){
		GraphicsTest lol = new GraphicsTest();
		JFrame frame = new JFrame("Image test");
		lol.loadAndDisplayImage(frame);

		
	}
	
}
