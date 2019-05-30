/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Andy
 */
public interface SceneActionListener {
    void BeforeRender (Graphics g);
    void AfterRender (Graphics g);
    void BeforeRenderStaticElements (Graphics2D g);
    void AfterRenderStaticElements (Graphics2D g);
    void BeforeAddGameElement(GameElement e);
    void AfterAddGameElement(GameElement e);
}
