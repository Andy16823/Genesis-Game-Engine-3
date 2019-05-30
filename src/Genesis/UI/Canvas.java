/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Canvas {
    private Vector<UIElement> Elements;
    private Vector<UIActionListener> Callbacks;
    private Vector2 location;
    private Vector2 size;
    private boolean enable;
    private String Name;
    
    /**
     * Create a new Canvas class. We recommend to use the bounds from the screen
     * @param Location the location. Recommend to use 0,0
     * @param Size the size
     */
    public Canvas (Vector2 Location, Vector2 Size) {
        this.Elements = new Vector<UIElement>();
        this.Callbacks = new Vector<UIActionListener>();
        this.location = Location;
        this.size = Size;
        this.enable = true;
        this.Name = "canvas";
    }
    
    /**
     * Create a new Canvas class. We recommend to use the bounds from the screen
     * @param Location the location. Recommend to use 0,0
     * @param Size the size
     */
    public Canvas (String Name, Vector2 Location, Vector2 Size) {
        this.Elements = new Vector<UIElement>();
        this.Callbacks = new Vector<UIActionListener>();
        this.location = Location;
        this.size = Size;
        this.enable = true;
        this.Name = Name;
    }
    
    /**
     * Adds a new ui element
     * @param element the element to add
     */
    public void AddElement(UIElement element)
    {
        element.setCanvas(this);
        this.Elements.add(element);
    }
    
    /**
     * Adds a new ActionListener for the canvas
     * @param listener the element to add
     */
    public void AddUIActionListener(UIActionListener listener)
    {
        this.Callbacks.add(listener);
    }
    
    /**
     * Renders the canvas and the element
     * @param g 
     */
    public void RenderCanvas(Graphics g)
    {
        BufferedImage image = new BufferedImage(this.size.getX(), this.size.getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics g2d = image.createGraphics();
        for(UIElement e : this.Elements)
        {
            if(e.isEnabled())
            {
                e.Render(g2d);
            }
        }
        g.drawImage(image, this.location.getX(), this.location.getY(), this.size.getX(), this.size.getY(), null);
    }
    
    /**
     * Hover Function
     * @param e 
     */
    public void Hover(MouseEvent e) {
        for(UIActionListener listener : this.Callbacks)
        {
            listener.CB_UI_ON_HOVER(e, null);
        }
        for(UIElement element : this.Elements)
        {
            if(element.Contains(e.getX() - this.getLocation().getX(), e.getY() - this.getLocation().getY()) && element.isEnabled())
            {
                element.Hover(e);
            }
            else {
                element.OnMouseLeave(e);
            }
        }
    }
    
    /**
     * On Mouse Leave Function
     * @param e 
     */
    public void OnMouseLeave(MouseEvent e)
    {
        for(UIActionListener listener : this.Callbacks)
        {
            listener.CB_UI_ON_LEAVE(e, null);
        }
        for(UIElement element : this.Elements)
        {
            element.setHovered(false);
        }
    }
    
    /**
     * On Click Function
     * @param e 
     */
    public void OnClick(MouseEvent e)
    {
        for(UIActionListener listener : this.Callbacks)
        {
            listener.CB_UI_ON_CLICK(e, null);
        }
        for(UIElement element : this.Elements)
        {
            if(element.Contains(e.getX() - location.getX(), e.getY() - location.getY()) && element.isEnabled())
            {
                element.OnMouseClick(e);
            }
        }
    }
    
    public boolean Contains(int X, int Y)
    {
        int Width = this.getLocation().getX() + this.getSize().getX();
        int Height = this.getLocation().getY() + this.getSize().getY();
        if(X > this.getLocation().getX() && Y > this.getLocation().getY() && X < Width && Y < Height )
        {
            return true;
        }
        return false;
    }

    public Vector<UIElement> getElements() {
        return Elements;
    }
    
    public UIElement getElement(String Name){
        for(UIElement e : this.Elements)
        {
            if(e.getName().equals(Name))
            {
                return e;
            }
        }
        return null;
    }

    public void setElements(Vector<UIElement> Elements) {
        this.Elements = Elements;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
        
}
