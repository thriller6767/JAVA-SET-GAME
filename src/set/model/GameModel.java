package set.model;


import java.io.Serializable;
import java.util.List;
import java.util.Observable;

/**
 * Fluent interface for GameModelImpl
 */
public abstract class GameModel extends Observable implements Serializable {

    public static int SET_NUM = 3;
    public static int CARD_TOTAL = 12;
    public static int MAX_CARD_NUM = 15;

    /**
     * Deals three cards
     */
    public abstract void dealThreeCards();

    /**
     * Removes a set
     */
    public abstract void removeSet();

    /**
     * @return if the set is indeed a set
     */
    public abstract boolean isSet();

    /**
     * Adds a given CardModel to the selected cards array
     *
     * @param card
     */
    public abstract void addSelectedCards(CardModel card);

    /**
     * @return true if there are three cards currently selected
     */
    public abstract boolean hasThreeCardsSelected();

    /**
     * @return a list of the current cards
     */
    public abstract List<CardModel> getCurrentCards();

    /**
     * @return a list of the selected cards
     */
    public abstract List<CardModel> getSelectedCards();

    /**
     * @return a list of sets, which are lists of card
     */
    public abstract List<List<CardModel>> getAllSets();

    /**
     * @return true if the game is playing
     */
    public abstract boolean isPlaying();

    /**
     * @return true if number of current cards on board
     * is leass or equal to 12
     */
    public abstract boolean canDealThree();


    /**
     * this method will cancel all the selected cards
     * especially if player fails at choosing a set
     */
    public abstract void clearSelectCards();


    /**
     * @returns the first set found in current cards
     */
    public abstract List<CardModel> findOneSet();

    /**
     * Starts a new game
     */
    public abstract void newGame();

    /**
     * This is method is for JUnit Test
     * @param a
     * @param b
     * @param c
     * @return true if a, b, and c form a set
     */
    public abstract boolean isSetParam(CardModel a, CardModel b, CardModel c);

    /**
     *
     * @param cardModel to get index of
     * @return the index of the wanted cardModel
     */
    public abstract int getCardModelIndex(CardModel cardModel);

    /**
     *
     * @param index
     * @return CardModel of that index.
     */
    public abstract CardModel getCardModelByIndex(int index);
}
