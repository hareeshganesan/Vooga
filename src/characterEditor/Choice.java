package characterEditor;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


abstract class Choice extends JPanel
{
    protected Object myModel;
    protected Fighter myFighter;
    protected DefaultListModel mylitem;
    private JFrame currentFrame;


    public Choice (Object obj, Fighter fgt, JFrame p)
    {
        currentFrame = p;
        myModel = obj;
        myFighter = fgt;
        setLayout(new BorderLayout());
        add(makeOperatePanel(), BorderLayout.NORTH);
    }


    private JComponent makeOperatePanel ()
    {
        JPanel panel = new JPanel();
        mylitem = new DefaultListModel();
        JList list = new JList(mylitem);
        addElement();
        list.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged (ListSelectionEvent e)
            {
                JList l = (JList) e.getSource();
                Object s = l.getSelectedValue();
                setValue(s);
                update();
                currentFrame.dispose();
            }
        });
        panel.add(list);
        return panel;
    }


    abstract void update ();


    abstract void addElement ();


    abstract void setValue (Object s);
}
