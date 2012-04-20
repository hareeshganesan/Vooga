package camera;

import java.awt.Graphics;
import java.util.ArrayList;

import sprite.FighterSprite;

public class VerticalScrollingCamera extends Camera{
    
    public VerticalScrollingCamera() {
        super();
    }
    
    @Override
    public void update(ArrayList<FighterSprite> playerSprites, CameraBackground bg) {
        super.update(playerSprites, bg);       
        bg.setToCenter(0, this.getY(), this.getHeight(), this.getWidth());
    }
    
    public void render(Graphics g1, CameraBackground bg) {
        super.render(g1, bg);
    }
}
