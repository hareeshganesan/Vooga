package PhysicsEngine;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import sprite.FighterSprite;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.util.ImageUtil;

public class MotionRotate extends Motion {

	public MotionRotate(GameObject game, int x, int y) {
		super(game, x, y);
	}

	@Override
	public boolean isThisMotion(FighterSprite sprite) {
		return myGame.keyPressed((KeyEvent.VK_T));
	}

	@Override
	public void doThisMotion(FighterSprite sprite, long elapsedTime) {
//		double speed = sprite.getSpeed();
		BufferedImage image=sprite.getImage();

		BufferedImage rotatedImage1 = ImageUtil.rotate(image, 45);
//		BufferedImage rotatedImage2 = ImageUtil.rotate(image, 90);
//		BufferedImage rotatedImage3 = ImageUtil.rotate(image, 0);
		BufferedImage[] array={rotatedImage1};
		sprite.setImages(array);

//		sprite.moveY(-getMovementLength(speed, elapsedTime) * 30);
//		sprite.moveX(getMovementLength(speed, elapsedTime)*20);

	}

}
