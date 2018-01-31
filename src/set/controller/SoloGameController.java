package set.controller;

import set.MainListener;
import set.model.CardModel;
import set.model.GameModel;
import set.view.GameView;
import set.view.GameViewListener;
import set.view.toolbar.SoloToolbarListener;

import java.util.List;

/**
 * Subclass controller for Solitaire game mode, it will update GameModel and GameView
 */
public class SoloGameController extends GameController implements GameViewListener, SoloToolbarListener {

    public SoloGameController(GameModel m, GameView v, MainListener main) {
        super(m, v, main);
    }

    /**
     * If less than three cards selected, the game will allow player to continue;
     * Else: if it is a set, remove the set and deal three more;
     * if not a set, cancel all the selected cards;
     *
     * @param cardModel the card get clicked
     */
    @Override
    public void cardClicked(CardModel cardModel) {
        gameModel.addSelectedCards(cardModel);

        if (gameModel.hasThreeCardsSelected())
            if (gameModel.isSet()) {
                //remove the set and deal three more;
                gameModel.removeSet();
                if (gameModel.getCurrentCards().size() < GameModel.CARD_TOTAL) {
                    gameModel.dealThreeCards();
                }
            } else {
                //if not a set, cancel all the selected cards;
                gameView.showErrorNotASet();
                gameModel.clearSelectCards();
            }
    }


    /**
     * This method will draw three cards from deck to the game board,
     * after user clicks addThreeCards button;
     * The maximum of cards in game is 15
     */
    @Override
    public void drawThreeCards() {
        // if current cards <= 13
        if (gameModel.canDealThree()) {
            gameModel.dealThreeCards();
        } else {
            gameView.showErrorMaximumCards();
        }
    }

    /**
     * It will only show one set of card
     */
    @Override
    public void showHint() {
        List<CardModel> set = gameModel.findOneSet();
        if (set.size() == 0) {
            gameView.showErrorNoSetInSolo();
        } else {
            gameView.highlightOneSet(set);
        }
    }

}
