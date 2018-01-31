package set.model;

import java.io.Serializable;

/**
 * Fluent Interface fot DeckModelImpl
 */
public interface DeckModel extends Serializable {

    /**
     * Shuffles the deck
     */
    void shuffleDeck();

    /**
     * Deals a card
     * @return a CardModel object
     */
    CardModel dealCard();

    /**
     * @return true iff there are 3 cards remaining (to be dealt)
     */
    boolean hasThreeCardsRemaining();

    /**
     * Fills the deck with card model objects
     */
    void populateDeck();
}
