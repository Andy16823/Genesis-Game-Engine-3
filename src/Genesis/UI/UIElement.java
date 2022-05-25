/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.GameElement;
import Genesis.Input;
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
    private Vector<UIElement> children;
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
    private boolean Clicked;


    public UIElement() {
        this.ActionLisener = new Vector<UIActionListener>();
        this.children = new Vector<>();
        this.enabled = true;
    }
       
    public void onUpdate(){
        for(UIElement c : this.children) {
            c.onUpdate();
        }
    }
    public void onRender(Graphics g){
    
    }

    public void addChildren(UIElement element) {
        element.setParent(this);
        element.setCanvas(this.Canvas);
        this.children.add(element);
    }

    public Vector2 getElementOffset() {
        int offsetX = this.getLocation().getX();
        int offsetY = this.getLocation().getY();
        UIElement child = this;

        while (child.getParent() != null) {
            offsetX += child.getParent().getLocation().getX();
            offsetY += child.getParent().getLocation().getY();
            child = child.getParent();
        }

        offsetX += Canvas.getLocation().getX();
        offsetY += Canvas.getLocation().getY();
        return new Vector2(offsetX, offsetY);
    }

    public void onHover(MouseEvent e) {
        this.Hovered = true;
        for(UIActionListener Listener : this.ActionLisener)
        {
            Listener.CB_UI_ON_HOVER(e, this);
        }

        for(UIElement element : this.children) {
            if(element.contains(e.getX(), e.getY())) {
                element.onHover(e);
            }
        }

    }
    
    public void onMouseLeave(MouseEvent e)    {
        this.Hovered = false;
        for(UIActionListener Listener: this.ActionLisener)
        {
            Listener.CB_UI_ON_LEAVE(e, this);
        }

        for(UIElement element : this.children) {
            element.onMouseLeave(e);
        }

    }
    
    public void onMouseClick(MouseEvent e) {
        for(UIActionListener listener : this.ActionLisener)
        {
            listener.CB_UI_ON_CLICK(e, this);
        }
        for(UIElement element : this.children) {
            if(element.contains(e.getX(), e.getY())) {
                element.onMouseClick(e);
                element.setClicked(true);
            }
            else {
                element.setClicked(false);
            }
        }
    }
    
    public void AddUIActionListener(UIActionListener e)
    {
        this.ActionLisener.add(e);
    }
    
    public boolean contains(int X, int Y) {
        Vector2 offsetVector = this.getElementOffset();
        int Width = offsetVector.getX() + this.getSize().getX();
        int Height = offsetVector.getY() + this.getSize().getY();
        if(X > offsetVector.getX() && Y > offsetVector.getY() && X < Width && Y < Height)
        {
            return true;
        }
        return false;
    }

    public void onElementAddToCanvas(Canvas c) {
        for(UIElement e : this.children) {
            e.setCanvas(c);
            e.onElementAddToCanvas(c);
        }
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

    public void setLocation(int x, int y) {
        this.location = new Vector2(x, y);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setSize(int x, int y) {
        this.size = new Vector2(x,y);
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

    public boolean isClicked() {
        boolean oldClicked = this.Clicked;
        this.Clicked = false;
        return oldClicked;
    }

    public void setClicked(boolean clicked) {
        Clicked = clicked;
    }

    public void keyPressed(Input e) {

    }

    public void keyReleased(Input e) {

    }

    public Vector<UIElement> getChildren() {
        return children;
    }

    public void setChildren(Vector<UIElement> children) {
        this.children = children;
    }

    public UIElement getChildren(String name) {
        for(UIElement element : this.children) {
            if(element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }

}
