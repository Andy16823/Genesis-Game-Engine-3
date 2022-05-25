package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Math.Rectangle;
import Genesis.Math.Vector2;
import Genesis.Toolkit;

import java.awt.*;

public class BoxCollider extends GameBehavior {
    private Color colliderColor = Toolkit.getColor(Color.RED, 100);
    private Vector2 size;
    private boolean debugMode = true;

    @Override
    public void afterRender(Graphics g) {
        super.afterRender(g);

        if(this.debugMode) {
            Color oldColor = g.getColor();
            g.setColor(colliderColor);

            int x = this.getParent().getLocation().getX();
            int y = this.getParent().getLocation().getY();
            int width = this.getParent().getSize().getX();
            int height = this.getParent().getSize().getY();

            if(this.size != null) {
                 x = this.getParent().getCenterLocation().getX() - (this.size.getX() / 2);
                 y = this.getParent().getCenterLocation().getY() - (this.size.getY() / 2);
                 width = this.size.getX();
                 height = this.size.getY();
            }
            g.fillRect(x,y, width, height);
            g.setColor(oldColor);
        }
    }

    public boolean collideWithElement(GameElement element) {
        Genesis.Math.Rectangle collider = this.getColliderBounds();
        if(collider.contains(element.getTopLeftCorner())) {
            return true;
        }
        else if(collider.contains(element.getTopRightCorner())) {
            return true;
        }
        else if(collider.contains(element.getBottomLeftCorner())) {
            return true;
        }
        else if(collider.contains(element.getBottomRightCorner())) {
            return true;
        }
        else if(collider.contains(element.getTopCenterLocation())) {
            return true;
        }
        else if(collider.contains(element.getLeftCenterLocation())) {
            return true;
        }
        else if(collider.contains(element.getRightCenterLocation())) {
            return true;
        }
        else if(collider.contains(element.getBottomCenterLocation())) {
            return true;
        }
        return false;
    }

    public boolean collideWithPoint(Vector2 point) {
        Rectangle parentRect = this.getColliderBounds();
        return  parentRect.contains(point.getX(), point.getY());
    }

    public Color getColliderColor() {
        return colliderColor;
    }

    public void setColliderColor(Color colliderColor) {
        this.colliderColor = colliderColor;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public Genesis.Math.Rectangle getColliderBounds() {
        int x = this.getParent().getLocation().getX();
        int y = this.getParent().getLocation().getY();
        int width = this.getParent().getSize().getX();
        int height = this.getParent().getSize().getY();

        if(this.size != null) {
            x = this.getParent().getCenterLocation().getX() - (this.size.getX() / 2);
            y = this.getParent().getCenterLocation().getY() - (this.size.getY() / 2);
            width = this.size.getX();
            height = this.size.getY();
        }
        return new Genesis.Math.Rectangle(x,y, width, height);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}
