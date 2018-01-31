package set.controller;

import set.MainListener;
import set.model.CardModel;
import set.model.GameModel;
import set.view.DisplaySetView;
import set.view.GameView;
import set.view.GameViewListener;
import set.view.toolbar.TutorialToolbarListener;

import java.util.List;

/**
 * Subclass controller for Tutorial game mode.
 */
public class TutorialGameController extends GameController implements
        GameViewListener, TutorialToolbarListener {

    // the window for the player to cycle all the sets
    private DisplaySetView displaySetView;

    /**
     * TutorialGameController non-default Constructor
     * @param m
     * @param v
     * @param main
     */
    public TutorialGameController(GameModel m, GameView v, MainListener main) {
        super(m, v, main);
    }

    /**
     * Tutorial mode does not accept any clicks on card
     * @param c the card clicked
     */
    @Override
    public void cardClicked(CardModel c) {
    }

    /**
     * This method will initiate a new game and the displaySetView will dispose
     */
    @Override
    public void newGame() {
        super.newGame();
        if (displaySetView != null) {
            displaySetView.setVisible(false);
            displaySetView.dispose();
            displaySetView = null;
        }
    }

    /**
     * If user clicks hint, the game will highlight each set with
     * different colors
     */
    @Override
    public void showAllSets() {
        List<List<CardModel>> sets = gameModel.getAllSets();

        if (sets.size() == 0) {
            gameView.showErrorNoSetInGame();
        } else {
            this.displaySetView = new DisplaySetView(gameView, sets);
            displaySetView.setVisible(true);
        }

    }
}
