package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PictureButton extends UIElement{
    private BufferedImage buttonImage;
    private BufferedImage hoverImage;

    public PictureButton(String name, Vector2 location, Vector2 size, BufferedImage image) {
        this.buttonImage = image;
        this.setName(name);
        this.setLocation(location);
        this.setSize(size);
    }

    @Override
    public void onRender(Graphics g) {
        super.onRender(g);

        BufferedImage image = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if(this.buttonImage != null)
        {
            if(this.isHovered()) {
                if(this.hoverImage != null) {
                    g2d.drawImage(this.hoverImage, 0, 0, this.getSize().getX(), this.getSize().getY(), null);
                }
                else {
                    g2d.drawImage(this.buttonImage, 0, 0, this.getSize().getX(), this.getSize().getY(), null);
                }
            }
            else {
                g2d.drawImage(this.buttonImage, 0, 0, this.getSize().getX(), this.getSize().getY(), null);
            }
        }
        g.drawImage(image, this.getLocation().getX(), this.getLocation().getY(), null);
    }

    public BufferedImage getButtonImage() {
        return buttonImage;
    }

    public void setButtonImage(BufferedImage buttonImage) {
        this.buttonImage = buttonImage;
    }

    public BufferedImage getHoverImage() {
        return hoverImage;
    }

    public void setHoverImage(BufferedImage hoverImage) {
        this.hoverImage = hoverImage;
    }
}
