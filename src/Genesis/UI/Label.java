/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andy
 */
public class Label extends UIElement{
    private String Text;
    private TextAlign textAlign;
    
    public Label(String Name, String Text)
    {
        this.Text = Text;
        this.setName(Name);
        this.setEnabled(true);
        this.setBackgroundColor(null);
        this.setForegroundColor(new Color(255,255,255));
        this.setFont(new Font("Arial", java.awt.Font.PLAIN, 12));
        this.textAlign = TextAlign.LEFT;
    }
    
    @Override
    public void onRender(Graphics g) {
        super.onRender(g);
        if(this.isEnabled())
        {
            int x = 0;
            int y = 0;

            BufferedImage renderTarget = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2D = renderTarget.createGraphics();
            java.awt.Font oldFont = g2D.getFont();
            g2D.setFont(this.getFont());
            FontMetrics fontMetrics = g2D.getFontMetrics();
            if(this.textAlign == TextAlign.CENTER) {
                int textWidth = fontMetrics.stringWidth(this.Text);
                int textHeight = fontMetrics.getHeight();
                x = (this.getSize().getX() / 2) - (textWidth / 2);
                y = (this.getSize().getY() / 2) - (textHeight / 2);
            }
            else if(this.textAlign == TextAlign.RIGHT) {
                int textWidth = fontMetrics.stringWidth(this.Text);
                int textHeight = fontMetrics.getHeight();
                x = this.getSize().getX() - textWidth;
                y = (this.getSize().getY() / 2) - (textHeight / 2);
            }
            else {
                int textHeight = fontMetrics.getHeight();
                x = 0;
                y = (this.getSize().getY() / 2) - (textHeight / 2);
            }

            g2D.setColor(this.getForegroundColor());
            g2D.drawString(Text, x, y);
            g2D.setFont(oldFont);
            g.drawImage(renderTarget, this.getLocation().getX(), this.getLocation().getY(), null);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public TextAlign getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(TextAlign textAlign) {
        this.textAlign = textAlign;
    }
}
