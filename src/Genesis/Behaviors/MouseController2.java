package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Math.Vector2;

public class MouseController2 extends GameBehavior {
    private Vector2 destination;

    public void move(int speed) {
        // Rotate
        int oX = this.getParent().getLocation().getX() + (this.getParent().getSize().getX() / 2);
        int oY = this.getParent().getLocation().getY() + (this.getParent().getSize().getY() / 2);
        float rAngel = (float) Math.atan2(destination.getY() - oY, destination.getX() - oX);
        this.getParent().setRotation(Math.toDegrees(rAngel));

        // Moving
        float x = (float) (speed * Math.cos(rAngel));
        float y = (float) (speed * Math.sin(rAngel));
        this.getParent().getLocation().addX((int) x);
        this.getParent().getLocation().addY((int) y);

    }

    /**
     * Set the destination point
     * @param dest
     */
    public void setDestination(Vector2 dest) {
        this.destination = dest;
    }


    public float getDistance() {
        float xDist = this.destination.getX() - (this.getParent().getLocation().getX() + this.getParent().getSize().getX() / 2);
        float yDist = this.destination.getY() - (this.getParent().getLocation().getY() + this.getParent().getSize().getY() / 2);

        return (float) Math.sqrt((xDist * xDist) + (yDist * yDist));
    }

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

}
