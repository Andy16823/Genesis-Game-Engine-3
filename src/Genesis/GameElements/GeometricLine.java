package Genesis.GameElements;

import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.image.BufferedImage;

public class GeometricLine extends GameElement {
    /**
     * @param Name The name of the element
     * @param pt1 The point 1 of the element
     * @param pt2 The point 2 of the element
     */
    public GeometricLine(String Name, Vector2 pt1, Vector2 pt2) {
        super(Name, pt1, Vector2.zero(), null, RenderMode.DYNAMIC);
    }
}
