package set.record.command;

import set.controller.PlaybackGameController;
import set.model.GameModel;
import set.record.CommandConstants;
import set.view.GameView;

/**
 * This class delegates Command class, and specifies the action (ADD) in game as an object
 */
public class AddCommand extends Command {

    public AddCommand(PlaybackGameController playbackGameController, GameView gameView) {
        super(playbackGameController, gameView);
    }

    /**
     *
     * @param args String decoded from Base64
     * @param gameModel
     */
    @Override
    public void execute(String args, GameModel gameModel) {
        playbackGameController.drawThreeCards();
    }

    /**
     *
     * @return the keyword of Add Command
     */
    @Override
    public String getKeyword() {
        return CommandConstants.ADD_THREE;
    }
}