/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andy
 */
public class Button extends UIElement {
    private String Text;
    private Color GradientColor;
    private Color HoverBackgroundColor;
    
    public Button(String Name, String Text2)
    {
        this.Text = Text2;
        this.setName(Name);
        this.setForegroundColor(new Color(255,255,255));
        this.setBackgroundColor(new Color(0f,0f,0f, 0.9f));
        this.GradientColor = new Color(this.getBackgroundColor().getRed(), this.getBackgroundColor().getGreen(), this.getBackgroundColor().getBlue(), 0.4f);
        this.HoverBackgroundColor = Color.RED;
        this.setEnabled(true);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g); 
        
        
        // Create Render Contex
        BufferedImage image = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
       
        // Draw Background
        if (this.isHovered())
        {
            g2d.setColor(HoverBackgroundColor);
        }
        else {
           int X = this.getSize().getX() /2;
           GradientPaint background  = new GradientPaint(X, this.getSize().getY(), this.getBackgroundColor(), X, 0, this.GradientColor);
           g2d.setPaint(background);
        }
        g2d.fillRect(0, 0, this.getSize().getX(), this.getSize().getY());
        
        // Draw Text
        g2d.setColor(this.getForegroundColor());
        if(this.getFont()!= null)
        {
            g2d.setFont(this.getFont());
        }
        FontMetrics metric = g2d.getFontMetrics();
        int sX = (this.getSize().getX() / 2) - metric.stringWidth(Text) /2;
        int sY = (this.getSize().getY() / 2) + (metric.getHeight() / 4);
        g2d.drawString(Text, sX, sY);
        
        // Draw the Image
        g.drawImage(image, this.getLocation().getX(), this.getLocation().getY(), null);
    }
    
    
    
}
