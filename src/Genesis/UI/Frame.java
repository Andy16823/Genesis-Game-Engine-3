/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Frame extends UIElement{
    private Vector<UIElement> Elements;
    private int TaskbarHeight;
    
    /**
     * Creates a new UI Frame
     * @param Location location on canvas
     * @param Size size for the frame
     * @param Text title
     */
    public Frame (Vector2 Location, Vector2 Size, String Text)
    {
        this.setLocation(Location);
        this.setSize(Size);
        this.setText(Text);
        this.setBackgroundColor(new Color(0,0,0,90));
        this.setBorderColor(Color.BLACK);
        this.setBorder(true);
        this.setForegroundColor(Color.WHITE);
        this.TaskbarHeight = 22;
        this.setBorderWidth(1);
        this.Elements = new Vector<UIElement>();
        this.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g); 
        BufferedImage img = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        // Background
        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0, 0, this.getSize().getX(), this.getSize().getY());
        
        // Taskbar
        g2d.setColor(this.getBorderColor());
        g2d.fillRect(0, 0, this.getSize().getX(), this.TaskbarHeight);
        
        // Title
        g2d.setColor(this.getForegroundColor());
        g2d.setFont(this.getFont());
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(this.getText(), 5, this.TaskbarHeight - (fm.getHeight() /2));
        
        g2d.setColor(this.getBorderColor());
        // Border
        if(this.isBorder())
        {
            g2d.drawRect(0, 0, this.getSize().getX() - this.getBorderWidth(), this.getSize().getY() - this.getBorderWidth());
        }
        
        // Render Children
        for(UIElement element : this.Elements)
        {
            if(element.isEnabled())
            {
                element.Render(g2d);
            }
        }
        
        g.drawImage(img, this.getLocation().getX(), this.getLocation().getY(), null);
    }

    @Override
    public void Hover(MouseEvent e) {
        super.Hover(e); 
        for(UIElement element : this.Elements)
        {
            if(element.isEnabled())
            {
                if(element.Contains(e.getX() - this.getCanvas().getLocation().getX() -  this.getLocation().getX(), e.getY()- this.getCanvas().getLocation().getY() - this.getLocation().getY()))
                {
                    element.Hover(e);
                }
            }
        }
    }

    @Override
    public void OnMouseClick(MouseEvent e) {
        super.OnMouseClick(e); 
        for(UIElement element : this.Elements)
        {
            if(element.isEnabled())
            {
                if(element.Contains(e.getX() - this.getCanvas().getLocation().getX() - this.getLocation().getX(), e.getY() - this.getCanvas().getLocation().getY() - this.getLocation().getY()))
                {
                    element.OnMouseClick(e);
                }
            }
        }
    }
    
    /**
     * Adds a children to the frame
     * @param element the element to add
     */
    public void AddElement(UIElement element)
    {
        element.setCanvas(this.getCanvas());
        element.setParent(this);
        this.Elements.add(element);
    }
    
    
}
