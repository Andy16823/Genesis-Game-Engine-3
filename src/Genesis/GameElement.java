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
    private boolean ignoreSceneTranform;
    private boolean mouseFocused;
    
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
        this.ignoreSceneTranform = false;
    }

    /**
     * Calls the BEFORE_UPDATE function from the gamebehaviors. This function will be called even the
     * GameElement is disabled
     */
    public void beforeUpdate() {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.beforeUpdate(this);
        }
    }

    /**
     * Update the GameElement
     */
    public void onUpdate(Game game) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onUpdate(game);
        }
    }

    /**
     * Calls the AFTER_UPDATE function of the GameBehaviors. This function will be called even the
     * GameElement is disabled
     */
    public void afterUpdate() {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.afterUpdate(this);
        }
    }
    
    /**
     * Method before the element beeing rendert
     * @param g 
     */
    public void bevoreRender(Graphics g) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.bevoreRender(g);
        }
    }
    
    /**
     * Method after the element beeing rendert
     * @param g 
     */
    public void afterRender(Graphics g) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.afterRender(g);
        }
    }
    
    /**
     * Calls the behavior on key down event
     * @param e 
     */
    public void onKeyDown(KeyEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onKeyDown(e);
        }
    }
    
    /**
     * Calls the behavior on key release event
     * @param e 
     */
    public void onKeyUp(KeyEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onKeyRelease(e);
        }
    }
    
    /**
     * Calls the behavior on mouse klick event
     * @param e 
     */
    public void onMouseClick(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onMouseClick(e);
        }
    }
    
    /**
     * Calls the behavior on mouse down event
     * @param e 
     */
    public void onMouseDown(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onMouseDown(e);
        }
    }
    
    /**
     * Calls the behavior on mouse relese event
     * @param e 
     */
    public void onMouseUp(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onMouseRelease(e);
        }
    }
    
    /**
     * Calls the beahior on mouse enter event
     * @param e 
     */
    public void onMouseEnter(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onMouseEnter(e);
        }
    }
    
    /**
     * Calls the behavior on mouse leave event
     * @param e 
     */
    public void onMouseLeave(MouseEvent e) {
        for(GameBehavior behavior : this.behaviors)
        {
            behavior.onMouseLeave(e);
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

    public boolean isMouseFocused() {
        return mouseFocused;
    }

    public void setMouseFocused(boolean mouseFocused) {
        this.mouseFocused = mouseFocused;
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
            if(e.getClass().getName().equals(Name))
            {
                return e;
            }
        }
        return null;
    }

    public void InitialBehaviors() {
        for(GameBehavior behavior : this.behaviors) {
            behavior.onInit();
        }
    }

    public Vector2 getCenterLocation() {
        return new Vector2(this.location.getX() + (this.getSize().getX() / 2), this.location.getY() + (this.getSize().getY() /2));
    }

    public boolean isIgnoreSceneTranform() {
        return ignoreSceneTranform;
    }

    public void setIgnoreSceneTranform(boolean ignoreSceneTranform) {
        this.ignoreSceneTranform = ignoreSceneTranform;
    }

    public boolean contains(Vector2 point) {
        if(point.getX() > this.getLocation().getX() && point.getY() > this.getLocation().getY() && point.getX() < (this.getLocation().getX() + this.getSize().getX()) && point.getY() < (this.getLocation().getY() + this.getSize().getY()))
        {
            return true;
        }
        return  false;
    }

    public boolean contains(int x, int y) {
        if(x > this.getLocation().getX() && y > this.getLocation().getY() && x < (this.getLocation().getX() + this.getSize().getX()) && y < (this.getLocation().getY() + this.getSize().getY()))
        {
            return true;
        }
        return  false;
    }

    public Vector2 forward(int width)
    {
        float radians = (float) Math.toRadians(this.getRotation());
        float startX = this.getCenterLocation().getX();
        float startY = this.getCenterLocation().getY();
        float x = (float) (startX + width * Math.cos(radians));
        float y = (float) (startY + width * Math.sin(radians));
        return new Vector2((int)x, (int)y);
    }

    public boolean intersects(GameElement ref) {
        Vector2 ref_top_left = new Vector2(ref.getLocation().getX(), ref.getLocation().getY());
        Vector2 ref_top_right = new Vector2((ref.getLocation().getX() + ref.getSize().getX()), ref.getLocation().getY());
        Vector2 ref_bottom_right = new Vector2((ref.getLocation().getX() + ref.getSize().getX()), (ref.getLocation().getY() + ref.getSize().getY()));
        Vector2 ref_bottom_left = new Vector2(ref.getLocation().getX(), (ref.getLocation().getY() + ref.getSize().getY()));

        if(this.contains(ref_top_left)) {
            return true;
        }
        else if(this.contains(ref_top_right)) {
            return true;
        }
        else if(this.contains(ref_bottom_right)) {
            return true;
        }
        else if(this.contains(ref_bottom_left)) {
            return true;
        }
        else {
            return false;
        }
    }

}

