package set.view.toolbar;

import set.MainListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaybackToolbarView extends ToolbarView<PlaybackToolbarListener> {

    private static JButton nextButton = new JButton("Next");

    public PlaybackToolbarView(MainListener mainListener) {
        super(mainListener);
        iniPlayBackToolBar();
    }

    private void iniPlayBackToolBar(){
        nextButton.addActionListener(new NextListener());
        setBackground(new Color(100, 127, 180));

        this.add(nextButton);
        this.remove(newGameButton);
    }

    /**
     * What to do when NextButton is pressed.
     */
    private class NextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolbarListener.nextStep();

        }
    }
}
