package Genesis.Prefabs;

import Genesis.Behaviors.MouseController2;
import Genesis.Game;
import Genesis.GameElement;
import Genesis.Graphics.Camera;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerPrefab extends GameElement {
    private Camera camera;
    private MouseController2 mouseController;


    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     * @param Sprite   The texture of the element
     * @param Camera     The camare for the player element
     */
    public PlayerPrefab(String Name, Vector2 Location, Vector2 Size, BufferedImage Sprite, Camera Camera) {
        super(Name, Location, Size, Sprite, RenderMode.DYNAMIC);
        this.camera = Camera;
        this.mouseController = new MouseController2();
        this.addBehavior(this.mouseController);
    }

    /**
     * Set the destination for the player
     * @param x
     * @param y
     */
    public void setDestination(int x, int y) {
        this.mouseController.setDestination(new Vector2(x, y));
    }

    @Override
    public void onUpdate(Game game) {
        super.onUpdate(game);
        if(this.mouseController.hasDestination()) {
            this.mouseController.rotateAt();
        }
    }

    /**
     * Moves the player towards the destination
     * @param speed
     */
    public void move(int speed) {
        if(this.mouseController.hasDestination()) {
            this.mouseController.move(speed);
            this.camera.snapToGameElement(this);
        }
    }

    @Override
    public void afterUpdate() {
        super.afterUpdate();
    }

    @Override
    public void afterRender(Graphics g) {
        super.afterRender(g);
        /*float radians = (float) Math.toRadians(this.getRotation());
        float startX = this.getCenterLocation().getX();
        float startY = this.getCenterLocation().getY();
        float x = (float) (startX + 40 * Math.cos(radians));
        float y = (float) (startY + 40 * Math.sin(radians));

        g.drawLine(this.getCenterLocation().getX(), this.getCenterLocation().getY(), (int) x, (int) y);*/
    }
}
