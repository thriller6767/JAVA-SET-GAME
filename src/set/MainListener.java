package set;

/**
 * Interface that defines callbacks for {@link Main}.
 */
public interface MainListener {

    /**
     * Navigates to the Main menu screen.
     */
    void goToMainMenu();

    /**
     * Navigate to the game screen with the specified game mode.
     *
     * @param mode Game mode to play.
     */
    void goToGame(GameMode mode);
}
