package set.view.shape;

import set.model.CardModel;

import java.awt.*;
/**
 * Subclass the renders a circle on the card.
 */
public class CircleShapeView extends ShapeView {

    /**
     *  Constructor for the CircleShapeView.
     * @param color Color of the circle.
     * @param shade Shade of the circle.
     */
    public CircleShapeView(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (shade != CardModel.Shade.OPEN) {
            drawCircle(g, true);

            if (shade == CardModel.Shade.STRIPED) {
                shadeStripped(g);
            }
        }

        drawCircle(g, false);

    }

    private void drawCircle(Graphics g, boolean filled) {
        g.setColor(color);

        int xPoints = getWidth()/2 - SHAPE_WIDTH/2;
        int yPoints = getHeight()/2 - SHAPE_HEIGHT/2;

        if (!filled) {
            g.drawOval(xPoints,yPoints,SHAPE_WIDTH,SHAPE_HEIGHT);
        } else {
            g.fillOval(xPoints,yPoints,SHAPE_WIDTH,SHAPE_HEIGHT);
        }
    }
}
