package set.record.command;


import set.GameMode;
import set.controller.PlaybackGameController;
import set.model.CardModel;
import set.model.GameModel;
import set.record.CommandConstants;
import set.view.GameView;

/**
 * The Click Command Class
 */
public class ClickCommand extends Command {

    public ClickCommand(PlaybackGameController playbackGameController, GameView gameView) {
        super(playbackGameController, gameView);
    }

    /**
     *
     * @param args  serialized string encoded by Base64 code
     * @param gameModel to be loaded
     */
    @Override
    public void execute(String args, GameModel gameModel) {
        int index = Integer.valueOf(args);

        CardModel cardModel = gameModel.getCardModelByIndex(index);

        playbackGameController.cardClicked(cardModel);
    }

    /**
     *
     * @return keyword of ClickCard command
     */
    @Override
    public String getKeyword() {
        return CommandConstants.CLICK_CARD;
    }
}
