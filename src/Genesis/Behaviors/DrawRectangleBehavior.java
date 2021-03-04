package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Math.Rectangle;

import java.awt.*;

public class DrawRectangleBehavior extends GameBehavior {
    private Rectangle rectangle;
    private Color gridColor;

    /**
     * create a new drawRectangleBehavior
     * @param rectangle
     * @param gridColor
     */
    public DrawRectangleBehavior(Rectangle rectangle, Color gridColor) {
        this.rectangle = rectangle;
        this.gridColor = gridColor;
    }

    @Override
    public void afterRender(Graphics g) {
        super.afterRender(g);
        Color oldColor = g.getColor();
        g.setColor(this.gridColor);
        g.drawRect(this.rectangle.getX(), this.rectangle.getY(), this.rectangle.getWidth(), this.rectangle.getHeight());
        g.setColor(oldColor);
    }

    /**
     * Returns the rectangle
     * @return
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Set the rectangle
     * @param rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Returns the gridColor
     * @return
     */
    public Color getGridColor() {
        return gridColor;
    }

    /**
     * Set the gridColor
     * @param gridColor
     */
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }
}
