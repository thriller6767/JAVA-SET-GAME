package set.view;

import set.model.CardModel;
import set.model.GameModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * View class that renders the game board and the current 12/15 active cards.
 */
public class GameView extends JPanel implements Observer {

    private static final int GRID_COL = 3;
    private static final int GRID_ROW = 5;
    private static final int GRID_Y_SPACING = 30;
    private static final int GRID_X_SPACING = 30;
    private static final int GRID_OUTER_SPACING = 30;

    private GameModel gameModel;
    private Map<CardModel, CardView> cardViewMap;
    private GameViewListener controller;
    private Random rand;
    private boolean attachListeners;

    /**
     * Constructor for creating a new game view.
     *
     * @param gameModel GameModel to initialize from.
     */
    public GameView(GameModel gameModel) {
        super();
        this.gameModel = gameModel;
        this.cardViewMap = new HashMap<>();
        this.rand = new Random();
        this.attachListeners = true;
        gameModel.addObserver(this);

        setBorder(new EmptyBorder(GRID_OUTER_SPACING, GRID_OUTER_SPACING, GRID_OUTER_SPACING, GRID_OUTER_SPACING));
        setBackground(new Color(154, 217, 163));
        setLayout(new GridLayout(GRID_ROW, GRID_COL, GRID_X_SPACING, GRID_Y_SPACING));
        updateCardViews(gameModel.getCurrentCards());
    }

    private void updateCardViews(List<CardModel> cardModels) {
        removeAll();
        revalidate();

        cardViewMap.clear();

        for (CardModel cardModel : cardModels) {
            CardView cardView = new CardView(cardModel);
            cardViewMap.put(cardModel, cardView);

            attachClickListeners(cardView);
            add(cardView);
        }

        // Add blank child components to the grid to fill the
        // Remaining grid spaces
        int size = cardModels.size();
        if (size < GameModel.MAX_CARD_NUM) {
            for (int i = 0; i < GameModel.MAX_CARD_NUM - size; i++) {
                add(Box.createGlue());
            }
        }
    }

    private void attachClickListeners(CardView view) {
        if (attachListeners) {
            view.addMouseListener(new CardClickHandler());
        }
    }

    /**
     * Attaches the game controller to the current GameView
     *
     * @param controller Game controller to attach.
     */
    public void attachListener(GameViewListener controller) {
        this.controller = controller;
    }


    /**
     * Called automatically when the parent Observer object notifies its
     * observers.
     *
     * @param o Observable object
     * @param arg Arguments from the observable.
     */
    @Override
    public void update(Observable o, Object arg) {
        updateCardViews(gameModel.getCurrentCards());
    }

    /**
     * Shows a dialog window indicating that 3 cards cannot be dealt.
     */
    public void showErrorMaximumCards() {
        JOptionPane.showMessageDialog(null, "Cannot Deal Three Cards!");
    }

    /**
     * Shows a dialog window indicating that the cards selected are not a set.
     */
    public void showErrorNotASet() {
        JOptionPane.showMessageDialog(null, "Not A Set. Because two of ____ and one of _____ can not form a set.");
    }

    /**
     * Show a dialog window indicating that the game does not have a set
     */
    public void showErrorNoSetInGame() {
        JOptionPane.showMessageDialog(null, "Sorry, the game does not have a SET");
    }

    /**
     * Show a message dialog when there is no set can show in Solo game mode
     */
    public void showErrorNoSetInSolo() {
        JOptionPane.showMessageDialog(null, "Sorry, no SET in game, Please Add Three cards or click New Game.");
    }

    /**
     * Highlights each individual set with a new random color.
     *
     * @param set Single set.
     */
    public void highlightOneSet(List<CardModel> set) {
        unhighlightAllCards();

        Color color = getRandomColor();


        for (int j = 0; j < GameModel.SET_NUM; j++) {
            CardModel model = set.get(j);
            CardView view = cardViewMap.get(model);

            view.setHighlightColor(color);
            view.highlight();
        }
    }

    private void unhighlightAllCards() {
        for (CardView cardView : cardViewMap.values()) {
            cardView.unHighlight();
        }
    }

    /**
     * Gets a random dark color.
     *
     * @return new Random color.
     */
    private Color getRandomColor() {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b).darker();
    }

    /**
     * Called when a CardView is clicked.
     */
    private class CardClickHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            CardView cardView = (CardView) e.getComponent();
            controller.cardClicked(cardView.getCardModel());
        }
    }

    /**
     * Sets the GameModel of the current GameView.
     *
     * @param gameModel GameModel to set.
     */
    public void setGameModel(GameModel gameModel) {
        if (this.gameModel != null) {
            this.gameModel.deleteObserver(this);

            this.gameModel = gameModel;
            updateCardViews(gameModel.getCurrentCards());
            gameModel.addObserver(this);

        }
    }


    /**
     * Sets whether click listeners should be attached to the CardViews.
     *
     * @param attachListeners Yes, if true, else false.
     */
    public void setAttachListeners(boolean attachListeners) {
        this.attachListeners = attachListeners;
    }
}
