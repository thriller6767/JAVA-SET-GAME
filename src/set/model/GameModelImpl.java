package set.model;

import java.util.ArrayList;
import java.util.List;

/**
 * GameModelImpl to make a game board.
 */
public class GameModelImpl extends GameModel {

    private DeckModel deck;
    private List<CardModel> currentCards;
    private List<CardModel> selectedCards;
    private boolean isPlaying;

    /**
     * Constructor for Game Model Implemented
     */
    public GameModelImpl() {
        selectedCards = new ArrayList<>(SET_NUM);
        currentCards = new ArrayList<>(CARD_TOTAL);
    }

    /**
     * Deals three cards into currentCards
     */
    @Override
    public void dealThreeCards() {
        for (int i = 0; i <= 2; i++) {
            currentCards.add(deck.dealCard());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * If the three selected cards make a set, the set is removed from the current cards.
     * If not, the selected cards are reset.
     */
    @Override
    public void removeSet() {

        for (CardModel selectedCard : selectedCards) {
            currentCards.remove(selectedCard);
        }

        clearSelectCards();

        setChanged();

        notifyObservers();

    }

    /**
     * @return true if the three selected cards make a set.
     */
    @Override
    public boolean isSet() {
        if (hasThreeCardsSelected()) {
            return (isSetParam(selectedCards.get(0), selectedCards.get(1), selectedCards.get(2)));
        } else {
            return false;
        }
    }

    /**
     * Add a given card to the selected cards list only when
     * the the given card is not already selected
     *
     * @param card to add
     */
    @Override
    public void addSelectedCards(CardModel card) {
        if (!selectedCards.contains(card)) {

            selectedCards.add(card);

        } else {
            selectedCards.remove(card);
        }
        card.toggleSeleted();

        setChanged();
        notifyObservers();
    }

    /**
     * @return true if the number of selected cards is 3, the number of cards in a set
     */
    @Override
    public boolean hasThreeCardsSelected() {
        return (selectedCards.size() == SET_NUM);
    }

    /**
     * Checks whether three CardModel objects make a set
     *
     * @param a card
     * @param b card
     * @param c card
     * @return true if a, b, c form a set.
     */
    @Override
    public boolean isSetParam(CardModel a, CardModel b, CardModel c) {
        return (checkColor(a, b, c) && checkShape(a, b, c)
                && checkShade(a, b, c) && checkNum(a, b, c));
    }


    /**
     * the following methods check whether
     * "A set msut be either all the same or all different in each individual feature"
     * from: http://www.setgame.com/sites/default/files/instructions/SET%20INSTRUCTIONS%20-%20ENGLISH.pdf
     *
     * @param a card
     * @param b card
     * @param c card
     * @return true if all the same or all different
     */
    private boolean checkColor(CardModel a, CardModel b, CardModel c) {
        return ((a.getColor() == b.getColor() &&
                b.getColor() == c.getColor() &&
                a.getColor() == c.getColor())
                || (a.getColor() != b.getColor() &&
                b.getColor() != c.getColor() &&
                a.getColor() != c.getColor()));
    }

    private boolean checkShape(CardModel a, CardModel b, CardModel c) {
        return ((a.getShape() == b.getShape() &&
                b.getShape() == c.getShape() &&
                a.getShape() == c.getShape())
                || (a.getShape() != b.getShape() &&
                b.getShape() != c.getShape()) &&
                a.getShape() != c.getShape());
    }

    private boolean checkShade(CardModel a, CardModel b, CardModel c) {
        return ((a.getShade() == b.getShade() &&
                b.getShade() == c.getShade() &&
                a.getShade() == c.getShade())
                || (a.getShade() != b.getShade() &&
                b.getShade() != c.getShade()) &&
                a.getShade() != c.getShade());
    }

    private boolean checkNum(CardModel a, CardModel b, CardModel c) {
        return ((a.getShapeNum() == b.getShapeNum() &&
                b.getShapeNum() == c.getShapeNum() &&
                a.getShapeNum() == c.getShapeNum())
                || (a.getShapeNum() != b.getShapeNum() &&
                b.getShapeNum() != c.getShapeNum() &&
                a.getShapeNum() != c.getShapeNum()));
    }

    /**
     * @return a list of sets, which are lists of cards
     */
    @Override
    public List<List<CardModel>> getAllSets() {
        List<List<CardModel>> allSets = new ArrayList<>();
        for (int i = 0; i < currentCards.size(); i++) {                  // start at the first index
            for (int j = (i + 1); j < currentCards.size(); j++) {        // start at the second index
                for (int k = (j + 1); k < currentCards.size(); k++) {    // start at the third index

                    CardModel card1 = currentCards.get(i);
                    CardModel card2 = currentCards.get(j);
                    CardModel card3 = currentCards.get(k);

                    if (isSetParam(card1, card2, card3)) {
                        List<CardModel> addSet = new ArrayList<>();
                        addSet.add(card1);
                        addSet.add(card2);
                        addSet.add(card3);
                        allSets.add(addSet);
                        break;
                    }
                }
                break;
            }
        }
        return allSets;
    }

    /**
     * @return the current cards as a Set
     */
    @Override
    public List<CardModel> getCurrentCards() {
        return currentCards;
    }

    /**
     * @return the selected cards as a set
     */
    @Override
    public List<CardModel> getSelectedCards() {
        return selectedCards;
    }

    /**
     * @return true if the user is playing
     */
    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * @return true if the deck has three or more cards remaining
     * and the current cards are less or equal to 12
     */
    @Override
    public boolean canDealThree() {
        if ((currentCards.size() <= CARD_TOTAL) && (deck.hasThreeCardsRemaining())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clear all the selected cards
     */
    @Override
    public void clearSelectCards() {
        for (CardModel selectedCard : selectedCards) {
            selectedCard.toggleSeleted();
        }
        selectedCards.clear();

        setChanged();
        notifyObservers();
    }

    /**
     * @return one set from the current cards,
     * or return an empty list if there is no set
     */
    @Override
    public List<CardModel> findOneSet() {
        List<CardModel> addSet = new ArrayList<>();
        for (int i = 0; i < currentCards.size(); i++) {                  // start at the first index
            for (int j = (i + 1); j < currentCards.size(); j++) {        // start at the second index
                for (int k = (j + 1); k < currentCards.size(); k++) {    // start at the third index

                    CardModel card1 = currentCards.get(i);
                    CardModel card2 = currentCards.get(j);
                    CardModel card3 = currentCards.get(k);

                    if (isSetParam(card1, card2, card3)) {

                        addSet.add(card1);
                        addSet.add(card2);
                        addSet.add(card3);

                        return addSet;
                    }
                }
            }
        }
        return addSet;
    }

    /**
     * Deals 12 cards for the start of the SET game
     */
    private void dealTwelveCards() {
        for (int i = 0; i < CARD_TOTAL; i++) {
            currentCards.add(deck.dealCard());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Creates a new SET game
     */
    public void newGame() {
        this.deck = new DeckModelImpl();
        this.isPlaying = true;
        deck.shuffleDeck();
        currentCards.clear();
        selectedCards.clear();
        dealTwelveCards();
        setChanged();
        notifyObservers();
    }

    public int getCardModelIndex(CardModel cardModel) {
        return currentCards.indexOf(cardModel);
    }

    public CardModel getCardModelByIndex(int index) {
        return currentCards.get(index);
    }

}
