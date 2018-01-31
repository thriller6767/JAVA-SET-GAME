package set.controller;

import set.MainListener;
import set.model.GameModel;
import set.view.GameView;
import set.view.GameViewListener;
import set.view.toolbar.ToolbarListener;

/**
 * Controller to connect view and model
 */
public abstract class GameController implements ToolbarListener, GameViewListener {

    protected GameModel gameModel;
    protected GameView gameView;
    protected MainListener mainListener;

    /**
     *
     * @param m GameModel
     * @param v GameView
     * @param main mainListener to decide whether go to mainmenu or game
     */
    public GameController(GameModel m, GameView v, MainListener main) {
        gameModel = m;
        gameView = v;
        mainListener = main;
    }


    /**
     * Sets the MainListener with the given GameView.
     *
     * @param mainListener MainlListener to set.
     */
    public void setMainListener(MainListener mainListener) {
        this.mainListener = mainListener;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * It initiate a new game
     */
    @Override
    public void newGame() {
        gameModel.newGame();
    }
}
