package Genesis.Math;

import javafx.scene.effect.Light;

public class Rectangle {
    private  Vector2 location;
    private  Vector2 size;

    /**
     * Create a new instance of the rectangle class
     * @param x the x - coordinate
     * @param y the y - coordinate
     * @param width the width
     * @param height the height
     */
    public Rectangle(int x, int y, int width, int height) {
        this.location = new Vector2(x, y);
        this.size = new Vector2(width, height);
    }

    /**
     * Returns the x - coordinate
     * @return int
     */
    public int getX() {
        return this.location.getX();
    }

    /**
     * Set the x - coordinate
     * @param x int
     */
    public void setX(int x) {
        this.location.setX(x);
    }

    /**
     * Returns the y - coordinate
     * @return
     */
    public int getY() {
        return this.location.getY();
    }

    /**
     * Set the y coordinate
     * @param y
     */
    public void setY(int y) {
        this.location.setY(y);
    }

    /**
     * Returns the width value
     * @return
     */
    public int getWidth() {
        return this.size.getX();
    }

    /**
     * Sets the width value
     * @param width
     */
    public void setWidth(int width) {
        this.size.setX(width);
    }

    /**
     * Returns the height value
     * @return
     */
    public int getHeight() {
        return this.size.getY();
    }

    /**
     * Set the height value
     * @param height
     */
    public void setHeight(int height) {
        this.size.setY(height);
    }

    /**
     * Add value to the x coordinate
     * @param x
     */
    public void addX(int x) {
        this.location.addX(x);
    }

    /**
     * Add value to the y coordinate
     * @param y
     */
    public void addY(int y) {
        this.location.addY(y);
    }

    /**
     * Add value to the x and y coordinate
     * @param x
     * @param y
     */
    public void addXY(int x, int y) {
        this.location.addXY(x, y);
    }

    /**
     * Add value to the width
     * @param width
     */
    public void addWidth(int width) {
        this.size.addX(width);
    }

    /**
     * add value to the height
     * @param height
     */
    public void addHeight(int height) {
        this.size.addY(height);
    }

    /**
     * Add value to width and height
     * @param width
     * @param height
     */
    public void addWidthHeight(int width, int height) {
        this.size.addXY(width, height);
    }

    /**
     * Returns the location as vector2
     * @return
     */
    public Vector2 getLocation() {
        return location;
    }

    /**
     * Set the location as vector2
     * @param location
     */
    public void setLocation(Vector2 location) {
        this.location = location;
    }

    /**
     * Returns the size as vector2
     * @return
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * Set the size as vector2
     * @param size
     */
    public void setSize(Vector2 size) {
        this.size = size;
    }

    /**
     * Checks if the vector2 inside of the rectangle area
     * @param point
     * @return
     */
    public boolean contains(Vector2 point) {
        if(point.getX() >= this.location.getX() && point.getX() <= (this.location.getX() + this.size.getX()) && point.getY() >= this.location.getY() && point.getY() <= (this.location.getY() + this.size.getY()))
        {
            return true;
        }
        return false;
    }

}
