package set.record.command;

import set.controller.PlaybackGameController;
import set.model.GameModel;
import set.view.GameView;

/**
 * This class is the super class of all command line objects, each of which is recorded with
 *      a keyword at first, and Base64-encoded gameModel
 */
public abstract class Command {

    protected PlaybackGameController playbackGameController;
    protected GameView gameView;

    /**
     *
     * @param playbackGameController
     * @param gameView
     */
    public Command(PlaybackGameController playbackGameController, GameView gameView) {
        this.playbackGameController = playbackGameController;
        this.gameView = gameView;
    }

    /**
     *
     * @param args  stirng decorded from Base64
     * @param gameModel to be loaded
     */
    public abstract void execute(String args, GameModel gameModel);

    /**
     *
     * @return the keyword of the command
     */
    public abstract String getKeyword();
}
