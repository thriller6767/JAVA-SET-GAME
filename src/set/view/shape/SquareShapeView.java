package set.view.shape;

import set.model.CardModel;

import java.awt.*;

/**
 * Subclass the renders a square on the card.
 */
public class SquareShapeView extends ShapeView {

    /**
     * Constructor for the SquareShapeView.
     * @param color Color of the square.
     * @param shade Shade of the square.
     */
    public SquareShapeView(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (shade != CardModel.Shade.OPEN) {
            drawSquare(g, true);

            if (shade == CardModel.Shade.STRIPED) {
                shadeStripped(g);
            }
        }

        drawSquare(g, false);

    }

    private void drawSquare(Graphics g, boolean filled) {
        g.setColor(color);
        int xPoints = (getWidth()/2 - SHAPE_WIDTH/2);
        int yPoints = (getHeight()/2 - SHAPE_HEIGHT/2);

        if (!filled) {
            g.drawRect(xPoints,yPoints,SHAPE_WIDTH,SHAPE_HEIGHT);
        } else {
            g.fillRect(xPoints,yPoints, SHAPE_WIDTH, SHAPE_HEIGHT);
        }
    }

}
