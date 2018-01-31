package set.model;

import java.awt.*;

/**
 * CardModelImpl to construct
 */
public class CardModelImpl implements CardModel {

    private Color color;
    private int shapeNum;
    private Shape shape;
    private Shade shade;
    private boolean isSelected;

    /**
     * Constructor for the CardModel Object
     * @param builder
     */
    private CardModelImpl(Builder builder) {
        this.color = builder.color;
        this.shapeNum = builder.shapeNum;
        this.shape = builder.shape;
        this.shade = builder.shade;
    }


    /**
     * Gets the color
     * @return
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color to a given color
     * @param color
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the number of shapes
     */
    @Override
    public int getShapeNum() {
        return shapeNum;
    }

    /**
     * Sets the number of shapes to a given integer
     * @param shapeNum
     */
    @Override
    public void setShapeNum(int shapeNum) {
        this.shapeNum = shapeNum;
    }

    /**
     * @return the type of Shape
     */
    @Override
    public Shape getShape() {
        return shape;
    }

    /**
     * Sets the shape to a given shape
     * @param shape
     */
    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * @return the type of shading
     */
    @Override
    public Shade getShade() {
        return shade;
    }

    /**
     * Sets the shade to a given shade
     * @param shade
     */
    @Override
    public void setShade(Shade shade) {
        this.shade = shade;
    }

    /**
     * Unselect the card
     */
    public void toggleSeleted() {
        isSelected = !isSelected;
    }

    /**
     * @return true if the card is selected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Builder for the CardModel Object
     */
    public static class Builder {

        private Color color;
        private int shapeNum;
        private Shape shape;
        private Shade shade;

        public Builder() {

        }

        /**
         * @param color
         * @return the builder with the given color
         */
        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        /**
         * @param shape
         * @return builder with given shape type
         */
        public Builder shape(Shape shape) {
            this.shape = shape;
            return this;
        }

        /**
         * @param shade
         * @return builder with given shade type
         */
        public Builder shade(Shade shade) {
            this.shade = shade;
            return this;
        }

        /**
         * @param shapeNum
         * @return builder with given shape number
         */
        public Builder shapeNumber(int shapeNum) {
            this.shapeNum = shapeNum;
            return this;
        }

        /**
         *
         * @return CardModel that is built
         */
        public CardModel build() {
            return new CardModelImpl(this);
        }


    }


}
