package set.view;

import set.model.CardModel;
import set.view.shape.ShapeView;
import set.view.shape.ShapeViewFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * View that renders a Set card.
 */
public class CardView extends JPanel {

    private static final Border nullBorder = BorderFactory.createEmptyBorder();
    private static final Border selectedBorder = BorderFactory.createLineBorder(Color.BLACK);

    private CardModel cardModel;
    private Color highlightColor;
    private Color defaultColor;
    private List<ShapeView> shapeViewList;

    /**
     * Constructor for creating a new CardView.
     *
     * @param cardModel CardModel data to display as view.
     */
    public CardView(CardModel cardModel) {
        super();
        this.cardModel = cardModel;
        this.shapeViewList = new ArrayList<>();
        this.highlightColor = Color.YELLOW;
        this.defaultColor = Color.WHITE;

        setLayout(new GridLayout(1, 3));

        setBackground(Color.WHITE);

        addShapes();

        toggleSelection();

    }

    private void addShapes() {
        for (int i = 0; i < cardModel.getShapeNum(); i++) {
            ShapeView shapeView = ShapeViewFactory.buildShapeView(cardModel);
            add(shapeView);
            shapeViewList.add(shapeView);
        }
    }

    /**
     * Returns the CardModel that is represented as the CardView.
     *
     * @return
     */
    public CardModel getCardModel() {
        return cardModel;
    }

    private void toggleSelection() {
        if (cardModel.isSelected()) {
            setBorder(selectedBorder);
        } else {
            setBorder(nullBorder);
        }

    }

    /**
     * Highlights the specified CardView.
     * Call {@link #setHighlightColor(Color)} to set before calling.
     */
    public void highlight() {
        setBackground(highlightColor);

        for (ShapeView shapeView : shapeViewList) {
            shapeView.setHighlightColor(highlightColor);
        }
    }

    public void unHighlight() {
        setHighlightColor(defaultColor);
        highlight();
    }


    /**
     * Sets the highlight color of the CardView.
     *
     * @param highlightColor Color to highlight with.
     */
    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }
}

