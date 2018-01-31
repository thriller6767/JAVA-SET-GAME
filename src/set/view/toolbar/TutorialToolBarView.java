package set.view.toolbar;

import set.MainListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Extended class from ToolBarView.
 */
public class TutorialToolBarView extends ToolbarView<TutorialToolbarListener> {

    private static JButton getHintButton = new JButton("Show All Sets");

    /**
     * Constructor for TutorialToolBarView.
     * @param mainListener
     */
    public TutorialToolBarView(MainListener mainListener) {
        super(mainListener);
        setBackground(new Color(127, 227, 240));
        iniTutoToolBar();
    }

    private void iniTutoToolBar() {
        getHintButton.addActionListener(new HightLightListener());

        this.add(getHintButton);
    }

    /**
     * What to do when HighLightButton is pressed.
     */
    private class HightLightListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolbarListener.showAllSets();
        }
    }
}
