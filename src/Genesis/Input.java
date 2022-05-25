/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import java.util.Date;
import java.util.Vector;

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
    private Vector<Integer> keysDown;

    public Input() {
        this.keysDown = new Vector<>();
    }
    
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
        if(!this.keysDown.contains(InputKey)) {
            this.keysDown.add(InputKey);
        }
    }

    public void onKeyRelease(int Key) {
        if(this.keysDown.contains(Key)) {
            this.keysDown.remove(Integer.valueOf(Key));
        }
    }

    public boolean isKeyDown(int key) {
        return this.keysDown.contains(key);
    }

    public boolean isAnyKeyDown() {
        if(this.keysDown.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
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

    public void clearInput() {
        this.isInput = false;
        this.InputKey = 0;
        this.MouseX = 0;
        this.MouseY = 0;
        this.MouseInputKey = 0;
        this.MouseInput = false;
    }
    
}
