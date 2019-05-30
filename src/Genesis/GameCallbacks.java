/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Behaviors.MouseController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Andy
 */
public interface GameCallbacks {
    void CB_ON_UPDATE();
    void CB_ON_KEY_DOWN(KeyEvent e);
    void CB_ON_KEY_RELEASE(KeyEvent e);
    void CB_ON_MOUSE_DOWN(MouseEvent e);
    void CB_ON_MOUSE_RELEASE(MouseEvent e);
    void CB_ON_MOUSE_CLICK(MouseEvent e);
    void CB_ON_MOUSE_ENTER(MouseEvent e);
    void CB_ON_MOUSE_LEAVE(MouseEvent e);
    void CB_ON_MOUSE_MOVE(MouseEvent e);
}
