package set.model;

import java.awt.*;
import java.io.Serializable;

/**
 * Fluent interface for CardModelImpl
 */
public interface CardModel extends Serializable {

    enum Shade {
        SOLID, STRIPED, OPEN
    }

    enum Shape {
        DIAMOND, CIRCLE, SQUARE
    }

    /**
     * @return color of the shape in card
     */
    Color getColor();

    /**
     * Change the color of the shape of the card
     *
     * @param color to change
     */
    void setColor(Color color);

    /**
     * @return the number of shapes
     */
    int getShapeNum();

    /**
     * Change the number of shapes in the card
     *
     * @param shapeNum
     */
    void setShapeNum(int shapeNum);

    /**
     * @return the shape
     */
    Shape getShape();

    /**
     * Change the shape in the card
     *
     * @param shape to change
     */
    void setShape(Shape shape);

    /**
     * @return Shade of the card
     */
    Shade getShade();

    /**
     * Change the shade of the shape of the card
     *
     * @param shade to change
     */
    void setShade(Shade shade);

    /**
     * Toggle the highlight color
     */
    void toggleSeleted();

    /**
     * @return true if the card is selected
     */
    boolean isSelected();
}
