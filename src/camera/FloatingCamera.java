package camera;

import java.awt.Graphics;
import java.util.ArrayList;

import sprite.FighterSprite;

public class FloatingCamera extends Camera{
    @Override
    public void update(ArrayList<FighterSprite> playerSprites, CameraBackground bg) {
        bg.setToCenter(this.getX(), this.getY(), this.getHeight(), this.getWidth());
        super.update(playerSprites, bg);       
    }
    
    public void render(Graphics g1, CameraBackground bg) {
        super.render(g1, bg);
    }
}
