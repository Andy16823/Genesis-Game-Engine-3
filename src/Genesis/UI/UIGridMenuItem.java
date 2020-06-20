package Genesis.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIGridMenuItem extends UIMenuItem {

    public UIGridMenuItem(String Name, String Text, BufferedImage image) {
        super(Name, Text, image);
    }

    @Override
    public void renderItem(Graphics2D g) {
        super.renderItem(g);

        BufferedImage renderTarget = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        int imgWidth = this.getSize().getX() - (2 * this.getPadding());
        int imgHeight = this.getSize().getY() - (2 * this.getPadding());
        g2d.drawImage(this.getImage(), this.getPadding(), this.getPadding(), imgWidth, imgHeight, null);

        FontMetrics fm = g2d.getFontMetrics();
        double textX = (this.getSize().getX() / 2) - (fm.stringWidth(this.getText()) / 2);
        double textY = this.getSize().getY() - fm.getHeight() - 5;
        g2d.drawString(this.getText(), (int) textX, (int) textY);

        g.drawImage(renderTarget, this.getLocation().getX(), this.getLocation().getY(), this.getSize().getX(), this.getSize().getY(), null);
    }
}
