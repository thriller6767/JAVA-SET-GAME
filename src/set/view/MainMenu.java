package set.view;

import set.GameMode;
import set.MainListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JPanel class that represents the Main Menu of the game.
 */
public class MainMenu extends JPanel {

    private static final String SET_LABEL = "Set";
    private static final String TITLE_LABEL = "Main Menu";
    private static final String SOLO_BUTTON_LABEL = "Solo Mode";
    private static final String TUTORIAL_BUTTON_LABEL = "Tutorial Mode";
    private static final String QUIT_BUTTON_LABEL = "Quit";
    private static final String PLAYBACK_BUTTON_LABEL = "Playback Mode";
    private static final String RECORD_BUTTON_LABEL = "Record Mode";

    private MainListener mainListener;
    private JButton tutorial;
    private JButton solo;
    private JButton quit;
    private JButton playBack;
    private JButton record;

    /**
     * Constructor for creating a new Main Menu.
     *
     * @param mainListener Listener callbacks for the Main Japplet.
     */
    public MainMenu(MainListener mainListener) {
        this.mainListener = mainListener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initGUI();
    }

    private void initGUI() {

        JLabel setLabel = new JLabel(SET_LABEL);
        setLabel.setFont(new Font(getFont().getName(), Font.BOLD, 40));
        setCenterAlignment(setLabel);
        add(setLabel);

        JLabel menuLabel = new JLabel(TITLE_LABEL);
        menuLabel.setFont(new Font(getFont().getName(), Font.PLAIN, 30));
        setCenterAlignment(menuLabel);
        add(menuLabel);


        tutorial = new JButton(TUTORIAL_BUTTON_LABEL);
        setCenterAlignment(tutorial);
        tutorial.addActionListener(new TutorialButtonListener());
        add(tutorial);


        solo = new JButton(SOLO_BUTTON_LABEL);
        setCenterAlignment(solo);
        solo.addActionListener(new SoloButtonListener());
        add(solo);

        playBack = new JButton((PLAYBACK_BUTTON_LABEL));
        setCenterAlignment(playBack);
        playBack.addActionListener(new PlayBackActionListener());
        add(playBack);

        record = new JButton(RECORD_BUTTON_LABEL);
        setCenterAlignment(record);
        record.addActionListener(new RecordActionListener());
        add(record);

        quit = new JButton(QUIT_BUTTON_LABEL);
        setCenterAlignment(quit);
        quit.addActionListener(new QuitButtonListener());
        add(quit);


    }

    private void setCenterAlignment(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }


    /**
     * Called when the Solo button is pressed.
     */
    private class SoloButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(GameMode.SOLO);
        }
    }

    /**
     * Called when the Tutorial button is pressed.
     */
    private class TutorialButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(GameMode.TUTORIAL);
        }
    }

    /**
     * Called when the Quit button is pressed.
     */
    private class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class PlayBackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(GameMode.PLAYBACK);
        }
    }

    private class RecordActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(GameMode.RECORDING);
        }
    }

}
