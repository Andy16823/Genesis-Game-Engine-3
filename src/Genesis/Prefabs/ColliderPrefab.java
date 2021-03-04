package Genesis.Prefabs;

import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColliderPrefab extends GameElement {
    private boolean renderCollider = false;
    private Color colliderColor;
    private Color colliderBorderColor;

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     */
    public ColliderPrefab(String Name, Vector2 Location, Vector2 Size) {
        super(Name, Location, Size, new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB), RenderMode.DYNAMIC);
        this.setTag("genesis_collider");
        this.colliderBorderColor = Color.GREEN;
        this.colliderColor = new Color(Color.green.getRed(), Color.green.getGreen(), Color.green.getBlue(), 170);
    }

    /**
     * check for collide
     * @param refElements the elements to check
     * @return
     */
    public boolean isCollide(GameElement[] refElements) {
        for(GameElement element : refElements) {
            if(this.intersects(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check for collide
     * @param refElement the element to check
     * @return
     */
    public boolean isCollide(GameElement refElement) {
        if(this.intersects(refElement)) {
            return true;
        }
        return false;
    }

    /**
     * check for collide
     * @param vector2 the point to check
     * @return
     */
    public boolean isCollide(Vector2 vector2) {
        if(this.contains(vector2)) {
            return true;
        }
        return  false;
    }

    /**
     * get the game element from the collide
     * @param refElements elements to check
     * @return
     */
    public GameElement getCollideElement(GameElement[] refElements) {
        for(GameElement element : refElements) {
            if(this.intersects(element)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public void afterRender(Graphics g) {
        super.afterRender(g);
        if(this.renderCollider) {
            Color oldColor = g.getColor();
            g.setColor(this.colliderColor);
            g.fillRect(this.getLocation().getX(), this.getLocation().getY(), this.getSize().getX(), this.getSize().getY());
            g.setColor(this.colliderBorderColor);
            g.drawRect(this.getLocation().getX(), this.getLocation().getY(), this.getSize().getX(), this.getSize().getY());
            g.setColor(oldColor);
        }
    }

    public boolean isRenderCollider() {
        return renderCollider;
    }

    public void setRenderCollider(boolean renderCollider) {
        this.renderCollider = renderCollider;
    }

    public Color getColliderColor() {
        return colliderColor;
    }

    public void setColliderColor(Color colliderColor) {
        this.colliderColor = colliderColor;
    }

    public Color getColliderBorderColor() {
        return colliderBorderColor;
    }

    public void setColliderBorderColor(Color colliderBorderColor) {
        this.colliderBorderColor = colliderBorderColor;
    }
}
