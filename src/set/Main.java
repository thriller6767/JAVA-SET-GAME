package set;

import set.view.MainMenu;

import javax.swing.*;
import java.awt.*;

/**
 * Entry point of the Set game. This class acts as a "ViewController" bridge
 * between Swing and our MVC design pattern of the Set game.
 * <p>
 * Main exposes MainListener which provides methods for going to the Main Menu
 * of the game and starting a game with a specific game mode.
 */

public class Main implements MainListener {

    private static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 800;
    private static final String MENU_FRAME_TITLE = "Set";
    private static final String SOLO_FRAME_TITLE = "Solo Mode";
    private static final String TUT_FRAME_TITLE = "Tutorial Mode";
    private static final String PLAYBACK_FRAME_TITLE = "Playback Mode";
    private static final String RECORD_FRAME_TITLE = "Recording Mode";
    private JFrame frame;

    /**
     * Constructor that invokes Java Swing UI.
     */
    private Main() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    /**
     * Main Java Entry Point
     *
     * @param args Java arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    /**
     * Initialize the Swing UI and show it.
     */
    private void createAndShowGUI() {
        frame = new JFrame(MENU_FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);

        // Start with the main menu
        goToMainMenu();

        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Sets the main panel of the Applet to be the main menu panel.
     */
    @Override
    public void goToMainMenu() {
        if (!(getContentPane() instanceof MainMenu)) {
            MainMenu mainMenu = new MainMenu(this);
            frame.setTitle(MENU_FRAME_TITLE);
            updateContentPane(mainMenu);
        }
    }

    /**
     * Sets the main panel of the Applet to be a new Set game with the specified
     * game mode.
     *
     * @param mode Game mode to play. See {@link GameMode} for more information
     * on game modes.
     */
    public void goToGame(GameMode mode) {
        if (!(getContentPane() instanceof Game)) {
            Game game = new Game(this, mode);
            frame.setTitle(getFrameTitle(mode));
            updateContentPane(game);
            game.newGame();
        }
    }

    private Container getContentPane() {
        return frame.getContentPane();
    }

    private void updateContentPane(Container pane) {
        // Setting size is really important here since the JFrame relies on its
        // Children components for size!
        pane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pane.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setContentPane(pane);
        frame.revalidate();
        frame.repaint();
    }

    private String getFrameTitle(GameMode mode) {
        if (mode == GameMode.SOLO) {
            return SOLO_FRAME_TITLE;
        } else if (mode == GameMode.TUTORIAL) {
            return TUT_FRAME_TITLE;
        } else if (mode == GameMode.RECORDING) {
            return RECORD_FRAME_TITLE;
        } else {
            return PLAYBACK_FRAME_TITLE;
        }

    }
}



