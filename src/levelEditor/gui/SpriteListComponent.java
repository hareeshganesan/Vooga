package levelEditor.gui;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import levelEditor.mvc.Controller;


/**
 * @author Peggy Li
 */

@SuppressWarnings({ "unused", "serial" })
public class SpriteListComponent extends JComponent
{

    private Controller myController;

    private String[] myNames;

    private JList spriteList;

    private String currentSpriteType;


    public SpriteListComponent (Controller c)
    {
        myController = c;
        myNames = myController.getClassNamesArray();

        spriteList = new JList(myNames);
        spriteList.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged (ListSelectionEvent e)
            {
                currentSpriteType = (String) spriteList.getSelectedValue();

            }

        });

    }


    public JScrollPane getScrollable ()
    {
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().setView(spriteList);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scroll;
    }


    /**
     * Return name of sprite class from list currently selected by cursor
     */
    public String getSelectedType ()
    {
        return currentSpriteType;
    }

}
