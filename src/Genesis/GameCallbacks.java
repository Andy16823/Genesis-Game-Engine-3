/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Behaviors.MouseController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Andy
 */
public interface GameCallbacks {
    void onUpdate();
    void afterUpdate();
    void onKeyDown(KeyEvent e);
    void onKeyRelease(KeyEvent e);
    void onMouseDown(MouseEvent e);
    void onMouseRelease(MouseEvent e);
    void onMouseClick(MouseEvent e);
    void onMouseEnter(MouseEvent e);
    void onMouseLeave(MouseEvent e);
    void onMouseMove(MouseEvent e);
    void onMouseWheeleMove(MouseWheelEvent e);
}
