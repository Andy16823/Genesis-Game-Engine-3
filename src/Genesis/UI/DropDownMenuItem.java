package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class DropDownMenuItem extends DropDownMenuElement{
    private String name;
    private String text;

    public DropDownMenuItem(String name, String text) {
        super(name, text);
        this.name = name;
        this.text = text;
    }

    @Override
    public void Render(Graphics2D g, Vector2 location) {
        super.Render(g, location);
        int width = this.getParent().getSize().getX();
        int height = this.getParent().getItemHeight();

        BufferedImage renderTarget = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        g2d.setColor(new Color(0,0,0, 200));
        g2d.fillRect(0,0, width, height);
        g2d.setColor(Color.WHITE);
        g2d.setFont(this.getFont());
        FontMetrics fm = g2d.getFontMetrics();

        int textWidth = fm.stringWidth(this.text);
        int textHeight = fm.getHeight();
        int tx = (width / 2) - (textWidth / 2);
        int ty = (height / 2) + (textHeight / 2);

        g2d.drawString(this.text, tx, ty);
        g.drawImage(renderTarget, location.getX(),location.getY(), null);
    }

    @Override
    public void activate() {
        for (DropDownMenuItemActionListener e : this.getActionListeners()) {
            e.OnMenuItemActivation(this);
        }
    }

    @Override
    public void select() {
        for (DropDownMenuItemActionListener e : this.getActionListeners()) {
            e.OnMenuItemSelection(this);
        }
    }
}
