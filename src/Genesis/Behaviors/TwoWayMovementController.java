package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Math.Vector2;

public class TwoWayMovementController extends GameBehavior {
    private float rAngel;

    /**
     * Rotates the element to the destination point
     */
    public void rotateTo(Vector2 e) {
        int oX = this.getParent().getLocation().getX() + this.getParent().getSize().getX() / 2;
        int oY = this.getParent().getLocation().getY() + this.getParent().getSize().getY()  / 2;
        float rAngel = (float) Math.atan2(e.getY() - oY, e.getX() - oX);
        rAngel = (float) Math.toDegrees(rAngel);
        this.getParent().setRotation(rAngel);
    }

    /**
     * moves forwards
     */
    public void moveForward(int speed) {
        float rad = (float) Math.toRadians(this.getParent().getRotation());
        double x = speed * Math.cos(rad);
        double y = speed * Math.sin(rad);
        this.getParent().getLocation().addX((int) x);
        this.getParent().getLocation().addY((int) y);
    }

    /**
     * move backwards
     */
    public void moveBackward(int speed) {
        float rad = (float) Math.toRadians(this.getParent().getRotation());
        float x = (float) (speed * Math.cos(rad));
        float y = (float) (speed * Math.sin(rad));
        this.getParent().getLocation().addX((int) -x);
        this.getParent().getLocation().addY((int) -y);
    }

}
