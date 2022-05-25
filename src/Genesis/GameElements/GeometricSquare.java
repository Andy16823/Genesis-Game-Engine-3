package Genesis.GameElements;

import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeometricSquare extends GameElement {
    private Color borderColor;
    private Color fillColor;
    Stroke border;

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     */
    public GeometricSquare(String Name, Vector2 Location, Vector2 Size) {
        super(Name, Location, Size, null, RenderMode.DYNAMIC);
        this.borderColor = Color.GREEN;
        this.fillColor = null;
        this.border = new BasicStroke(2);
    }

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     */
    public GeometricSquare(String Name, Vector2 Location, Vector2 Size, Color BorderColor, Color FillColor, Stroke Border) {
        super(Name, Location, Size, null, RenderMode.DYNAMIC);
        this.borderColor = BorderColor;
        this.fillColor = FillColor;
        this.border = Border;
    }

    @Override
    public void renderGameElement(Graphics2D g) {
        //super.renderGameElement(g);
        this.bevoreRender(g);
        Color oldCol = g.getColor();

        if(this.fillColor != null) {
            g.setColor(this.fillColor);
            g.fillRect(this.getLocation().getX(), this.getLocation().getY(),this.getSize().getX(), this.getSize().getY());
            g.setColor(oldCol);
        }

        if(this.borderColor != null) {
            Stroke stroke = g.getStroke();
            g.setColor(this.borderColor);
            g.setStroke(this.border);
            g.drawRect(this.getLocation().getX(), this.getLocation().getY(),this.getSize().getX(), this.getSize().getY());
            g.setStroke(stroke);
            g.setColor(oldCol);
        }
        this.afterRender(g);
    }
}
