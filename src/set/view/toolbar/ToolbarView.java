package set.view.toolbar;

import set.MainListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Base abstract class that represents a Toolbar view.
 *
 * @param <T> Generic type of the listener that toolbar calls back to.
 */
public abstract class ToolbarView<T extends ToolbarListener> extends JPanel {

    private static final String MAIN_MENU_LABEL = "Main Menu";
    private static final String NEW_GAME_LABEL = "New Game";

    protected MainListener mainListener;
    protected T toolbarListener;
    protected JButton mainMenuButton;
    protected JButton newGameButton;

    /**
     * Constructor fot ToolBarVIew.
     * @param mainListener calls back to the main menu.
     */
    public ToolbarView(MainListener mainListener) {
        super();
        this.mainListener = mainListener;
        this.mainMenuButton = new JButton(MAIN_MENU_LABEL);
        this.newGameButton = new JButton(NEW_GAME_LABEL);
        iniToolBar();
    }


    private void iniToolBar() {

        mainMenuButton.addActionListener(new MainMenuListener());
        newGameButton.addActionListener(new NewGameListener());

        this.add(mainMenuButton);
        this.add(newGameButton);

    }


    public void attachListener(T listener) {

        this.toolbarListener = listener;
    }

    /**
     * What to do when MainMenuButton is pressed.
     */
    private class MainMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainListener.goToMainMenu();
        }

    }

    /**
     * What to do when NameGameButton is pressed.
     */
    private class NewGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolbarListener.newGame();

        }
    }

}
