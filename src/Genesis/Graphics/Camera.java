package Genesis.Graphics;

import Genesis.GameElement;
import Genesis.Math.Vector2;
import Genesis.Scene;

public class Camera {
    private int x;
    private int y;
    private int width;
    private  int height;
    private Scene scene;

    public Camera(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Camera(int x, int y, Vector2 size)
    {
        this.x = x;
        this.y = y;
        this.width = size.getX();
        this.height = size.getY();
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setViewport(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setViewport(Vector2 location)
    {
        this.x = location.getX();
        this.y = location.getY();
    }

    public void setResolution(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void changeResolution(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.lookAtViewport();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void transfromCamera(int x, int y) {
        if(this.scene != null) {
            this.x += x;
            this.y += y;

            this.scene.resetTransform();
            this.scene.transformScene(-this.x +(this.width / 2), -this.y + (this.height / 2));
        }
    }

    public void activateCamera() {
        if(this.scene != null) {
            this.scene.resetTransform();
            this.scene.transformScene(-this.x +(this.width / 2), -this.y + (this.height / 2));
        }
    }

    public void lookAtViewport() {
        if(this.scene != null) {
            this.scene.resetTransform();
            this.scene.transformScene(-this.x +(this.width / 2), -this.y + (this.height / 2));
        }
    }

    public void snapToGameElement(GameElement element)
    {
        this.setX(element.getCenterLocation().getX() - scene.getLocation().getX());
        this.setY(element.getCenterLocation().getY() - scene.getLocation().getY());
        this.lookAtViewport();
    }

}
