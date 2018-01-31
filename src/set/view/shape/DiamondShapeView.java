package set.view.shape;

import set.model.CardModel;

import java.awt.*;

/**
 * {@link ShapeView} subclass that renders a Diamond for a Card.
 */
public class DiamondShapeView extends ShapeView {

    /**
     * Creates a new DiamondShapeView
     *
     * @param color Color of Diamond
     * @param shade Shade of Diamond
     */
    public DiamondShapeView(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (shade != CardModel.Shade.OPEN) {
            drawDiamond(g, true);

            if (shade == CardModel.Shade.STRIPED) {
                shadeStripped(g);
            }
        }

        drawDiamond(g, false);

    }

    private void drawDiamond(Graphics g, boolean filled) {
        g.setColor(color);
        int paddingX = getWidth() / 2 - SHAPE_WIDTH / 2;
        int paddingY = getHeight() / 2 - SHAPE_HEIGHT / 2;
        int[] xPoints = {paddingX + SHAPE_WIDTH / 2, paddingX + SHAPE_WIDTH, paddingX + SHAPE_WIDTH / 2, paddingX};
        int[] yPoints = {paddingY, paddingY + SHAPE_HEIGHT / 2, paddingY + SHAPE_HEIGHT, paddingY + SHAPE_HEIGHT / 2};

        if (!filled) {
            g.drawPolygon(xPoints, yPoints, 4);
        } else {
            g.fillPolygon(xPoints, yPoints, 4);
        }
    }
}
