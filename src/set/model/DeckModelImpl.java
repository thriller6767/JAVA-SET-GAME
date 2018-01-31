package set.model;

import java.util.Collections;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

/**
 * DeckModel to build a deck of cards, which will be dealt to the game board.
 */
public class DeckModelImpl implements DeckModel {

    private List<CardModel> cards;
    private int cardIndex;

    /**
     * Contructor for DeckModelImpl
     */
    public DeckModelImpl() {
        cards = new ArrayList<>(81);
        populateDeck();
        cardIndex = 0;
    }

    /**
     * Shuffles the deck using Java's built in Collections Class
     */
    @Override
    public void shuffleDeck() {
        Collections.shuffle(cards);

    }

    /**
     * Deals a card from the front of the Arraylist of cards.
     * @return A CardModel Object
     */
    @Override
    public CardModel dealCard() {
        if (cardIndex < cards.size() - 1) {
            CardModel toReturn = cards.get(cardIndex);
            cardIndex++;                // increments the current card by one
            return toReturn;
        }else{
            throw new IllegalStateException("Less than 3 cards in the deck");
        }
    }

    /**
     * @return true if there are three cards remaining (if the index is 3 from the back of the array)
     */
    @Override
    public boolean hasThreeCardsRemaining() {
        if (cardIndex <= cards.size() - 3) { // If the card index equals the index of the second to last card
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fills the Deck (the arraylist of CardModel Objects) with 81 Cards
     */
    public void populateDeck() {

        Color[] colors = new Color[]{Color.red, Color.green, Color.blue};

        int i = 0;
        for (CardModel.Shape shapeType : CardModel.Shape.values()) {        // Iterate through the Shapes
            for (CardModel.Shade shadeType : CardModel.Shade.values()) {    // Iterate through the Shades
                for (int numShape = 1; numShape <= 3; numShape++) {         // Iterate through the Number of Shapes
                    for (Color c : colors) {                                // Iterate through the Colors
                        CardModel buildCard = new CardModelImpl.Builder()
                                .shape(shapeType)
                                .shade(shadeType)
                                .shapeNumber(numShape)
                                .color(c)
                                .build();
                        cards.add(i, buildCard);
                        i++;
                    }
                }
            }
        }
    }

}
