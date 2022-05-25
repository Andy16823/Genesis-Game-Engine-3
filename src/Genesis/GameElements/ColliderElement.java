package Genesis.GameElements;

import Genesis.Behaviors.BoxCollider;
import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColliderElement extends GameElement {
    private BoxCollider collider;

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     */
    public ColliderElement(String Name, Vector2 Location, Vector2 Size) {
        super(Name, Location, Size, null, RenderMode.DYNAMIC);
        this.collider = new BoxCollider();
        this.addBehavior(collider);
    }

    @Override
    public void renderGameElement(Graphics2D g) {
        this.bevoreRender(g);
        //super.renderGameElement(g);
        this.afterRender(g);
    }

    public boolean collideWithElement(GameElement element) {
        return this.collider.collideWithElement(element);
    }

    public BoxCollider getCollider() {
        return collider;
    }

    public void setCollider(BoxCollider collider) {
        this.collider = collider;
    }

    public boolean collideWithPoint(Vector2 point) {
        return collider.collideWithPoint(point);
    }
}
