/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.Graphics;

import Genesis.Math.Vector2;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andy
 */
public class SpotLight extends ILight {
    private int Radius;
    private boolean ShowDiffuse;

    public SpotLight(Vector2 Location, int Intensity, int Radius) {
        super(Location, Intensity);
        this.Radius = Radius;
    }
    public SpotLight(Vector2 Location, int Intensity, int Radius, Color c) {
        super(Location, Intensity);
        this.Radius = Radius;
        this.setDiffuseColor(c);
    }
    
    public SpotLight(Vector2 Location, int Intensity, int Radius, boolean ShowDiffuse) {
        super(Location, Intensity);
        this.Radius = Radius;
        this.ShowDiffuse = ShowDiffuse;
    }
    
    public SpotLight(Vector2 Location, int Intensity, int Radius, Color c, boolean ShowDiffuse) {
        super(Location, Intensity);
        this.Radius = Radius;
        this.setDiffuseColor(c);
        this.ShowDiffuse = ShowDiffuse;
    }

    @Override
    public void RenderLight(Graphics2D g) {
        super.RenderLight(g); 
        BufferedImage LightImg = new BufferedImage(this.Radius, this.Radius, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = LightImg.createGraphics();
        Color c1 = new Color(this.getDiffuseColor().getRed(), this.getDiffuseColor().getGreen(), this.getDiffuseColor().getBlue(), this.getIntensity());
        Color c2 = new Color(this.getDiffuseColor().getRed(), this.getDiffuseColor().getGreen(), this.getDiffuseColor().getBlue(), 0);
        
        float[] dist = {0.0f , 1.0f};
        Color[] colors = { c1, c2};
        RadialGradientPaint paint = new java.awt.RadialGradientPaint( new Point2D.Float(this.Radius / 2, this.Radius / 2), this.Radius / 2, dist, colors);
        
        g2d.setPaint(paint);
        g2d.fillOval(0, 0, Radius, Radius);
        
        g.setComposite(AlphaComposite.DstOut);
        g.drawImage(LightImg, this.getLocation().getX(), this.getLocation().getY(), null);
        
        if(this.ShowDiffuse)
        {
            g.setComposite(AlphaComposite.SrcOver);
            g.drawImage(LightImg, this.getLocation().getX(), this.getLocation().getY(), null);
        }
        
    }
    
    
}
