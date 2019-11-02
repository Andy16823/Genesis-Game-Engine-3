package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Math.Vector2;
import javafx.print.Printer;

/**
 * A Controler for the movement in direction of a destination point
 */
public class DestinationMovementController extends GameBehavior {
    private Vector2 destination;

    /**
     * Sets the destination for the MovementController
     * @param dest the destination to set
     */
    public void setDestination(Vector2 dest) {
        this.destination = dest;
    }

    /**
     * Checks if the destination value exists
     * @return true if it exists otherwise false
     */
    public boolean hasDestination(){
        if(this.destination != null) {
            return true;
        }
        return  false;
    }

    /**
     * Perform a step in direction of the destination
     * @param speed the speed for the step
     */
    public void move(int speed)    {
        // Rotate the object
        int oX = this.getParent().getLocation().getX() + (this.getParent().getSize().getX() / 2);
        int oY = this.getParent().getLocation().getY() + (this.getParent().getSize().getY() / 2);
        float rAngel = (float) Math.atan2(destination.getY() - oY, destination.getX() - oX);
        this.getParent().setRotation(Math.toDegrees(rAngel));

        // move in the direction of the angel
        float mX = (float) (speed * Math.cos(rAngel));
        float mY = (float) (speed * Math.sin(rAngel));
        this.getParent().getLocation().addX((int) mX);
        this.getParent().getLocation().addY((int) mY);
    }

    /**
     * Returns the distance between the gameelement and the desitnation
     * @return the value
     */
    public float getDistance() {
        int distX = this.destination.getX() - (this.getParent().getLocation().getX() + this.getParent().getSize().getX() /2);
        int distY = this.destination.getY() - (this.getParent().getLocation().getY() + this.getParent().getSize().getY() /2);
        return (float) Math.sqrt((distX * distX) + (distY * distY));
    }

}
