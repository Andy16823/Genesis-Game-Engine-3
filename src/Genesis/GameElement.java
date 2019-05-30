/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class GameElement implements Cloneable{
    private Vector<GameBehavior> behaviors;
    private String name;
    private String tag;
    private BufferedImage sprite;
    private boolean enabled;
    private RenderMode render_mode;
    private Vector2 location;
    private Vector2 size;
    private double rotation;
    
    /**
     * 
     * @param Name The name of the element
     * @param Location The location of the element
     * @param Size The size of the element
     * @param Sprite The texture of the element
     * @param Type The render type of the element
     */
    public GameElement(String Name, Vector2 Location, Vector2 Size, BufferedImage Sprite, RenderMode Type)
    {
        this.behaviors = new Vector<GameBehavior>();
        this.name = Name;
        this.location = Location;
        this.size = Size;
        this.sprite = Sprite; 
        this.render_mode = Type;
        this.enabled = true;
    }

    /**
     * Update the GameElement
     */
    public void OnUpdate() {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_UPDATE();
        }
    }
    
    /**
     * Method before the element beeing rendert
     * @param g 
     */
    public void BevoreRender(Graphics g) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.BEVORE_RENDER(g);
        }
    }
    
    /**
     * Method after the element beeing rendert
     * @param g 
     */
    public void AfterRender(Graphics g) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.AFTER_RENDER(g);
        }
    }
    
    /**
     * Calls the behavior on key down event
     * @param e 
     */
    public void OnKeyDown(KeyEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_KEY_DOWN(e);
        }
    }
    
    /**
     * Calls the behavior on key release event
     * @param e 
     */
    public void OnKeyUp(KeyEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_KEY_RELEASE(e);
        }
    }
    
    /**
     * Calls the behavior on mouse klick event
     * @param e 
     */
    public void OnMouseClick(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_CLICK(e);
        }
    }
    
    /**
     * Calls the behavior on mouse down event
     * @param e 
     */
    public void OnMouseDown(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_DOWN(e);
        }
    }
    
    /**
     * Calls the behavior on mouse relese event
     * @param e 
     */
    public void OnMouseUp(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_RELEASE(e);
        }
    }
    
    /**
     * Calls the beahior on mouse enter event
     * @param e 
     */
    public void OnMouseEnter(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_ENTER(e);
        }
    }
    
    /**
     * Calls the behavior on mouse leave event
     * @param e 
     */
    public void OnMouseLeave(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.ON_MOUSE_LEAVE(e);
        }
    }
    
    /**
     * Returns the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the element
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the tag of the element
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the tag of the element
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the sprite of the element
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Set the sprite of the element
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Returns the enabled state
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the enable state
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the render mode of the elment
     */
    public RenderMode getRender_mode() {
        return render_mode;
    }

    /**
     * Set the render mode of the element
     */
    public void setRender_mode(RenderMode render_mode) {
        this.render_mode = render_mode;
    }

    /**
     * Returns the location of the element
     */
    public Vector2 getLocation() {
        return location;
    }

    /**
     * Set the location of the element
     */
    public void setLocation(Vector2 location) {
        this.location = location;
    }

    /**
     * Retuns the size of the element
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * Set the size of the element
     */
    public void setSize(Vector2 size) {
        this.size = size;
    }
    
    /**
     * Adds a new game beahvior to the element
     */
    public void addBehavior(GameBehavior behavior) {
        behavior.setParent(this);
        this.behaviors.add(behavior);
    }

    /**
     * Returns the behaviors of the element
     */
    public Vector<GameBehavior> getBehaviors() {
        return behaviors;
    }

    @Override
    public GameElement clone() throws CloneNotSupportedException {
        GameElement e = (GameElement) super.clone();
        return e; //To change body of generated methods, choose Tools | Templates.
    }    

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public GameBehavior getBehaviorOfType(String Name) {
        for (GameBehavior e: this.behaviors)
        {
            System.out.println(e.getClass().getName());
            if(e.getClass().getName().equals(Name))
            {
                return e;
            }
        }
        return null;
    }
}

