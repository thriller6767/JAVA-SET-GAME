package set.controller;

import set.GameMode;
import set.MainListener;
import set.model.GameModel;
import set.view.GameView;

/**
 * Factory to build the controller with different modes (Tutorial / Solitare)
 */
public class GameControllerFactory {

    /**
     * This method builds the controller with given game mode
     *
     * @param mode the game mode player choose to play
     * @return the gameController corresponding to that game mode
     */
    public static GameController buildController(GameMode mode, GameModel gameModel, GameView gameView, MainListener main) {
        GameController gameController;
        if (mode == GameMode.TUTORIAL) {
            gameController = new TutorialGameController(gameModel, gameView, main);
        } else if (mode == GameMode.SOLO) {
            gameController = new SoloGameController(gameModel, gameView, main);
        } else if (mode == GameMode.RECORDING) {
            gameController = new RecordingController(gameModel, gameView, main);
        } else {
            gameController = new PlaybackGameController(gameModel, gameView, main);
        }
        return gameController;
    }
}
