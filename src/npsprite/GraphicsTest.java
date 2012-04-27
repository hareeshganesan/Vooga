package npsprite;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;

import com.golden.gamedev.util.ImageUtil;


public class GraphicsTest
{
    BufferedImage img;


    public static BufferedImage loadImage (String ref)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(ref));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return img;
    }


	
	  public static BufferedImage rotate(BufferedImage src, double angle) {
          int w = src.getWidth(), h = src.getHeight(), transparency = src
                  .getColorModel().getTransparency();
          
          int maxD = Math.max(w, h);  
          BufferedImage image = ImageUtil.createImage(maxD,maxD, transparency);
           
          Graphics2D g = image.createGraphics();
          g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                  RenderingHints.VALUE_INTERPOLATION_BILINEAR);
          g.rotate(Math.toRadians(angle),  w/2, h/2);
          g.drawImage(src, 0, 0, null);
          g.dispose();
          
          return image;
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
	
}
