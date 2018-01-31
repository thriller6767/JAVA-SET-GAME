package set.view.toolbar;

import set.MainListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Extended class from ToolBarView.
 */
public class SoloToolbarView extends ToolbarView<SoloToolbarListener> {


    private static JButton addThreeButton = new JButton("Add Three Cards");
    private static JButton showHintButton = new JButton("Hint");

    /**
     * Constructor for SoloToolBarView.
     *
     * @param mainListener calls back the main menu.
     */
    public SoloToolbarView(MainListener mainListener) {
        super(mainListener);

        setBackground(new Color(240, 127, 180));
        iniSoloToolBar();

    }

    private void iniSoloToolBar() {
        addThreeButton.addActionListener(new AddThreeListener());
        showHintButton.addActionListener(new ShowHintListener());

        this.add(addThreeButton);
        this.add(showHintButton);
    }

    /**
     * What to do when ShowHintButton is pressed.
     */
    private class ShowHintListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolbarListener.showHint();
        }
    }

    /**
     * What to do when AddThreeButton is pressed.
     */
    private class AddThreeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolbarListener.drawThreeCards();
        }
    }
}
