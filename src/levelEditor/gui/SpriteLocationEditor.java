package levelEditor.gui;

/**
 * @author Peggy Li (pl59)
 */

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class SpriteLocationEditor extends JPanel {

	private JTextField xField;
	private JTextField yField;
	

	public SpriteLocationEditor () {

		xField = new JTextField("x", 4);
		yField = new JTextField("y", 4);
		
		JPanel xPanel = new JPanel();
		xPanel.add(new JLabel("x "));
		xPanel.add(xField);
		
		JPanel yPanel = new JPanel();
		yPanel.add(new JLabel("y "));
		yPanel.add(yField);
		
		add(xPanel);
		add(yPanel);

	}

	public Point getLocation () {
		try {
			int x = Integer.parseInt(xField.getText());
			int y = Integer.parseInt(yField.getText());
			return new Point(x, y);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}


}
