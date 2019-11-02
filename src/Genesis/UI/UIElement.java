/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public abstract class UIElement {
    private Vector<UIActionListener> ActionLisener;
    private String name;
    private String tag;
    private String text;
    private boolean enabled;
    private Vector2 location;
    private Vector2 size;
    private Color ForegroundColor;
    private Color BackgroundColor;
    private Color BorderColor;
    private Font Font;
    private boolean Hovered;
    private boolean Border;
    private int BorderWidth;
    private Canvas Canvas;
    private UIElement Parent;
    
    public UIElement() {
        this.ActionLisener = new Vector<UIActionListener>();
        this.enabled = true;
    }
       
    public void OnUpdate(){
    
    }
    public void Render(Graphics g){
    
    }
    
    public void Hover(MouseEvent e) {
        this.Hovered = true;
        for(UIActionListener Listener : this.ActionLisener)
        {
            Listener.CB_UI_ON_HOVER(e, this);
        }
    }
    
    public void OnMouseLeave(MouseEvent e)    {
        this.Hovered = false;
        for(UIActionListener Listener: this.ActionLisener)
        {
            Listener.CB_UI_ON_LEAVE(e, this);
        }
    }
    
    public void OnMouseClick(MouseEvent e) {
        for(UIActionListener listener : this.ActionLisener)
        {
            listener.CB_UI_ON_CLICK(e, this);
        }
    }
    
    public void AddUIActionListener(UIActionListener e)
    {
        this.ActionLisener.add(e);
    }
    
    public boolean Contains(int X, int Y) {
        int Width = this.getLocation().getX() + this.getSize().getX();
        int Height = this.getLocation().getY() + this.getSize().getY();
        if(X > this.getLocation().getX() && Y > this.getLocation().getY() && X < Width && Y < Height)
        {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Color getForegroundColor() {
        return ForegroundColor;
    }

    public void setForegroundColor(Color ForegroundColor) {
        this.ForegroundColor = ForegroundColor;
    }

    public Color getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(Color BackgroundColor) {
        this.BackgroundColor = BackgroundColor;
    }

    public Font getFont() {
        return Font;
    }

    public void setFont(Font Font) {
        this.Font = Font;
    }

    public boolean isHovered() {
        return Hovered;
    }

    public void setHovered(boolean Hovered) {
        this.Hovered = Hovered;
    }

    public Color getBorderColor() {
        return BorderColor;
    }

    public void setBorderColor(Color BorderColor) {
        this.BorderColor = BorderColor;
    }

    public boolean isBorder() {
        return Border;
    }

    public void setBorder(boolean Border) {
        this.Border = Border;
    }

    public int getBorderWidth() {
        return BorderWidth;
    }

    public void setBorderWidth(int BorderWidth) {
        this.BorderWidth = BorderWidth;
    }    

    public Canvas getCanvas() {
        return Canvas;
    }

    public void setCanvas(Canvas Canvas) {
        this.Canvas = Canvas;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UIElement getParent() {
        return Parent;
    }

    public void setParent(UIElement Parent) {
        this.Parent = Parent;
    }

}
