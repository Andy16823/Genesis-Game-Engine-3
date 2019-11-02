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
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class ToogleButton extends UIElement {
    private Vector<String> Options;
    private int SelectedIndex;
    
    public ToogleButton(Vector2 Location, Vector2 Size)
    {
        this.setLocation(Location);
        this.setSize(Size);
        this.Options = new Vector<String>();
        this.setBackgroundColor(Color.darkGray);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);
        int ToogleWidth = this.getSize().getY();
        
        BufferedImage bmp = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bmp.createGraphics();
        
        // render left button
        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0, 0, ToogleWidth, this.getSize().getY());
        
        // render display field
        int x = ToogleWidth + 2;
        int width = this.getSize().getX() - (2 * ToogleWidth) - 4;
        g2d.fillRect(x, 0, width, this.getSize().getY());
        
        // render right button
        int x2 = this.getSize().getX() - ToogleWidth;
        g2d.fillRect(x2, 0, ToogleWidth, this.getSize().getY());
        
        g.drawImage(bmp, this.getLocation().getX(), this.getLocation().getY(), null);
    }
    
    
    
}
