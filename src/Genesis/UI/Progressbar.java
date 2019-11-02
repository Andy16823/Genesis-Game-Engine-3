/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Progressbar extends UIElement {
    private Vector<ProgressbarActionListener> ProgressbarActionListener;
    private int Max;
    private int Current;
    private Color BarColor;
    private int space;
    private int IncrementValue;

    public Progressbar() {
        this.ProgressbarActionListener = new Vector<ProgressbarActionListener>();
        this.setBackgroundColor(Color.DARK_GRAY);
        this.BarColor = Color.GREEN;
        this.setEnabled(true);
        this.space = 0;
        this.setCurrent(100);
        this.setIncrementValue(1);
        this.setMax(100);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g); //To change body of generated methods, choose Tools | Templates.
        BufferedImage image = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Draw Background
        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0, 0, this.getSize().getX(), this.getSize().getY());
        
        // Draw Bar
        float width = this.getSize().getX() / 100 * this.Current;
        g2d.setColor(this.BarColor);
        g2d.fillRect(space , space , (int) width - (space * 2), this.getSize().getY() - (space * 2));
        
        g.drawImage(image, this.getLocation().getX(), this.getLocation().getY(), null);
    }
    
    public void AfterCurrentValueChanged()
    {
        for(ProgressbarActionListener listener : this.ProgressbarActionListener)
        {
            listener.AFTER_CURRENT_VALUE_CHANGE(this);
        }
    }
    
    public void Increase()
    {
        this.Current += IncrementValue;
        if(Current > 100)
        {
            this.Current = 100;
        }
        AfterCurrentValueChanged();
    }
    
    public void Decrease() 
    {
        this.Current -= IncrementValue;
        if(Current < 0)
        {
            this.Current = 0;
        }
        AfterCurrentValueChanged();
    }
    
    public int getMax() {
        return Max;
    }

    public void setMax(int Max) {
        this.Max = Max;
    }

    public int getCurrent() {
        return Current;
    }

    public void setCurrent(int c) {
        if(c > 100 || c < 0 )
        {
            this.Current = 0;
            AfterCurrentValueChanged();
            throw new UnsupportedOperationException("Your value is not between 0 and 100!");
        }
        else
        {
            this.Current = c;
        }
        AfterCurrentValueChanged();
    }

    public Color getBarColor() {
        return BarColor;
    }

    public void setBarColor(Color BarColor) {
        this.BarColor = BarColor;
    }  

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getIncrementValue() {
        return IncrementValue;
    }

    public void setIncrementValue(int IncrementValue) {
        this.IncrementValue = IncrementValue;
    }  
}
