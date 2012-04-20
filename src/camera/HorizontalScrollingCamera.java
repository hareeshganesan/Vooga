package camera;

import java.awt.Graphics;
import java.util.ArrayList;

import sprite.FighterSprite;

public class HorizontalScrollingCamera extends Camera{
    
    public HorizontalScrollingCamera() {
        super();
    }
    
    @Override
    public void update(ArrayList<FighterSprite> playerSprites, CameraBackground bg) {
        super.update(playerSprites, bg);       
        bg.setToCenter(this.getX(), 0, this.getHeight(), this.getWidth());
    }
    
    public void render(Graphics g1, CameraBackground bg) {
        super.render(g1, bg);
    }
}
