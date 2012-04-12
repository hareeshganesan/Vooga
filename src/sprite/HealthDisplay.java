package sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;


public class HealthDisplay
{
    GameFont font;
    BufferedImage HPframe;
    double HPLength;
    double HPincreLength;

    int xCoord;
    int yCoord;
    int size;

    double fighterHP;
    String fighterName;

    Timer speed;

    boolean alive;


//	public HealthDisplay(GameFont font, BufferedImage HPframe, int x, int y, int size){
    public HealthDisplay (int x, int y, int size)
    {

        this.xCoord = x;
        this.yCoord = y;
        this.size = size;
        //possibly coordinates, customizable increment image, fighter portrait 
        //this.font =font;
        //this.HPframe = HPframe;
        //this.HPincrement = HPincrement;
        speed = new Timer(20);

    }


    public void setStat (String fighterName, int fighterMaxHP)
    {

        alive = true;
        this.fighterName = fighterName;
        this.fighterHP = fighterMaxHP;

        //HPLength = HPframe.getWidth();
        HPLength = this.size;
        HPincreLength = HPLength / fighterMaxHP;
//		System.out.println("this.sizs is " + this.size);
//	System.out.println("this.fighterMax" + fighterMaxHP);
    }


    public void update (long elapsedTime, int currentHP)
    {
        if (alive)
        {
            if (speed.action(elapsedTime))
            {

                fighterHP = currentHP;
                if (currentHP == 0)
                {
                    alive = false;

                }
            }
        }
    }


    public void render (Graphics2D pen)
    {
        //notes: add customizable x, y coordinates, customizable health frame

        //pen.drawImage(HPframe, 10, 10, null);
        pen.setColor(Color.red);

        pen.fillRect(this.xCoord,
                     this.yCoord,
                     (int) (HPincreLength * fighterHP),
                     10);
//		System.out.println("(HPincreLength *fighterHP) is " + (HPincreLength *fighterHP));
//		System.out.println("hpincre is " + HPincreLength);
//		System.out.println("fighterHP is "+ fighterHP);
//		
        //font.drawString(pen, fighterName, this.xCoord,this.yCoord+10);
        pen.drawString(fighterName, this.xCoord, this.yCoord);
    }

}
