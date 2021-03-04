package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;
import Genesis.Scene;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelCollider extends GameBehavior {
    private float radius;

    public PixelCollider(float radius) {
        this.radius = radius;
    }

    public boolean isCollideInScene(Scene scene) {
        BufferedImage image = new BufferedImage(scene.getSize().getX(), scene.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        for(GameElement e : scene.getAllGeameElements()) {
            if(e!=this.getParent() && e.getRender_mode() == RenderMode.DYNAMIC) {
                g2d.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), null);
            }
        }

        for(int y = 0; y < this.getParent().getSize().getY(); y++) {
            for(int x = 0; x < this.getParent().getSize().getX(); x++) {
                Color c = new Color(image.getRGB((x + this.getParent().getLocation().getX()), (y + this.getParent().getLocation().getY())), true);
                if(c.getAlpha() > 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
