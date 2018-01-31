package set;

import set.controller.GameController;
import set.controller.GameControllerFactory;
import set.controller.SoloGameController;
import set.model.GameModel;
import set.model.GameModelImpl;
import set.view.GameView;
import set.view.toolbar.SoloToolbarView;
import set.view.toolbar.ToolbarFactory;
import set.view.toolbar.ToolbarView;
import set.view.toolbar.TutorialToolBarView;

import javax.swing.*;
import java.awt.*;

/**
 * "ViewController" that acts as the glue between the Game Model, View, and
 * Controller.
 */
public class Game extends JPanel {

    private final MainListener main;
    private GameController gameController;
    private GameModel gameModel;
    private GameView gameView;
    private final GameMode gameMode;

    /**
     * Constructor for creating a new Game object.
     *
     * @param main MainListener object.
     * @param mode Game mode of game type to create.
     */
    public Game(MainListener main, GameMode mode) {
        super();
        this.main = main;
        this.gameMode = mode;
        setLayout(new BorderLayout());

        initializeMVC();
        initializeToolbar();
    }


    private void initializeMVC() {
        gameModel = new GameModelImpl();
        gameView = new GameView(gameModel);

        gameController = GameControllerFactory.buildController(gameMode, gameModel, gameView, main);

        gameView.attachListener(gameController);

        add(gameView, BorderLayout.CENTER);
    }

    private void initializeToolbar() {
        ToolbarView toolbarView = ToolbarFactory.buildToolbar(gameMode, main);

        //noinspection unchecked
        toolbarView.attachListener(gameController);

        add(toolbarView, BorderLayout.NORTH);

    }

    /**
     * Calls the controller to initialize a new game.
     */
    public void newGame(){
        gameController.newGame();
    }

}
