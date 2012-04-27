package charactorEditor.drag.Component;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import charactorEditor.Controller;
import charactorEditor.Model.Sort;
import charactorEditor.drag.AttributePane;
import charactorEditor.drag.Update;

@SuppressWarnings("serial")
public class NameofFighter extends JTextField  implements Update{
	private AttributePane outer;
    private Controller myController=Controller.Instance();
	public NameofFighter(AttributePane e) {
		super("FigherName");
		outer = e;
		setBounds(4, 15, 173, 37);
		setFont(new Font("", Font.BOLD, 16));
		outer.register(this);
		outer.add(this);
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					myController.setFighterName(getText());
					myController.updateFigherBuilder();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
	}

	public void update() {

	}
}