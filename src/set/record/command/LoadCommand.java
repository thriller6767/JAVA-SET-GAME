package set.record.command;

import set.GameMode;
import set.controller.PlaybackGameController;
import set.model.GameModel;
import set.record.CommandConstants;
import set.record.SerializeUtils;
import set.view.GameView;

import java.io.IOException;

/**
 * A subclass to Command, this class will load and execute instructions.
 */
public class LoadCommand extends Command {

    public LoadCommand(PlaybackGameController playbackGameController, GameView gameView) {
        super(playbackGameController, gameView);
    }

    /**
     * Deserialize the instruction and update gameView
     * @param args to be deserialize
     */
    @Override
    public void execute(String args, GameModel gameModel) {
        try {
            GameModel game = SerializeUtils.<GameModel>deserializeObject(args);

            playbackGameController.setGameModel(game);
            gameView.setGameModel(game);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return the keyword LOAD
     */
    @Override
    public String getKeyword() {
        return CommandConstants.LOAD;
    }
}
