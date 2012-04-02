package ChrarctorEditor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class FighterEditorView extends JPanel {
	private JFrame currentFrame;
	private FighterEditorModel myModel;
	// private JButton myChangeHead;
	private JButton myMakeChange;
	private ArrayList<JLabel> myLabels=new ArrayList<JLabel>();

	public FighterEditorView(FighterEditorModel m, JFrame p) {
		myModel = m;
		currentFrame = p;
		setLayout(new BorderLayout());
		add(makeOperatePanel(), BorderLayout.NORTH);
		add(makeInformationPanel(), BorderLayout.CENTER);
		enableButtons();
		update();
	}

	private JComponent makeOperatePanel() {
		JPanel panel = new JPanel();
		// myChangeHead=new JButton("Change Head");
		// myChangeHead.addActionListener(new ActionListener(){
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// myModel.changeHead(FighterEditorView.this);
		// update();
		// }});
		// panel.add(myChangeHead);
		for (final String s : Fighter.item) {
			JButton toAdd = new JButton("change the " + s);
			toAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					myModel.getFighter().set(s,
							JOptionPane.showInputDialog("please input " + s));
					update();
				}
			});
			panel.add(toAdd);

		}

		myMakeChange = new JButton("Make the Change");
		myMakeChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myModel.makeTheChange();
				currentFrame.dispose();
			}
		});
		panel.add(myMakeChange);
		return panel;
	}

	private JComponent makeInformationPanel() {
		JPanel panel = new JPanel();
		for (String s : Fighter.item) {
			JLabel l = new JLabel();
			panel.add(l);
			myLabels.add(l);
			panel.add(l);
		}
		return panel;
	}

	private void enableButtons() {
		// myChangeHead.setEnabled(true);
	}

	public void update() {
		for (int i = 0; i < Fighter.item.size(); i++)
			myLabels.get(i).setText(
					myModel.getFighter().get(Fighter.item.get(i)));
	}
}