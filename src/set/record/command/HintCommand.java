package set.record.command;


import set.controller.PlaybackGameController;
import set.model.GameModel;
import set.record.CommandConstants;
import set.view.GameView;

/**
 * This class is a subclass of Command and specifies the Hint action.
 */
public class HintCommand extends Command {

    public HintCommand(PlaybackGameController playbackGameController, GameView gameView) {
        super(playbackGameController, gameView);
    }

    /**
     *
     * @param args  stirng decoded from Base64
     * @param gameModel to be loaded
     */
    @Override
    public void execute(String args, GameModel gameModel) {
        playbackGameController.showHint();
    }

    /**
     *
     * @return the keyword of Hint command
     */
    @Override
    public String getKeyword() {
        return CommandConstants.HINT;
    }
}

