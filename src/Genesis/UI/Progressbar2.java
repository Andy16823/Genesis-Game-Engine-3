package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Progressbar2 extends UIElement {
    private int stepps;
    private Color steppColor;
    private Color steppBGColor;
    private int steppWidth;
    private int spacing;
    private int maxValue;
    private int value;


    public Progressbar2(Vector2 location, Vector2 size, int stepps, int spacing) {
        this.setSize(size);
        this.setLocation(location);
        this.stepps = stepps;
        this.spacing = spacing;
        this.steppBGColor = new Color(20,114,111);
        this.steppColor = new Color(103,201,202);
        int spacingWidth = spacing * (stepps + 1);
        int widthLeft = size.getX() - spacingWidth;
        this.steppWidth = widthLeft / stepps;
        this.maxValue = 100;
        this.value = 50;
    }

    public int getStepps() {
        return stepps;
    }

    public void setStepps(int stepps) {
        this.stepps = stepps;
    }

    public Color getSteppColor() {
        return steppColor;
    }

    public void setSteppColor(Color steppColor) {
        this.steppColor = steppColor;
    }

    public Color getSteppBGColor() {
        return steppBGColor;
    }

    public void setSteppBGColor(Color steppBGColor) {
        this.steppBGColor = steppBGColor;
    }

    public int getSteppWidth() {
        return steppWidth;
    }

    public void setSteppWidth(int steppWidth) {
        this.steppWidth = steppWidth;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int value) {
        this.value += value;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void onRender(Graphics g) {
        super.onRender(g);

        BufferedImage renderTarget = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        Color oldCol = g2d.getColor();

        for(int i = 0; i < stepps; i++) {
            float x = (i * steppWidth) + (spacing * i) + spacing;
            float y = 0;
            float width = this.steppWidth;
            float height = this.getSize().getY();
            if(i == 0) {
                x = spacing;
            }
            Rectangle2D.Float rect = new Rectangle2D.Float(x,y,width,height);

            int stop = value * stepps / maxValue;
            if(i >= stop) {

                g2d.setColor(steppBGColor);
                g2d.fill(rect);
                g2d.setColor(oldCol);
            }
            else {
                g2d.setColor(steppColor);
                g2d.fill(rect);
                g2d.setColor(oldCol);
            }

        }
        g.drawImage(renderTarget, this.getLocation().getX(), this.getLocation().getY(), null);
    }
}