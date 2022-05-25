package Genesis;

import Genesis.Graphics.RenderMode;
import Genesis.Graphics.SpriteSheet;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Sprite extends GameElement{
    private SpriteSheet spriteSheet;
    private int spriteX;
    private int spriteY;

    /**
     * @param Name     The name of the element
     * @param Location The location of the element
     * @param Size     The size of the element
     * @param Type     The render type of the element
     */
    public Sprite(String Name, Vector2 Location, Vector2 Size, SpriteSheet spriteSheet, int sX, int sY, RenderMode Type) {
        super(Name, Location, Size, null, Type);
        this.spriteSheet = spriteSheet;
        this.spriteX = sX;
        this.spriteY = sY;
    }

    public void defineSprite(int x, int y) {
        this.spriteX = x;
        this.spriteY = y;
    }

    public void defineSprite(int[] cords) {
        this.spriteX = cords[0];
        this.spriteY = cords[1];
    }

    public void defineSprite(int[] cords, int n) {
        int x = n * 2;
        this.spriteX = cords[x];
        this.spriteY = cords[x + 1];
    }

    @Override
    public void renderGameElement(Graphics2D g) {
        //super.renderGameElement(g);
        this.bevoreRender(g);
        AffineTransform oldTransform = g.getTransform();
        AffineTransform newtransform = new AffineTransform();
        newtransform.rotate(Math.toRadians(this.getRotation()), (this.getLocation().getX() + (this.getSize().getX() / 2)), (this.getLocation().getY() + (this.getSize().getY() / 2)));
        g.setTransform(newtransform);
        if(this.spriteSheet != null) {
            g.drawImage(this.spriteSheet.getSprite(spriteX, spriteY), this.getLocation().getX(), this.getLocation().getY(), this.getSize().getX(), this.getSize().getY(),null);
        }
        g.setTransform(oldTransform);
        this.afterRender(g);
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public void setSpriteX(int spriteX) {
        this.spriteX = spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public void setSpriteY(int spriteY) {
        this.spriteY = spriteY;
    }
}
