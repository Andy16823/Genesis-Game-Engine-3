package Genesis.Behaviors;

import Genesis.GameBehavior;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class HighliteBehavior extends GameBehavior {
    private Color OutlineColor;
    private BufferedImage HighliteTexture;
    private boolean enabled;
    private int thickness;
    private boolean blur;

    public HighliteBehavior(Color color, int thickness) {
        this.OutlineColor = color;
        this.enabled = true;
        this.thickness = thickness;
        this.blur = true;
    }


    @Override
    public void onInit() {
        super.onInit();

        this.HighliteTexture = new BufferedImage(this.getParent().getSprite().getWidth(), this.getParent().getSprite().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = this.HighliteTexture.createGraphics();
        g2d.setRenderingHint(RenderingHints .KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(this.OutlineColor);
        int width = this.getParent().getSprite().getWidth();
        int height = this.getParent().getSprite().getHeight();

        for (int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixelColor = new Color(this.getParent().getSprite().getRGB(x,y), true);

                int rX = x - thickness;
                int ry = y - thickness;
                int rwidth = thickness * 2;
                int rheight = thickness *2;

                if(x != 0) {
                    Color prevColor = new Color(this.getParent().getSprite().getRGB(x -1,y), true);
                    if(pixelColor.getAlpha() == 0 && prevColor.getAlpha() != 0) {
                        g2d.fillRect(rX, ry, rwidth, rheight);
                    }
                }
                if(x != (width -1)) {
                    Color nextColor = new Color(this.getParent().getSprite().getRGB(x +1,y), true);
                    if(pixelColor.getAlpha() == 0 && nextColor.getAlpha() != 0) {
                        g2d.fillRect(rX, ry, rwidth, rheight);
                    }
                }
            }
        }

        if(this.blur) {
            Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f,
                    1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
            BufferedImageOp op = new ConvolveOp(kernel);
            this.HighliteTexture = op.filter(this.HighliteTexture, null);
        }

    }

    @Override
    public void onMouseDown(MouseEvent e) {
        super.onMouseDown(e);
        if(this.enabled == false && this.getParent().isMouseFocused()) {
            this.enabled = true;
        }
        else
        {
            enabled = false;
        }
    }

    public void createOutline() {
        this.HighliteTexture = new BufferedImage(this.getParent().getSprite().getWidth(), this.getParent().getSprite().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = this.HighliteTexture.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(this.OutlineColor);
        int width = this.getParent().getSprite().getWidth();
        int height = this.getParent().getSprite().getHeight();

        for (int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixelColor = new Color(this.getParent().getSprite().getRGB(x,y), true);

                int rX = x - thickness;
                int ry = y - thickness;
                int rwidth = thickness * 2;
                int rheight = thickness *2;

                if(x != 0) {
                    Color prevColor = new Color(this.getParent().getSprite().getRGB(x -1,y), true);
                    if(pixelColor.getAlpha() == 0 && prevColor.getAlpha() != 0) {
                        g2d.fillRect(rX, ry, rwidth, rheight);
                    }
                }
                if(x != (width -1)) {
                    Color nextColor = new Color(this.getParent().getSprite().getRGB(x +1,y), true);
                    if(pixelColor.getAlpha() == 0 && nextColor.getAlpha() != 0) {
                        g2d.fillRect(rX, ry, rwidth, rheight);
                    }
                }
            }
        }

    }

    @Override
    public void bevoreRender(Graphics g) {
        super.afterRender(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints .KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if(this.enabled && this.HighliteTexture != null)
        {
            g2d.drawImage(this.HighliteTexture, this.getParent().getLocation().getX(), this.getParent().getLocation().getY(), this.getParent().getSize().getX(), this.getParent().getSize().getY(), null);
        }
    }

}
