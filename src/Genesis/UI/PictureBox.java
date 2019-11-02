/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andy
 */
public class PictureBox extends UIElement {
    private BufferedImage Source;

    public PictureBox(Vector2 Location, Vector2 Size, BufferedImage Source)
    {
        this.setLocation(Location);
        this.setSize(Size);
        this.Source = Source;
        this.setBackgroundColor(Color.darkGray);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g); //To change body of generated methods, choose Tools | Templates.
        
        BufferedImage image = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Background
        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0, 0, this.getSize().getX(), this.getSize().getY());
        
        // Image
        if(this.Source != null)
        {
            g2d.drawImage(this.Source, 0, 0, this.getSize().getX(), this.getSize().getY(), null);
        }
        
        g.drawImage(image, this.getLocation().getX(), this.getLocation().getY(), null);
    }
    
    
    
}
