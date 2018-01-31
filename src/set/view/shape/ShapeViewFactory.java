package set.view.shape;

import set.model.CardModel;

import java.awt.*;

/**
 * Factory class that draws Shape on the card model.
 */
public class ShapeViewFactory {

    private ShapeViewFactory() {
        // No instance
    }

    /**
     * Returns the corresponding ShapeView for a specific CardModel.
     *
     * @param cardModel Card model
     * @return New instance of ShapeView.
     */
    public static ShapeView buildShapeView(CardModel cardModel) {
        CardModel.Shape shape = cardModel.getShape();
        Color color = cardModel.getColor();
        CardModel.Shade shade = cardModel.getShade();
        if (shape == CardModel.Shape.SQUARE) {
            return new SquareShapeView(color, shade);
        } else if (shape == CardModel.Shape.CIRCLE) {
            return new CircleShapeView(color, shade);
        } else {
            return new DiamondShapeView(color, shade);
        }
    }
}

