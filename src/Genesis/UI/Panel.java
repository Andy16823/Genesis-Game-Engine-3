package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends UIElement {

    public Panel(String name, Vector2 location, Vector2 size) {
        this.setName(name);
        this.setLocation(location);
        this.setSize(size);
        this.setBackgroundColor(Genesis.Toolkit.getColorAlpha(Color.BLACK, 120));
        this.setBorderColor(Color.BLACK);
        this.setEnabled(true);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);

        BufferedImage renderTarget = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0,0, this.getSize().getX(), this.getSize().getY());
        g2d.setColor(this.getBorderColor());
        g2d.drawRect(0,0,this.getSize().getX(), this.getSize().getY());


        g.drawImage(renderTarget, this.getLocation().getX(), this.getLocation().getY(), null);
    }

}
