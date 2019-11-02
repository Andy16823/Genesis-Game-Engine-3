/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Andy
 */
public class Label extends UIElement{
    private String Text;
    
    public Label(String Name, String Text)
    {
        this.Text = Text;
        this.setName(Name);
        this.setEnabled(true);
        this.setBackgroundColor(null);
        this.setForegroundColor(new Color(255,255,255));
    }
    
    @Override
    public void Render(Graphics g) {
        super.Render(g); 
        if(this.isEnabled())
        {
            g.setColor(this.getForegroundColor());
            g.drawString(Text, this.getLocation().getX(), this.getLocation().getY());
        }
    }

    @Override
    public void OnUpdate() {
        super.OnUpdate(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }
    
    
}
