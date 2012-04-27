package game;

import java.awt.Font;
import java.awt.Graphics2D;


public class Label extends Option
{

    public Label (String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }


    @Override
    void renderOption (Graphics2D pen, int x, int y)
    {
        pen.setFont(new Font("Serif", Font.BOLD, 20));
        pen.drawString(this.myName, x, y);

    }

}
