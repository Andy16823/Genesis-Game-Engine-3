/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Andy
 */
public abstract class GameBehavior {
    private GameElement parent;
    
    public GameBehavior(GameElement element)
    {
        this.parent = element;
    }
    
    public GameBehavior()
    {
        
    }
    
    public void BEVORE_RENDER(Graphics g){
        
    }
    
    public void AFTER_RENDER(Graphics g){
        
    }
    
    public void ON_KEY_DOWN(KeyEvent e){
        
    }
    
    public void ON_KEY_RELEASE(KeyEvent e){
        
    }
    
    public void ON_MOUSE_CLICK(MouseEvent e){
        
    }
    
    public void ON_MOUSE_DOWN(MouseEvent e){
        
    }
    
    public void ON_MOUSE_RELEASE(MouseEvent e){
        
    }
    public void ON_MOUSE_ENTER(MouseEvent e){
        
    }
    
    public void ON_MOUSE_LEAVE(MouseEvent e){
        
    }
    
    public void ON_UPDATE() {
        
    }

    public GameElement getParent() {
        return parent;
    }

    public void setParent(GameElement parent) {
        this.parent = parent;
    }
    
}
