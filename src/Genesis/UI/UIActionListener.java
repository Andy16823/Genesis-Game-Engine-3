/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import java.awt.event.MouseEvent;

/**
 *
 * @author Andy
 */
public interface UIActionListener {
    public void CB_UI_ON_HOVER(MouseEvent e, UIElement sender);
    public void CB_UI_ON_LEAVE(MouseEvent e, UIElement sender);
    public void CB_UI_ON_CLICK(MouseEvent e, UIElement sender);
}
