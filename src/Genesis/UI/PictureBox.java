/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andy
 */
public class PictureBox extends UIElement {
    private BufferedImage Source;
    private boolean transparency = true;

    public PictureBox(Vector2 Location, Vector2 Size, BufferedImage Source)
    {
        this.setLocation(Location);
        this.setSize(Size);
        this.Source = Source;
        this.setBackgroundColor(Color.darkGray);
        this.setBorderColor(Color.BLACK);
    }

    public PictureBox(Vector2 location, Vector2 size) {
        this.setLocation(location);
        this.setSize(size);
        this.setBackgroundColor(Color.darkGray);
        this.setBorderColor(Color.BLACK);
    }

    public PictureBox(String name, Vector2 location, Vector2 size) {
        this.setName(name);
        this.setLocation(location);
        this.setSize(size);
        this.setBackgroundColor(Color.darkGray);
        this.setBorderColor(Color.BLACK);
    }

    public PictureBox(String name, Vector2 location, Vector2 size, BufferedImage source) {
        this.setName(name);
        this.setLocation(location);
        this.setSize(size);
        this.setBackgroundColor(Color.darkGray);
        this.setBorderColor(Color.BLACK);
        this.Source = source;
    }

    public BufferedImage getSource() {
        return Source;
    }

    public void setSource(BufferedImage source) {
        Source = source;
    }

    @Override
    public void onRender(Graphics g) {
        super.onRender(g); //To change body of generated methods, choose Tools | Templates.
        
        BufferedImage image = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Background
        if(!this.transparency) {
            g2d.setColor(this.getBackgroundColor());
            g2d.fillRect(0, 0, this.getSize().getX(), this.getSize().getY());
        }

        // Image
        if(this.Source != null)
        {
            g2d.drawImage(this.Source, 0, 0, this.getSize().getX(), this.getSize().getY(), null);
        }
        
        g.drawImage(image, this.getLocation().getX(), this.getLocation().getY(), null);
    }

    public boolean isTransparency() {
        return transparency;
    }

    public void setTransparency(boolean transparency) {
        this.transparency = transparency;
    }
}
