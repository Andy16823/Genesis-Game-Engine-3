package Genesis;

import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;
import java.awt.image.BufferedImage;

public class Empty extends GameElement {

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     */
    public Empty(String Name, Vector2 Location, Vector2 Size) {
        super(Name, Location, Size, null, RenderMode.DYNAMIC);
    }

    /**
     * @param Name the name of the element
     */
    public Empty(String Name) {
        super(Name, new Vector2(0,0), new Vector2(0,0), null, RenderMode.DYNAMIC);
    }

}
