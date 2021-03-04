/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
    
    public void bevoreRender(Graphics g){
        
    }
    
    public void afterRender(Graphics g){
        
    }

    public void bevoreRenderElements(Graphics g) {

    }

    public void afterRenderElements(Graphics g) {

    }

    public void onKeyDown(KeyEvent e){
        
    }
    
    public void onKeyRelease(KeyEvent e){
        
    }
    
    public void onMouseClick(MouseEvent e){
        
    }
    
    public void onMouseDown(MouseEvent e){
        
    }
    
    public void onMouseRelease(MouseEvent e){
        
    }
    public void onMouseEnter(MouseEvent e){
        
    }
    
    public void onMouseLeave(MouseEvent e){
        
    }

    public void beforeUpdate(GameElement e) {

    }

    public void onUpdate(Game game) {
        
    }

    public void afterUpdate(GameElement e) {

    }

    public void onInit() {

    }

    public GameElement getParent() {
        return parent;
    }

    public void setParent(GameElement parent) {
        this.parent = parent;
    }
    
}
