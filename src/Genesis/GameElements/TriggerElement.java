package Genesis.GameElements;

import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;
import Genesis.Toolkit;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TriggerElement extends GameElement {
    private Color debugColor = Toolkit.getColor(Color.GREEN, 100);

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     */
    public TriggerElement(String Name, Vector2 Location, Vector2 Size) {
        super(Name, Location, Size, null, RenderMode.DYNAMIC);
    }

    @Override
    public void renderGameElement(Graphics2D g) {
        this.bevoreRender(g);

        this.afterRender(g);
    }
}
