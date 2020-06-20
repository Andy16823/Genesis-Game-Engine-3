package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Math.Vector2;
import Genesis.Scene;

public class SceneMovementController2 extends GameBehavior {
    private Vector2 destination;
    private Scene scene;
    private float rotation;

    public SceneMovementController2(Scene scene) {
        this.scene = scene;
    }

    /**
     * move the gameelement in direction of the destination, with speed n
     * @param speed the speed value
     */
    public void move(int speed) {

        int oX = this.getParent().getLocation().getX() + (this.getParent().getSize().getX() / 2);
        int oY = this.getParent().getLocation().getY() + (this.getParent().getSize().getY() / 2);
        float rAngel = (float) Math.atan2(destination.getY() - oY, destination.getX() - oX);

        // Moving
        float x = (float) (speed * Math.cos(rAngel));
        float y = (float) (speed * Math.sin(rAngel));

        this.scene.TransformScene((int) -x, (int) -y);
        this.destination.addXY((int) -x, (int) -y);
        this.getParent().getLocation().addXY((int) x, (int) y);
        //System.out.println("Destination X: " + this.destination.getX() + " Calculated Element X: " + (this.getParent().getLocation().getX() + this.scene.getLocation().getX()) + " Scene X: " + this.scene.getLocation().getX() + " " + x);
    }

    /**
     * Set the destination point
     * @param dest the destination
     */
    public void setDestination(Vector2 dest) {
        this.destination = dest;

        int oX = this.getParent().getLocation().getX() + (this.getParent().getSize().getX() / 2);
        int oY = this.getParent().getLocation().getY() + (this.getParent().getSize().getY() / 2);
        float rAngel = (float) Math.atan2(destination.getY() - oY, destination.getX() - oX);
        this.getParent().setRotation(Math.toDegrees(rAngel));
        this.rotation = rAngel;
    }

    /**
     * Returns the distance beetween both objects
     * @return the distance beetween the gameelement and the destination
     */
    public float getDistance() {
        float xDist = this.destination.getX() - (this.getParent().getLocation().getX() + this.getParent().getSize().getX() / 2);
        float yDist = this.destination.getY() - (this.getParent().getLocation().getY() + this.getParent().getSize().getY() / 2);

        return (float) Math.sqrt((xDist * xDist) + (yDist * yDist));
    }

    /**
     * Checks if the destination value is set
     * @return true if its set, false if its not set
     */
    public boolean hasDestination() {
        if(this.destination != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void clearDestination() {
        this.destination = null;
    }
}
