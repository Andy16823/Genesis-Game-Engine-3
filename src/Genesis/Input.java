/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import java.util.Date;

/**
 *
 * @author Andy
 */
public class Input {
    private boolean isInput = false;
    private int InputKey;
    private int MouseX;
    private int MouseY;
    private boolean MouseInput;
    private int MouseInputKey;
    
    public boolean isIsInput() {
        return isInput;
    }

    public void setIsInput(boolean isInput) {
        this.isInput = isInput;
    }

    public int getInputKey() {
        return InputKey;
    }

    public void setInputKey(int InputKey) {
        this.InputKey = InputKey;
    }

    public int getMouseX() {
        return MouseX;
    }

    public void setMouseX(int MouseX) {
        this.MouseX = MouseX;
    }

    public int getMouseY() {
        return MouseY;
    }

    public void setMouseY(int MouseY) {
        this.MouseY = MouseY;
    }

    public boolean isMouseInput() {
        return MouseInput;
    }

    public void setMouseInput(boolean MouseInput) {
        this.MouseInput = MouseInput;
    }

    public int getMouseInputKey() {
        return MouseInputKey;
    }

    public void setMouseInputKey(int MouseInputKey) {
        this.MouseInputKey = MouseInputKey;
    }
      
    
}
