package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.GameFont;


public class Title extends GameObject
{
    GameFont font;

    BufferedImage title;
    BufferedImage arrow;

    int option;


    public Title (GameEngine parent)
    {
        super(parent);
    }


    public void initResources ()
    {
        title = getImage("resources/title.png", false);

        font = fontManager.getFont(new Font("Serif", Font.BOLD, 20));
    }


    public void update (long elapsedTime)
    {
        switch (bsInput.getKeyPressed())
        {
            case KeyEvent.VK_1:
                parent.nextGameID = MainGame.GAME;
                finish();
//                break;
        }
    }


    public void render (Graphics2D g)
    {
        g.drawImage(title, 0, 0, null);

    }

}
